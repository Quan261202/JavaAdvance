package com.halloween.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private static final String name = "quan2020606122@gmail.com";
    private static final String pass = "quan261202";
    public static void senMail(String mailTo, String subject, String content){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(name, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(name));
            message.setSubject(subject);
            message.setContent(content, "text/plain");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            Transport.send(message);
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
}
