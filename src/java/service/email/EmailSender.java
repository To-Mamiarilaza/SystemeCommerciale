/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.email;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author to
 */
public class EmailSender {
    
    public static void sendEmail(String[] recipients, String subject, String content, String filename) {
        // Sender's email and password
        File file = new File("");
        System.out.println("PATH : " + file.getAbsolutePath());

        final String senderEmail = "mamiarilaza.to@gmail.com";
        final String senderPassword = "kqlw korv hluf efwz";
        
        // Setting all properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        
         // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender
            message.setFrom(new InternetAddress(senderEmail));
            
            // Set the recipients
            InternetAddress[] addresses = new InternetAddress[recipients.length];
            for (int i = 0; i < addresses.length; i++) {
                addresses[i] = new InternetAddress(recipients[i]);
            }
            
            message.setRecipients(Message.RecipientType.TO, addresses);

            // Set the subject and content of the email
            message.setSubject(subject);
            
            // Multipart object
            Multipart multipartObject = new MimeMultipart();
            
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipartObject.addBodyPart(contentPart);
            
            // attaching the file
            if (filename != null) {
                BodyPart filePart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                filePart.setDataHandler(new DataHandler(source));
                filePart.setFileName(filename);
                multipartObject.addBodyPart(filePart);
            }
            
            
            message.setContent(multipartObject);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        String[] recipients = {"mamiarilaza.to@gmail.com"};
        sendEmail(recipients, "Demande d'employe", "Motivé pour travailler avec vous", "base.sql");
    }
}
