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

    //Variables required for session initiation
    private String username;
    private String password;
    private String serverSMTP;

    //Variables required for reporting purpose
    private String status;
    private String recipient;

    public EmailTLS() {

    }

    public EmailTLS(String username, String password, String serverSMTP) {

        //Assigns retrieved values to class variables
        this.username = username;
        this.password = password;
        this.serverSMTP = serverSMTP;
    }

    /**     
     * Method starts session required for email sending.
     * @return Session object     
     */
    public Session initiateSession() {
        //Object required for session properties
        Properties props = new Properties();
        //Adds required properties
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", serverSMTP);
        props.put("mail.smtp.port", "587");

        //Connection to email account is performed
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }
        );
        return session;
    }

    /**     
     * Method performs email sending .     
     * @param recipient email will be sent to this email contact
     * @param subject email subject
     * @param messageText email text
     */
    public void sendEmailTLS(String recipient, String subject, String messageText) {

        try {
            this.recipient = recipient;
            //Message is created
            Message message = new MimeMessage(this.initiateSession());
            //TODO: check username spoofing
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(messageText, "text/html");

            //Message is sent
            Transport.send(message);
            //System.out.println("Successfully sent");
            this.status = "Successfully sent";
        } catch (AuthenticationFailedException afe) {
            System.out.println("Authentication Failed. Check security settings for email provider.");
            this.status = "Authentication Failed.";
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    /**     
     * Method retrieves status of email sending.     
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**     
     * Method retrieves email recipient.     
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**     
     * Method retrieves email account username.     
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
