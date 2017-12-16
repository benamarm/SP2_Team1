package email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javafx.collections.ObservableList;
import logic.Event;
import logic.Vaardigheid;

public class Email {
	public static boolean sendPassword(String to, String password) {

		// properties for connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// make connection
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
			message.setText(
					"Beste\n\nU krijgt deze mail omdat uw wachtwoord is gewijzigd.\n"
					+ "Uw nieuw wachtwoord is: " + password 
					+ "\n\nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);

		} catch (MessagingException me) {
			me.printStackTrace();
			return false;
		}
		return true;
	}

	public static void createdUser(String to, String password) {

		// properties connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// make connection
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
			message.setText(
					"Beste\n\nU krijgt deze mail omdat er een account werd aangemaakt met uw gegevens.\n"
					+ "Uw inlog-gegevens zijn:\n"
					+ "Username: " + to 
					+ "\nWachtwoord: " + password
					+ "\n\nGelieve niet te antwoorden op deze mail.");

			// send the message
			Transport.send(message);

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public static void aanvraag(ObservableList<Vaardigheid> aanvragen) {

		// connection properties
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {

			for (Vaardigheid v : aanvragen) {
				// create message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(v.getPersoneel().getAccount().getLoginemail()));

				if (v.isChecked()) {
					message.setSubject("Goedkeuring opleiding");
					message.setText("Beste \n\nUw aanvraag is goedgekeurd voor volgend event:\nOpleiding: "
							+ v.getEvent().getOpleiding().getNaam() + "\nTrainer: " + v.getEvent().getNaamTrainer()
							+ "\nAdres: " + v.getEvent().getAdres().toString()
							+ "\nVan " + v.getEvent().getStringStartdatum() + " tot " + v.getEvent().getStringEinddatum()
							+ "\n\nGelieve niet te antwoorden op deze mail.");
				} else {
					message.setSubject("Afkeuring opleiding");
					message.setText("Beste \n\nUw aanvraag is afgekeurd voor volgend event:"
							+ "\nOpleiding: " + v.getEvent().getOpleiding().getNaam() 
							+ "\nTrainer: " + v.getEvent().getNaamTrainer()
							+ "\nAdres: " + v.getEvent().getAdres().toString() + "\nVan "
							+ v.getEvent().getStringStartdatum() + " tot " + v.getEvent().getStringEinddatum()
							+ "\n\nGelieve niet te antwoorden op deze mail.");
				}
				// send the message
				Transport.send(message);
			}

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	public static void eventAfgelast(Event e, String reden) {

		// properties connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			// create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			
			for(Vaardigheid v : e.getVaardigheden()) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(v.getPersoneel().getAccount().getLoginemail()));
			}
			
			message.setSubject("Event afgelast");
			message.setText("Beste \n\nHet volgende event is afgelast:"
					+ "\nOpleiding: "+ e.getOpleiding().getNaam() 
					+ "\nTrainer: " + e.getNaamTrainer()
					+ "\nAdres: " + e.getAdres().toString() 
					+ "\nVan " + e.getStringStartdatum() + " tot " + e.getStringEinddatum()
					+ "\nReden: " + reden
					+ "\n\nGelieve niet te antwoorden op deze mail.");
			
			// send the message
			Transport.send(message);

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	public static void eventGewijzigd(Event nieuw, Event oud) {

		// properties connection
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// make connection
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.hr.sp2@gmail.com", "+SP2Team1+");
			}
		});

		try {
			// create message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.hr.sp2@gmail.com"));
			
			for(Vaardigheid v : oud.getVaardigheden()) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(v.getPersoneel().getAccount().getLoginemail()));
			}
			
			message.setSubject("Event afgelast");
			message.setText("Beste \n\nEr zijn wijzigingen aangebracht aan een event."
					+ "\nNieuw event:\n"
					+ "\nOpleiding: "+ nieuw.getOpleiding().getNaam() 
					+ "\nTrainer: " + nieuw.getNaamTrainer()
					+ "\nAdres: " + nieuw.getAdres().toString() 
					+ "\nVan " + nieuw.getStringStartdatum() + " tot " + nieuw.getStringEinddatum()
					+ "\nOud event:\n"
					+ "\nOpleiding: "+ oud.getOpleiding().getNaam() 
					+ "\nTrainer: " + oud.getNaamTrainer()
					+ "\nAdres: " + oud.getAdres().toString() 
					+ "\nVan " + oud.getStringStartdatum() + " tot " + oud.getStringEinddatum()
					+ "\n\nGelieve niet te antwoorden op deze mail.");
			
			// send the message
			Transport.send(message);

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}