package br.com.alura.case_tecnico.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailSenderConfig {

    @Value("${email.sender.username}")
    private String username;

    @Value("${email.sender.password}")
    private String password;

    @Bean
    public EmailSender emailSender() {
        return new EmailSender(username, password);
    }

}
