package com.rogue.schedule.mail.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Builder
public class EmailRequestDto {
  private String recipient;
  private String msgBody;
  private String subject;
  private String attachment;
  private LocalDateTime dateTime;
  private ZoneId timeZone;
}
