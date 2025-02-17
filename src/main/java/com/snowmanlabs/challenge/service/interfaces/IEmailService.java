package com.snowmanlabs.challenge.service.interfaces;

public interface IEmailService {
    void sendConfirmationEmail(String email, String bookTitle);
}
