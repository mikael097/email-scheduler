package com.rogue.schedule.mail.controller;

import com.rogue.schedule.mail.dto.EmailRequestDto;
import com.rogue.schedule.mail.dto.EmailResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email/schedule")
public interface IEmailScheduleController {

  @PostMapping("/simpleEmail")
  ResponseEntity<EmailResponseDto> scheduleSimpleEmail(@RequestBody EmailRequestDto emailRequestDto);
}
