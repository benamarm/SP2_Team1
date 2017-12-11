package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.sql.ordering.antlr.Factory;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

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
	
public static void aanvragenGekeurd(ObservableList<Vaardigheid> vs) {
        
        Session session = Main.factory.getCurrentSession();
        session.beginTransaction();
        
        String gekeurd = (vs.get(0).isChecked() ? "goedgekeurd" : "afgekeurd");
        
        for(Vaardigheid v : vs) {            
            Log log = new Log();
            log.setBeschrijving("Aanvraag " + v.getVaardigheidId() + " van emp " + 
                    v.getPersoneel().getPersId() + " voor event " + v.getEvent().getEventId() + " " + gekeurd);
            log.setType("UPDATE");
            log.setUser(Main.sessionUser);            
            session.save(log);
        }        
        
        session.getTransaction().commit();
    }
	
	public static void opleidingBewerkt(Opleiding o)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving("Opleiding " + o.getOpleidingId() + " bewerkt: " + o.getNaam() + " - " + o.getBeschrijving());
		log.setType("UPDATE");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.getTransaction().commit();
		
		
	}
	
	public static void opleidingToegevoegd(Opleiding o)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving("Opleiding " + o.getOpleidingId() + " toegevoegd: " + o.getNaam() + " - " + o.getBeschrijving());
		log.setType("INSERT");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.getTransaction().commit();
		
	}
	
	public static void remAdmin(String loginemail)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving("Adminrechten van " + loginemail + " afgeschaft.");
		log.setType("UPDATE");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.getTransaction().commit();
	}
	
	public static void setAdmin(String loginemail)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Log log = new Log();
		log.setBeschrijving(loginemail + " gepromoveerd tot admin");
		log.setType("UPDATE");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.getTransaction().commit();
	}
	
	public static void changedPassword(String loginemail, boolean app)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		String typeUser;
		
		if(app = true)
			typeUser = "Appuser";
		else
			typeUser = "Webuser";
		
		Log log = new Log();
		log.setBeschrijving("Wachtwoord van " + loginemail + " veranderd ( " + typeUser + ")");
		log.setType("UPDATE");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.getTransaction().commit();
	}
	
	public static void userToegevoegd(User u)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		boolean isAdmin;
		String positie = u.getPositie();
		String admin = null;
		
		if(positie != null)
		{

			if(positie == "Admin")
			{
				isAdmin = true;
				admin = "JA";
			}
			else
			{
				isAdmin = false;
				admin = "NEE";
			}
		}
		
		Log log = new Log();
		log.setBeschrijving("User" + u.getLoginemail() + " toegevoegd (Admin: " + admin + ")");
		log.setType("INSERT");
		log.setUser(Main.sessionUser);
		session.save(log);
		
		session.save(log);
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