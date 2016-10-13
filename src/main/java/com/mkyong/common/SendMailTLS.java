/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.common;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Claude
 */
public class SendMailTLS {
    
    private static String host = "smtp.gmail.com";
    private static String user = "**********";
    private static String pass = "***********";

    public static void sendEmail(String fromAddr, String toAddr, String subject, String body)
    {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", user);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(prop);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress(fromAddr));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.send(message, message.getAllRecipients()); 
            //The above line has a warning message
            //"The static method send(Message, Address[]) from the type Transport should be accessed in a static way"
            transport.close();
            System.out.println("done");
        }
        catch (AddressException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
    }
    
}
