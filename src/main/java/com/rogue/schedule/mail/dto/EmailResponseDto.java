package com.rogue.schedule.mail.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailResponseDto {
  private boolean success;
  private String jobId;
  private String jobGroup;
  private String message;
}
