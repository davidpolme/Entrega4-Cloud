package org.example.domain.service.impl;

import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


@Service
public class EmailService {

    public void sendEmail(String email, String mesg){
        String host = "smtp.gmail.com";
        String username = "oscar7bosigas@gmail.com";
        String password = "";
        String subject = "Prueba de correo electrónico";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(mesg);

            Transport.send(message);

            System.out.println("Correo electrónico enviado.");

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
        }

    }
}
