package com.sec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private final Log log = LogFactory.getLog(this.getClass());

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Value("${spring.webservices.path}")
    private String APP_URL;

    private String ACTIVATION_ROUTE = "activation/";

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String fullName, String key) {
        SimpleMailMessage message = null;

        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject("Sikeres regisztrálás");
            message.setText("Kedves " + fullName + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!" +
                    "\n\n Profilod aktiválásához klikkelj ide: " + APP_URL + ACTIVATION_ROUTE + key);
            javaMailSender.send(message);

        } catch (Exception e) {
            log.error("Hiba e-mail küldéskor az alábbi címre: " + email + "  " + e);
        }

    }

}