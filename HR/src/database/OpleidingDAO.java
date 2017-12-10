package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import exceptions.UserBestaatReedsException;
import gui.Main;
import javafx.collections.ObservableList;
import logic.Boek;
import logic.Opleiding;
import logic.User;

public class OpleidingDAO {
	
	public static List<Opleiding> getAll(){
		List<Opleiding> opleidingen = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM Opleiding");
			opleidingen = (List<Opleiding>) q.list();
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
			} catch(Exception e) {
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
			} catch(Exception e) {
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
			Query q = session.createQuery("FROM Opleiding where opleidingId = :id");
			q.setParameter("id", id);
			o = (Opleiding) q.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}
	
	public static ObservableList<Opleiding> getObservables(){
		ObservableList<Opleiding>  opleidingen = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM Opleiding");
			opleidingen = (ObservableList<Opleiding>) q.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return opleidingen;
	}
	
	public static void addBoekToOpleiding(Boek b, Opleiding o) {
		// Onderstaande twee statements zal je nodig hebben voor elke DAO-methode!
				Session session = Main.factory.getCurrentSession();
				session.beginTransaction();
				
				if(b != null & o != null) {
					try {
						Query q = session.createNativeQuery(
								"INSERT INTO opleiding_boek(opleiding_id, boek_id) VALUES(:o, :b)");
						q.setParameter("o", o.getNaam()).setParameter("b", b.getIsbn());	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				session.getTransaction().commit();
	}
	
	
	public static Opleiding getByName(String name){
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Opleiding o = null;
		try {
			
			Query q = session.createQuery("FROM Opleiding where naam = :name");
			q.setParameter("name", name);
			o = (Opleiding) q.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}
}
