package com.rogue.schedule.mail.controller.impl;

import com.rogue.schedule.mail.controller.IEmailScheduleController;
import com.rogue.schedule.mail.dto.EmailRequestDto;
import com.rogue.schedule.mail.dto.EmailResponseDto;
import com.rogue.schedule.mail.quartz.job.EmailJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.UUID;

@Configuration
@Slf4j
@AllArgsConstructor
public class EmailScheduleController implements IEmailScheduleController {

  private final Scheduler scheduler;

  @Override
  public ResponseEntity<EmailResponseDto> scheduleSimpleEmail(@RequestBody EmailRequestDto emailRequestDto) {
    try {
      ZonedDateTime zonedDateTime = ZonedDateTime.of(emailRequestDto.getDateTime(), emailRequestDto.getTimeZone());
      if (zonedDateTime.isBefore(ZonedDateTime.now())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(EmailResponseDto.builder()
                        .success(false)
                        .message("Date time must be after time!")
                        .build());
      }
      JobDetail createJobDetail = buildJobDetail(emailRequestDto);
      Trigger createTrigger = buildTrigger(createJobDetail, zonedDateTime);
      scheduler.scheduleJob(createJobDetail, createTrigger);
      return ResponseEntity.ok(EmailResponseDto.builder()
              .message("Successfully scheduled")
              .jobId(createJobDetail.getKey().getName())
              .jobGroup(createJobDetail.getKey().getGroup())
              .success(true)
              .build());
    } catch (SchedulerException se) {
      log.error("Error while scheduling", se);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(EmailResponseDto.builder()
                      .success(false)
                      .message("Scheduling failed try again after some time!")
                      .build());
    }
  }

  private JobDetail buildJobDetail(EmailRequestDto emailRequestDto) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("email", emailRequestDto.getRecipient());
    jobDataMap.put("subject", emailRequestDto.getSubject());
    jobDataMap.put("body", emailRequestDto.getMsgBody());
    jobDataMap.put("attachment", emailRequestDto.getAttachment());
    jobDataMap.put("dateTime", emailRequestDto.getDateTime());
    return JobBuilder.newJob(EmailJob.class)
            .withIdentity(UUID.randomUUID().toString(), "email-jobs")
            .withDescription("Send email job")
            .usingJobData(jobDataMap)
            .storeDurably()
            .build();
  }

  private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime zonedDateTime) {
    return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.getKey().getName(), "email-triggers")
            .startAt(Date.from(zonedDateTime.toInstant()))
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
            .build();
  }
}
