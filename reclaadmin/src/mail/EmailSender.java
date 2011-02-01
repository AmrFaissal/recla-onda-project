package mail;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

    private String smtpServer;
    private String port;
    private String username;
    private String password;
    private String auth="true";
    private String from;
    private String recipient;
    private String subject;
    private String msg;


    /*
     * getters and setters
     */
    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*
     * Simple Constructor
     */
    public EmailSender() {

    }
    

    /*
     * Sets all necessary information about the SMTP session
     */
    private Properties prepareProperties(String smtpServer, String port, String username, String password, String auth) {

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable","true");
        props.setProperty("mail.smtp.host", smtpServer);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.user", username);
        props.setProperty("mail.smtp.password", password);
        props.setProperty("mail.smtp.auth", auth);

            return props;
    }

    /*
     * prepare a MIME message
     */
    private MimeMessage prepareMessage(Session mailsession, String charset, String from,
            String subject, String HTMLMessage, String recipient) 
            throws MessagingException {

        MimeMessage message = null;

        message = new MimeMessage(mailsession);
        message.setFrom(new InternetAddress(from));
        message.setSubject(subject);
        message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
        message.setContent(HTMLMessage, "text/plain; charset=\""+charset+"\"");

             return message;

    }

    /*
     * Sending mails via web
     */
    public int sendEmail(String from, String password, String subject, String msg, String recipient, String smtpServer, String port, String username)
            throws NoSuchProviderException, MessagingException {

        Transport transport = null;

        Properties props = this.prepareProperties(smtpServer, port, username, password, "true");
        Session mailSession = Session.getDefaultInstance(props, new SMTPAuthenticator(from, password, true));
        transport = mailSession.getTransport("smtp");
        MimeMessage message = this.prepareMessage(mailSession,"ISO-8859-2", from, subject, msg, recipient);
        transport.connect();
        Transport.send(message);

            return 0;
     }

}
