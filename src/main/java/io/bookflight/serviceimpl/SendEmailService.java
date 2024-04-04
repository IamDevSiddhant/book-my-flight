package io.bookflight.serviceimpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

public void sendEmail(String toEmail,String subject, String body){
    SimpleMailMessage message= new SimpleMailMessage();
    message.setFrom(fromEmail);
    message.setTo(toEmail);
    message.setSubject(subject);
    //email - your booking is confirmed below are the details of your booking
    //id- , name - , date - , departure city - , destination -
    message.setText(body);
    javaMailSender.send(message);

}


}
