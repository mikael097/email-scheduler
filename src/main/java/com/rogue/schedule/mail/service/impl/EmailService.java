package com.rogue.schedule.mail.service.impl;

import com.rogue.schedule.mail.dto.BasicDto;
import com.rogue.schedule.mail.dto.EmailDetails;
import com.rogue.schedule.mail.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {
  private final JavaMailSender javaMailSender;

  @Override
  public BasicDto sendEmail(EmailDetails emailDetails) {
    try {

      SimpleMailMessage mailMessage = new SimpleMailMessage();

      mailMessage.setFrom("siba.palo@leucinetech.com");
      mailMessage.setTo(emailDetails.getRecipient());
      mailMessage.setText(emailDetails.getMsgBody());
      mailMessage.setSubject(emailDetails.getSubject());

      javaMailSender.send(mailMessage);
      return BasicDto.builder()
              .message("Successful")
              .build();
    } catch (Exception e) {
      return BasicDto.builder()
              .message("Failed")
              .build();
    }
  }

  @Override
  public BasicDto sendEmailWithAttachment(EmailDetails emailDetails) {
    var mimeMessage
            = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;

    try {
      mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setFrom("b518046@iiit-bh.ac.in");
      mimeMessageHelper.setTo(emailDetails.getRecipient());
      mimeMessageHelper.setText(emailDetails.getMsgBody());
      mimeMessageHelper.setSubject(emailDetails.getSubject());

      FileSystemResource file = new FileSystemResource(emailDetails.getAttachment());

      mimeMessageHelper.addAttachment(
              Objects.requireNonNull(file.getFilename()), file);

      javaMailSender.send(mimeMessage);
      return BasicDto.builder()
              .message("Successful")
              .build();
    } catch (Exception e) {
      return BasicDto.builder()
              .message("Failed")
              .build();
    }
  }
}
