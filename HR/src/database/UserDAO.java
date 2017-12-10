package database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import exceptions.UserBestaatReedsException;
import exceptions.UserOnbestaandException;
import gui.Main;
import logic.User;
import logic.Vaardigheid;

public class UserDAO {
	
	
	public static List<User> getAllExceptSession(){
		List<User> users = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM User where checked = 0");
			users = (List<User>) q.list();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}
	
	
	
	public static boolean updatePassword(String loginemail, String password) throws UserOnbestaandException{
				Session session = Main.factory.getCurrentSession();
				session.beginTransaction();
				
				if (session.get(User.class, loginemail) == null) {
					session.getTransaction().commit();
					throw new UserOnbestaandException();
				}
				
				Query q = session.createNativeQuery("UPDATE applogin set password = :pass where loginemail = :email");
				q.setParameter("pass", password).setParameter("email", loginemail);
				boolean updateSucceeded = true;
				if(q.executeUpdate() == 0) updateSucceeded = false;
				session.getTransaction().commit();

				return updateSucceeded;
	}

	public static boolean save(User u) throws UserBestaatReedsException {

		boolean added = false;

		// Onderstaande twee statements zal je nodig hebben voor elke DAO-methode!
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		// Check of user reeds bestaat en throw exception
		if (session.get(User.class, u.getLoginemail()) != null) {
			session.getTransaction().commit();
			throw new UserBestaatReedsException();
		}

		// PreparedStatement, je mag ook met ? werken maar ik vind :naamParameter gemakkelijker :D
		Query q = session.createNativeQuery(
				"INSERT INTO applogin(loginemail, naam, achternaam, positie, password) VALUES(:l, :n, :a, :po, :pa)");
		q.setParameter("l", u.getLoginemail()).setParameter("n", u.getNaam()).setParameter("a", u.getAchternaam())
				.setParameter("po", u.getPositie()).setParameter("pa", User.generatePassword());
		if(q.executeUpdate() > 0) {
			added = true;
		}		

		// Als laatste moet je committen, ook al voer je geen CREATE/INSERT/UPDATE/DELETE uit
		session.getTransaction().commit();

		return added;
	}

	public static boolean update(User u) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			
			session.update(u);	// session.update(Object) is een speciale Hibernate methode
								// Het doet een UPDATE op de tabel gemapt door de klasse, het moet wel een bestaande rij zijn
								// Je hebt ook INSERT met session.save(Object), DELETE session.delete(Object) en MERGE met session.saveOrUpdate(Object)
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	// Deze methode checkt of er een User bestaat met de ingegeven credentials en stuurt de User terug (null indien niet gevonden)
	public static User connect(String login, String password) {

		User u = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		// createNativeQuery: standaard SQL-taal van je database, gebruiken voor speciale INSERT als session.save() niet voldoet
		// createQuery: HQL, zoek maar op internet ik heb hier niet genoeg plaats xD (je kan met HQL geen INSERT doen)
		Query q = session.createQuery("FROM User WHERE loginemail = :l AND password = :p");
		q.setParameter("l", login).setParameter("p", password);
		List<User> users = q.getResultList();
		if (users.size() > 0)
			u = session.get(User.class, login); // session.get(<naamKlasse>.class, <ID>) is nog een speciale Hibernate methode
												// Het return het object (User) met die ID (login), of null als het niet bestaat

		session.getTransaction().commit();

		return u;
	}

}
