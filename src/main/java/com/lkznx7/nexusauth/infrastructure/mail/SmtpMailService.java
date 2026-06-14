package com.lkznx7.nexusauth.infrastructure.mail;

import com.lkznx7.nexusauth.application.port.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class SmtpMailService implements MailService {
    @Override
    public String getCodeVerification() {
        // fazer regex para gerar codigo de verificação tem q ser nesse formato
        // (6 caracteres poderndo ser letras ou numeros)
        return "";
    }

    @Override
    public boolean codeIsValid(String code) {
        return false;
    }

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public SmtpMailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    public void  sendMail(String to, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("noreply@lkznx7");
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
