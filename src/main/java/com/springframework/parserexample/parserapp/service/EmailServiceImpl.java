package com.springframework.parserexample.parserapp.service;

import com.springframework.parserexample.parserapp.data.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendmail(String emailAddress, List<RestResult> restResult){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);
        msg.setSubject("Testing from Spring Boot");
        msg.setText(formatEmailtoString(restResult));
        javaMailSender.send(msg);
    }

    private String formatEmailtoString(List<RestResult> restResult){
        StringBuffer emailMessage = new StringBuffer();
            emailMessage.append("Movie list: ");
            emailMessage.append("\r\n");
        for (RestResult in:restResult) {
            emailMessage.append("\r\n");
            emailMessage.append("Name: ");
            emailMessage.append(in.getName());
            emailMessage.append("\r\n");
            emailMessage.append("Format: ");
            emailMessage.append(in.getFormat());
            emailMessage.append("\r\n");
            emailMessage.append("Resolution: ");
            emailMessage.append(in.getResolution());
            emailMessage.append("\r\n");
            emailMessage.append("Size: ");
            emailMessage.append(in.getSize());
            emailMessage.append("\r\n");
            emailMessage.append("Click on link: ");
            emailMessage.append(in.getLink());
            emailMessage.append("\r\n");
        }
        return emailMessage.toString();
    }
}
