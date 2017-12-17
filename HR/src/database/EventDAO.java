package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import gui.Main;
import javafx.collections.ObservableList;
import logic.Boek;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;

public class EventDAO {
	private Session currentSession;
	public static void initialize(Event e) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		session.update(e);		
		Hibernate.initialize(e.getVaardigheden());
		
		session.getTransaction().commit();

	}
	public static List<Event> getAll(){
		List<Event> Events = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM events");
			Events = (List<Event>) q.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return Events;
	}
	public static Event getById(int id) {
		Event e = new Event();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			Query q = session.createQuery("FROM events where event_id = :id");
			q.setParameter("id", id);
			e = (Event) q.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return e;
	}
	public static void main(String[] args) {
	
		
	}
	
	private Session getCurrentSession() {
		// TODO Auto-generated method stub
		return currentSession;
	}
}
