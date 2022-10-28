package com.rogue.schedule.mail.quartz.job;

import com.rogue.schedule.mail.dto.EmailDetails;
import com.rogue.schedule.mail.service.IEmailService;
import lombok.AllArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@AllArgsConstructor
public class EmailJob extends QuartzJobBean {
  private final IEmailService emailService;

  @Override
  protected void executeInternal(JobExecutionContext context) {
    final JobDataMap jobDataMap = context.getMergedJobDataMap();
    final String recipient = jobDataMap.getString("email");
    final String subject = jobDataMap.getString("subject");
    final String body = jobDataMap.getString("body");
    final String attachment = jobDataMap.getString("attachment");
    if (ObjectUtils.isEmpty(attachment)) {
      emailService.sendEmail(EmailDetails.builder()
              .msgBody(body)
              .subject(subject)
              .recipient(recipient)
              .build());
    } else
      emailService.sendEmailWithAttachment(EmailDetails.builder()
              .msgBody(body)
              .subject(subject)
              .recipient(recipient)
              .attachment(attachment)
              .build());
  }
}
