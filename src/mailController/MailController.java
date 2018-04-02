package mailController;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {
    public static boolean sendEmail(String receiverEmail, String messageSubject, String mailMessage){
        boolean emailStatus = false;
        String senderEmail = "elvinjava1@gmail.com";
        String senderPassword = "Hello123Java%%%";

        //Get properties object
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");   //ssl
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","587");

        //get Session
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        //compose message
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverEmail));
            mimeMessage.setSubject(messageSubject);
            mimeMessage.setText(mailMessage);

            //send message
            Transport.send(mimeMessage);
            emailStatus = true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return emailStatus;
    }
}
