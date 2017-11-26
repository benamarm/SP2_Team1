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
	*  aan de methode of het een login is of niet zodat hij dat doorgeeft aan de database*/
	
	
	public static boolean authenticate(Boolean isLogin) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving("Logged " + (isLogin? "in: ": "out: ") +Main.sessionUser.getNaam()+" "+Main.sessionUser.getAchternaam());
		log.setType("AUTH");
		log.setUser(Main.sessionUser);
		
		try {
			session.save(log);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}



// met de volgende code heb ik logAuthenticationEvent() getest en goedgekeurd.
// er zijn nog imports die moeten verwijderd worden indien deze code niet meer nodig is om te testen

/*
public static boolean authenticate(User sessionUser, Boolean isLogin) {

		SessionFactory factory;
		factory = new Configuration().configure().addAnnotatedClass(Adres.class).addAnnotatedClass(Boek.class)
				.addAnnotatedClass(Event.class).addAnnotatedClass(Log.class).addAnnotatedClass(Opleiding.class)
				.addAnnotatedClass(Personeel.class).addAnnotatedClass(User.class).addAnnotatedClass(Vaardigheid.class)
				.addAnnotatedClass(Vraag.class).addAnnotatedClass(WebUser.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		Log log = new Log();
		log.setBeschrijving("Logged " + (isLogin? "in: ": "out: ") +sessionUser.getNaam()+" "+sessionUser.getAchternaam());
		log.setType("AUTH");
		log.setUser(sessionUser);
		
		try {
			session.save(log);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		
		User u = new User();
		u.setAchternaam("Benamar");
		u.setLoginemail("mohben@hr.com");
		u.setNaam("Mohammed");
		u.setPositie("ADMIN");
		
		if(authenticate(u, false)) System.out.println("Logged in.\n");
		
	}*/