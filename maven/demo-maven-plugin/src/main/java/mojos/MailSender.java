package mojos;


import org.apache.maven.plugins.annotations.Parameter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

public class MailSender {

    @Parameter(property = "project.name")
    private String projectName;

    public void sendMail(final String username, final String password, final String theme){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(username)
            );

            message.setSubject(theme != null? theme: projectName+" :installed successfully.");
            MimeBodyPart mbp = new MimeBodyPart();
            if ((new File("file.txt")).exists()){
            FileDataSource fds = new FileDataSource("file.txt");
            mbp.setDataHandler(new DataHandler(fds));
            mbp.setFileName(fds.getName());
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);
            Transport.send(message);} else {
                message.setText("The report is missing");
                Transport.send(message);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
