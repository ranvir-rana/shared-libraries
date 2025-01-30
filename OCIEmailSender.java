import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class OCIEmailSender {

    public static void main(String[] args) {
        // Replace with your email sender, recipient, and SMTP details
        final String senderEmail = "from@example.com"; // Approved sender email
        final String senderName = "Blog Test Sender";
        final String recipientEmail = "to@example.com"; // Verified recipient email

        // SMTP server details
        final String smtpHost = "sandbox.smtp.mailtrap.io";
        final int smtpPort = 587;

        // SMTP credentials
        final String smtpUsername = "d7e97e5c1268f5"; // Replace with your SMTP username
        final String smtpPassword = "1522af9cff4e1e"; // Replace with your SMTP password

        // Email subject and body
        final String subject = "Email Delivery Blog Test";
        final String bodyText = "This email was sent through the Email Delivery SMTP Interface using Java.";

        // Set properties for the SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Enable SMTP debug
        props.put("mail.debug", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.debug", "true");
        // Create a session with an authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        try {
            // Enable session debugging
            session.setDebug(true);

            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail, senderName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(bodyText);

            // Send the email
            Transport.send(message);
            System.out.println("Email successfully sent!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}