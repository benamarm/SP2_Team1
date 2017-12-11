package email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import logic.Event;

public class Email{
	public static boolean sendPassword(String to, String password) {
		
		//properties for connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		//make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			// create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Nieuw wachtwoord");
			message.setText("Beste\n\nU krijgt deze mail omdat uw wachtwoord is gewijzigd.\n"
					+ "Uw nieuwe wachtwoord is: " + password + "\n \nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void createdUser(String to, String password) {
		
		//properties connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		//make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			// create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Account aangemaakt");
			message.setText("Beste\n\n\nU krijgt deze mail omdat er een account werd aangemaakt met jouw gegevens. \nUw inlog-gegevens zijn:\n"
					+ "Username: " + to + "\nWachtwoord: " + password + "\n\n\nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void aanvraag(String to, boolean goedgekeurd, Event event) {
		
		//connection properties
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		//make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			// create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			if(goedgekeurd==true) 
			{
				message.setSubject("Goedkeuring opleiding");
				message.setText("Beste \n\n\nU krijgt deze mail omdat uw aanvraag is goedgekeurd voor volgend event:\nOpleiding:"
						+ event.getOpleiding().getNaam() + "\nTrainer: " + event.getNaamTrainer() + "\nAdres: " + event.getAdres() + "\nVan " + event.getStartdatum() + " tot " + event.getEinddatum()+ 
						"\n\n\nGelieve niet te antwoorden op deze mail.");
			}
			else if(goedgekeurd==false)
			{
				message.setSubject("Afkeuring opleiding");
				message.setText("Beste \n\n\nU krijgt deze mail omdat uw aanvraag is afgekeurd voor volgend event:\n"
				+ event.getOpleiding().getNaam() + "\n" + event.getNaamTrainer() + "\n" + event.getAdres() + "\nVan " + event.getStartdatum() + " tot " + event.getEinddatum()+ 
				"\n\n\nGelieve niet te antwoorden op deze mail.");
			}
			
			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}