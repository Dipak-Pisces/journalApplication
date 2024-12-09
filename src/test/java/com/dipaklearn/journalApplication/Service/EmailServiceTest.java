package com.dipaklearn.journalApplication.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void test(){
        emailService.sentEmail(
                "pdipak945@gmail.com",
                    "learning",
                "what next? any guesses?"
        );
    }

}