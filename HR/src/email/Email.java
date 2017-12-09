package email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Iterator;
import java.util.List;

public class Email {
	public static boolean sendPassword(String to, String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Nieuw wachtwoord");
			message.setText("Beste\n\nU krijgt deze mail omdat uw wachtwoord is gewijzigd.\nUw nieuwe wachtwoord is: " + password + "\n \nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void createdUser(String to, String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Account aangemaakt");
			message.setText("Beste\n\n\nU krijgt deze mail omdat er een account werd aangemaakt met jouw gegevens. \nUw inlog-gegevens zijn:\nUsername: " + to + "\nWachtwoord: " + password + "\n\n\nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}