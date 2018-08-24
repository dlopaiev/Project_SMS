/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Denys
 */
public class EmailTLS {
    
    private String username;    
    private String password;
    private String serverSMTP;

    public EmailTLS() {
        
    }
    
    public EmailTLS(String username, String password, String serverSMTP) {
        
        this.username = username;
        this.password = password;
        this.serverSMTP = serverSMTP;
    }
    
    public Session initiateSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", serverSMTP);
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );
        return session;
    }
    
    public void sendEmailTLS(String recipient, String subject, String messageText) {
        
        try {
            Message message = new MimeMessage(this.initiateSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(messageText, "text/html");
            
            Transport.send(message);
            System.out.println("Successfully sent");
        } catch(AuthenticationFailedException afe) {
            System.out.println("Authentication Failed. Check security settings for email provider.");
        } 
        catch(MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public String getUsername() {
        return username;
    }
}
