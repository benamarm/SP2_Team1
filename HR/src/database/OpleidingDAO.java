package database;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import exceptions.BoekNietGevondenException;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.Boek;
import logic.GoogleBooks;
import logic.GoogleBooksExecutableQuery;
import logic.GoogleBooksQueryPrefix;
import logic.Opleiding;

public class OpleidingDAO {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ObservableList<Boek> getBoeken(Opleiding o) {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		ObservableList<Boek> observables = FXCollections.observableArrayList();
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();
		ArrayList<Boek> boeken = null;

		try {
			Query q = session.createNativeQuery("Select isbn from opleiding_boek where opleiding_id = :id");
			q.setParameter("id", o.getOpleidingId());
			List<String> list = q.getResultList();
			for (int i = 0; i < list.size(); i++) {
				GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.ISBN,
						list.get(i));
				boeken = GoogleBooks.executeQuery(jsonFactory, query);
				if (boeken != null) {
					for (Boek b : boeken)
						observables.add(b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		session.getTransaction().commit();
		return observables;
	}

	public static List<Opleiding> getAll() {
		List<Opleiding> opleidingen = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Opleiding> q = session.createQuery("FROM Opleiding", Opleiding.class);
			opleidingen = q.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return opleidingen;
	}

	public static boolean save(Opleiding o) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			session.save(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean update(Opleiding o) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			session.update(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Opleiding getById(int id) {
		Opleiding o = new Opleiding();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Opleiding> q = session.createQuery("FROM Opleiding WHERE opleidingId = :id", Opleiding.class);
			q.setParameter("id", id);
			o = q.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}

	public static ObservableList<Opleiding> getObservables() {
		ObservableList<Opleiding> observables = FXCollections.observableArrayList();

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			Query<Opleiding> q = session.createQuery("FROM Opleiding", Opleiding.class);
			List<Opleiding> opleidingen = q.getResultList();
			for (Opleiding o : opleidingen) {
				observables.add(o);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return observables;
	}

	@SuppressWarnings("rawtypes")
	public static boolean addBoekToOpleiding(Boek b, Opleiding o) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("De functie werd uitgevoerd.");
			Query q = session.createNativeQuery("INSERT INTO opleiding_boek (opleiding_id, ISBN) VALUES (:opl, :boek)");
			q.setParameter("opl", o.getOpleidingId()).setParameter("boek", b.getIsbn() == null ? " " : b.getIsbn())
					.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().commit();
			e.printStackTrace();
		}
		return false;

	}

	@SuppressWarnings("rawtypes")
	public static void removeBoekFromOpleiding(Boek b, Opleiding o) throws BoekNietGevondenException {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		if (b != null & o != null) {
			try {
				Query q = session.createNativeQuery("DELETE FROM opleiding_boek WHERE opleiding_id = :o AND isbn = :b");
				q.setParameter("o", o.getOpleidingId()).setParameter("b", b.getIsbn());
				q.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.getTransaction().commit();
	}

	public static Opleiding getByName(String name) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Opleiding o = null;
		try {
			Query<Opleiding> q = session.createQuery("FROM Opleiding WHERE naam = :name", Opleiding.class);
			q.setParameter("name", name);
			o = (Opleiding) q.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}

	/*
	 * public static void main(String[] args) { Opleiding o = new Opleiding();
	 * o.setOpleidingId(3); Boek b = new Boek(); b.setIsbn("9780123820211");
	 * addBoekToOpleiding(b, o);
	 * 
	 * ObservableList<Boek> list = getBoeken(o); for (Boek boek : list) {
	 * System.out.println(boek.getTitel()); } }
	 */

}
