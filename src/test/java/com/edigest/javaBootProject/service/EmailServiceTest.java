package com.edigest.javaBootProject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("nihal.jadhav216@gmail.com", "Testing java mail sender", "Helloo!!");
    }
}
