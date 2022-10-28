package com.rogue.schedule.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class BasicDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 4378771848941731114L;

  private String id;
  private String message;
}
