package database;

import java.util.List;
import org.hibernate.query.Query;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import gui.Main;
import logic.Event;
import logic.Opleiding;
import logic.Vaardigheid;

public class EventDAO {

	public static int initialize(Event e) {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		session.refresh(e);
		Hibernate.initialize(e.getVaardigheden());
		
		int aantalChecked = 0;		
		for(Vaardigheid v : e.getVaardigheden()) {
			if(v.isChecked() == true)
				aantalChecked++;			
		}
		session.getTransaction().commit();
		
		return aantalChecked;
	}

	public static boolean save(Event e) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			session.save(e);
			session.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean update(Event e) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			session.update(e);
			session.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static List<Event> getKomendeEvents(Opleiding o) {
		List<Event> events = new ArrayList<Event>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = " + o.getOpleidingId()
					+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum", Event.class);
			events = q.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return events;
	}
}
