package database;

import org.hibernate.Session;
import org.hibernate.query.Query;

import gui.Main;
import logic.User;

public class LogDAO {	
	
	public static boolean loggedIn(User u) {

		boolean EventHasBeenLogged = false;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(log_id, loginemail, tijdstip, type, beschrijving) VALUES(:id, :email, :when, :type, :besch)");
		q.setParameter("id", "NULL")
		 .setParameter("email", u.getLoginemail())
		 .setParameter("when", "CURRENT_TIMESTAMP")
		 .setParameter("type", "AUTH")
		 .setParameter("besch", "Logged in: "+u.getNaam()+" "+u.getAchternaam());
		EventHasBeenLogged = true;
		
		session.getTransaction().commit();

		return EventHasBeenLogged;
	}
	
	
	public static boolean loggedOut(User u) {

		boolean EventHasBeenLogged = false;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(log_id, loginemail, tijdstip, type, beschrijving) VALUES(:id, :email, :when, :type, :besch)");
		q.setParameter("id", "NULL")
		 .setParameter("email", u.getLoginemail())
		 .setParameter("when", "CURRENT_TIMESTAMP")
		 .setParameter("type", "AUTH")
		 .setParameter("besch", "Logged OUT: "+u.getNaam()+" "+u.getAchternaam());
		EventHasBeenLogged = true;

		session.getTransaction().commit();

		return EventHasBeenLogged;
	}
}
