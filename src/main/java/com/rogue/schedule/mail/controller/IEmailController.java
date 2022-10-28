package com.rogue.schedule.mail.controller;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.dto.EmailDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
public interface IEmailController {
  @PostMapping("/send/simple")
  BasicDto sendSimpleEmail(@RequestBody EmailDetails emailDetails);

  @PostMapping("/send/attachment")
  BasicDto sendEmailAttachment(@RequestBody EmailDetails emailDetails);
}
