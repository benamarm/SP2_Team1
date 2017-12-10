package database;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import gui.Main;
import logic.Event;

public class EventDAO {
	
	public static void initialize(Event e) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		session.update(e);		
		Hibernate.initialize(e.getVaardigheden());
		
		session.getTransaction().commit();

	}	
}
