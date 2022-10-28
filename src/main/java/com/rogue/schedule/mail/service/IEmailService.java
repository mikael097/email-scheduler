package com.rogue.schedule.mail.service;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.dto.EmailDetails;

public interface IEmailService {
  BasicDto sendEmail(EmailDetails emailDetails);
  BasicDto sendEmailWithAttachment(EmailDetails emailDetails);
}
