package org.example.dao;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDAO {
    public static void sendEmail(String fullName, String email, String phoneNumber, String carName) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mercedestest@mail.ru", "AvX6Ey66FHxDU6w6kLFL");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mercedestest@mail.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("danildergachevmsk@gmail.com")); // Адрес получателя
        message.setSubject("Новая заявка!");

        String emailContent = "Полное имя: " + fullName + "\n" +
                "Email: " + email + "\n" +
                "Номер телефона: " + phoneNumber + "\n" +
                "Автомобиль: " + carName;

        message.setText(emailContent);
        Transport.send(message);

        Message messageTo = new MimeMessage(session);
        messageTo.setFrom(new InternetAddress("mercedestest@mail.ru"));
        messageTo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); //Адрес получателя
        messageTo.setSubject("Заявка на покупку mercedes!");

        emailContent = fullName + "!" + "\n" +
                "Наша компания получила вашу заявку на " + carName + ", менеджер позвонит вам с 9:00 - 21:00";
        messageTo.setText(emailContent);
        Transport.send(messageTo);

        System.out.println("Письмо отправлено успешно.");
    }
}
