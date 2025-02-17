package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.exception.BusinessException;
import com.snowmanlabs.challenge.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendConfirmationEmail(String email, String bookTitle) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Book reservation confirmed.");
            helper.setText("Your book '" + bookTitle + "' reservation was confirmed!");

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BusinessException("Email failed to be sent. " + e);
        }
    }
}
