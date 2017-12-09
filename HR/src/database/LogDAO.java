package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.sql.ordering.antlr.Factory;

import gui.Main;
import javafx.collections.ObservableList;
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
	
	
	public static void authenticate(Boolean isLogin) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving((isLogin? "In" : "Uit") + "gelogd.");
		log.setType("AUTH");
		log.setUser(Main.sessionUser);
		
		session.save(log);
		session.getTransaction().commit();
	}
	
	public static void aanvragenGekeurd(ObservableList<Vaardigheid> vs)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		boolean goedgekeurd;
		
		if(goedgekeurd = true)
		{
		
			for(Vaardigheid v : vs)
			{
				Log l = new Log();
				l.setBeschrijving("Aanvraag [vgid] van [persid] voor event [eventid] goedgekeurd");
				l.setType("UPDATE");
				session.save(l);
				l.setUser(Main.sessionUser);
			}
		}
		else
		{
			for(Vaardigheid v : vs)
			{
				Log l = new Log();
				l.setBeschrijving("Aanvraag [vgid] van [persid] voor event [eventid] afgekeurd");
				l.setType("UPDATE");
				session.save(l);
				l.setUser(Main.sessionUser);
			}
		}
		
		session.getTransaction().commit();
		}
	
	public static void opleidingBewerkt(Opleiding o)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("Opleiding [id] bewerkt: [oplnaam] - [oplbesch]");
		l.setType("UPDATE");
		session.save(l);
		l.setUser(Main.sessionUser);
		
		session.getTransaction().commit();
	}
	
	public static void opleidingToegevoegd(Opleiding o)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("Opleiding [id] toegevoegd: [oplnaam] - [oplbesch]");
		l.setType("INSERT");
		session.save(l);
		l.setUser(Main.sessionUser);
		
		session.getTransaction().commit();
	}
	
	public static void remAdmin(String loginemail)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("Adminrechten van [loginemail] afgeschaft");
		l.setType("UPDATE");
		session.save(l);
		l.setUser(Main.sessionUser);
		
		session.getTransaction().commit();
	}
	
	public static void setAdmin(String loginemail)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("[loginemail] gepromoveerd tot admin");
		l.setType("UPDATE");
		session.save(l);
		l.setUser(Main.sessionUser);
		
		session.getTransaction().commit();
	}
	
	public static void changedPassword(String loginemail, boolean app)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("Wachtwoord van [loginemail] veranderd (appuser/webuser)");
		l.setType("UPDATE");
		session.save(l);
		l.setUser(Main.sessionUser);
		
		session.getTransaction().commit();
	}
	
	public static void userToegevoegd(User u)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log l = new Log();
		l.setBeschrijving("User [loginemail] toegevoegd (admin: JA/NEE)");
		l.setType("INSERT");
		session.save(l);
		l.setUser(Main.sessionUser);
	}
	
	}


// met de volgende code heb ik logAuthenticationEvent() getest en goedgekeurd.
// er zijn nog imports die moeten verwijderd worden indien deze code niet meer nodig is om te testen

/*
public static void authenticate(User sessionUser, Boolean isLogin) {

		SessionFactory factory;
		factory = new Configuration().configure().addAnnotatedClass(Adres.class).addAnnotatedClass(Boek.class)
				.addAnnotatedClass(Event.class).addAnnotatedClass(Log.class).addAnnotatedClass(Opleiding.class)
				.addAnnotatedClass(Personeel.class).addAnnotatedClass(User.class).addAnnotatedClass(Vaardigheid.class)
				.addAnnotatedClass(Vraag.class).addAnnotatedClass(WebUser.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving("Logged " + (isLogin? "in.": "out."));
		log.setType("AUTH");
		log.setUser(sessionUser);
		
		session.save(log);
		session.getTransaction().commit();
	}*/


	
	/*public static void main(String[] args) {
		
		User u = new User();
		u.setAchternaam("Benamar");
		u.setLoginemail("mohben@hr.com");
		u.setNaam("Mohammed");
		u.setPositie("ADMIN");
		
		authenticate(u, false);
		System.out.println("Logged in.\n");
		
	}}*/