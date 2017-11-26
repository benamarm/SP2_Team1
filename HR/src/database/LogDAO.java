package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.sql.ordering.antlr.Factory;

import gui.Main;
import logic.Adres;
import logic.Boek;
import logic.Event;
import logic.Log;
import logic.Opleiding;
import logic.Personeel;
import logic.User;
import logic.Vaardigheid;
import logic.Vraag;
import logic.WebUser;

public class LogDAO {
	
	/* de isLogin parameter laat weten 
	*  aan de methode of het een login is of niet zodat hij dat doorgeeft aan de database
	*/
	
	public static boolean logAuthenticationEvent(Boolean isLogin) {

		boolean eventHasBeenLogged = false;
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(loginemail, type, beschrijving) VALUES(:email, :type, :besch)");
		 q.setParameter("email", Main.sessionUser.getLoginemail())
		 .setParameter("type", "AUTH")
		 //onze query zal afhankelijk van onze parameter lichtjes aangepast worden on the fly
		 .setParameter("besch", "Logged " + (isLogin? "in: ": "out: ") +Main.sessionUser.getNaam()+" "+Main.sessionUser.getAchternaam());
		 
		if(q.executeUpdate() > 0) eventHasBeenLogged = true;
		
		session.getTransaction().commit();

		return eventHasBeenLogged;
	}

}



// met de volgende code heb ik logAuthenticationEvent() getest en goedgekeurd.
// er zijn nog imports die moeten verwijderd worden indien deze code niet meer nodig is om te testen

/*
 * public static boolean logAuthenticationEvent(User sessionUser, Boolean isLogin) {

		boolean eventHasBeenLogged = false;
		SessionFactory factory;
		factory = new Configuration().configure().addAnnotatedClass(Adres.class).addAnnotatedClass(Boek.class)
				.addAnnotatedClass(Event.class).addAnnotatedClass(Log.class).addAnnotatedClass(Opleiding.class)
				.addAnnotatedClass(Personeel.class).addAnnotatedClass(User.class).addAnnotatedClass(Vaardigheid.class)
				.addAnnotatedClass(Vraag.class).addAnnotatedClass(WebUser.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(loginemail, type, beschrijving) VALUES(:email, :type, :besch)");
		 q.setParameter("email", sessionUser.getLoginemail())
		 .setParameter("type", "AUTH")
		 //onze query zal afhankelijk van onze parameter lichtjes aangepast worden on the fly
		 .setParameter("besch", "Logged " + (isLogin? "in: ": "out: ") +sessionUser.getNaam()+" "+sessionUser.getAchternaam());
		q.executeUpdate();
		eventHasBeenLogged = true;
		
		session.getTransaction().commit();
		factory.close();

		return eventHasBeenLogged;
	}
	
	
	
	public static void main(String[] args) {
		
		User u = new User();
		u.setAchternaam("Benamar");
		u.setLoginemail("mohben@hr.com");
		u.setNaam("Mohammed");
		u.setPositie("ADMIN");
		
		if(logAuthenticationEvent(u, true)) System.out.println("Logged in.\n");
		
	}*/