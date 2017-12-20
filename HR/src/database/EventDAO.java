package database;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
		for (Vaardigheid v : e.getVaardigheden()) {
			if (v.isChecked() == true)
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

	public static List<Event> getAll() {
		List<Event> events = new ArrayList<Event>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			Query<Event> q = session.createQuery("FROM Event ORDER BY startdatum", Event.class);
			events = q.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return events;
	}

	public static int getAantalDeelnemers(int id) {
		List<Event> events = new ArrayList<Event>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		int aantalDeelnames = 0;
		try {
			Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ORDER BY startdatum", Event.class);
			q.setParameter("id", id);
			events = q.getResultList();
			for (int i = 0; i < events.size(); i++) 
				aantalDeelnames = events.get(i).getAantalDeelnames() + aantalDeelnames;
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		System.out.println(aantalDeelnames);
		return aantalDeelnames;
	}

	public static int getOpl(int id) {
		List<Event> events = new ArrayList<Event>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ", Event.class);
			q.setParameter("id", id);
			events = q.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return events.size();
	}

	public static int getMaxAantalDeelnemers(int id)	{
		List<Event> events = new ArrayList<Event>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		int maxDeelnames = 0;
		try		{
			Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ", Event.class);
			q.setParameter("id", id);
			events = q.getResultList();
			for (int i = 0; i < events.size(); i++) {
				maxDeelnames = events.get(i).getMaxDeelnames() + maxDeelnames;
				System.out.println(maxDeelnames);
			}
			session.getTransaction().commit();
		} catch (Exception e){
			e.printStackTrace();
			return 0;
		}
		return maxDeelnames;
	}

}
