package com.vromanyu.messagems;

import com.vromanyu.messagems.dto.EmployeeCreationMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class MessageMsApplication {

    private final String MAIL_HOST;
    private final String MAIL_PORT;
    private final String MAIL_DEFAULT_ENCODING;
    private final String MAIL_USERNAME;
    private final String MAIL_PASSWORD;

    public  MessageMsApplication(Environment environment) {
        MAIL_HOST = environment.getProperty("mail.host");
        MAIL_PORT = environment.getProperty("mail.port");
        MAIL_DEFAULT_ENCODING = environment.getProperty("mail.default.encoding");
        MAIL_USERNAME = environment.getProperty("mail.username");
        MAIL_PASSWORD = environment.getProperty("mail.password");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageMsApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(MessageMsApplication.class);

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MAIL_HOST);
        mailSender.setPort(Integer.parseInt(MAIL_PORT));
        mailSender.setDefaultEncoding(MAIL_DEFAULT_ENCODING);
        mailSender.setUsername(MAIL_USERNAME);
        mailSender.setPassword(MAIL_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    };

    @Bean
    public Function<EmployeeCreationMessage, EmployeeCreationMessage> log() {
        return message -> {
            logger.info("{} - Received: {}", "consume()", message);
            return message;
        };
    }

    @Bean
    public Consumer<EmployeeCreationMessage> AndEmail(JavaMailSender mailSender, TemplateEngine templateEngine) {
        return message -> {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                Context context = new Context();
                context.setVariable("employeeName", message.firstName() + " " + message.lastName());
                context.setVariable("employeeEmail", message.email());
                context.setVariable("uuid", message.uuid());
                String email = templateEngine.process("email", context);
                helper.setSubject("Email Verification");
                helper.setFrom(MAIL_USERNAME);
                helper.setTo(message.email());
                helper.setText(email, true);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        };
    };
}
