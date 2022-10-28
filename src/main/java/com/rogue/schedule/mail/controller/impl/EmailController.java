package com.rogue.schedule.mail.controller.impl;

import com.rogue.schedule.mail.controller.IEmailController;
import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.dto.EmailDetails;
import com.rogue.schedule.mail.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EmailController implements IEmailController {
  private final IEmailService emailService;

  @Override
  public BasicDto sendEmailAttachment(EmailDetails emailDetails) {
    return emailService.sendEmailWithAttachment(emailDetails);
  }

  @Override
  public BasicDto sendSimpleEmail(EmailDetails emailDetails) {
    return emailService.sendEmail(emailDetails);
  }
}
