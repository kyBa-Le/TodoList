package com.example.todolist.API.Restful.ThirdPartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailForTaskCompleting(String toEmail,
                                           String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kb.mytodo@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Task change from myTodo");
        message.setText(body);

        mailSender.send(message);
    }
}
