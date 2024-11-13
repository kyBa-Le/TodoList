package com.example.todolist.Infrastructure.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailForTaskCompleting(String toEmail, String taskTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setSubject("Task change from myTodo");
        message.setText("Your task  '" + taskTitle + "' has been completed!");

        mailSender.send(message);
    }
}
