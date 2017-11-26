package database;

import org.hibernate.Session;
import org.hibernate.query.Query;

import gui.Main;

public class LogDAO {
	
	public static boolean loggedIn() {

		boolean EventHasBeenLogged = false;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(log_id, loginemail, tijdstip, type, beschrijving) VALUES(:id, :email, :when, :type, :besch)");
		q.setParameter("id", "NULL")
		 .setParameter("email", Main.sessionUser.getLoginemail())
		 .setParameter("when", "CURRENT_TIMESTAMP")
		 .setParameter("type", "AUTH")
		 .setParameter("besch", "Logged in: "+Main.sessionUser.getNaam()+" "+Main.sessionUser.getAchternaam());
		EventHasBeenLogged = true;
		
		session.getTransaction().commit();

		return EventHasBeenLogged;
	}
	
	
	public static boolean loggedOut() {

		boolean EventHasBeenLogged = false;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery(
				"INSERT INTO logs(log_id, loginemail, tijdstip, type, beschrijving) VALUES(:id, :email, :when, :type, :besch)");
		q.setParameter("id", "NULL")
		 .setParameter("email", Main.sessionUser.getLoginemail())
		 .setParameter("when", "CURRENT_TIMESTAMP")
		 .setParameter("type", "AUTH")
		 .setParameter("besch", "Logged OUT: "+Main.sessionUser.getNaam()+" "+Main.sessionUser.getAchternaam());
		EventHasBeenLogged = true;

		session.getTransaction().commit();

		return EventHasBeenLogged;
	}
}
