package database;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import exceptions.UserBestaatReedsException;
import exceptions.UserOnbestaandException;
import gui.Main;
import logic.SHA512;
import logic.User;

public class UserDAO {

	public static List<User> getAllExceptSession() {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query<User> q = session.createQuery("FROM User WHERE loginemail != :l", User.class);
		q.setParameter("l", Main.sessionUser.getLoginemail());

		List<User> users = q.getResultList();

		session.getTransaction().commit();

		return users;
	}

	@SuppressWarnings("rawtypes")
	public static boolean save(User u, String password) throws UserBestaatReedsException {
		String salt = SHA512.generateSalt();
		boolean added = false;

		// Onderstaande twee statements zal je nodig hebben voor elke DAO-methode!
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();

		// Check of user reeds bestaat en throw exception
		if (session.get(User.class, u.getLoginemail()) != null) {
			session.getTransaction().commit();
			throw new UserBestaatReedsException();
		}

		// PreparedStatement, je mag ook met ? werken maar ik vind :naamParameter
		// gemakkelijker :D
		Query q = session.createNativeQuery(
				"INSERT INTO applogin(loginemail, naam, achternaam, positie, password, salt) VALUES(:l, :n, :a, :po, :pa, :s)");
		q.setParameter("l", u.getLoginemail()).setParameter("n", u.getNaam()).setParameter("a", u.getAchternaam())
				.setParameter("po", u.getPositie()).setParameter("pa", SHA512.encrypt(password, salt)).setParameter("s", salt);
		if (q.executeUpdate() == 1) {
			added = true;
		}

		// Als laatste moet je committen, ook al voer je geen
		// CREATE/INSERT/UPDATE/DELETE uit
		session.getTransaction().commit();

		return added;
	}

	public static boolean update(User u) {

		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();

		try {

			session.update(u); // session.update(Object) is een speciale Hibernate methode
								// Het doet een UPDATE op de tabel gemapt door de klasse, het moet wel een
								// bestaande rij zijn
								// Je hebt ook INSERT met session.save(Object), DELETE session.delete(Object) en
								// MERGE met session.saveOrUpdate(Object)
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@SuppressWarnings("rawtypes")
	public static boolean updatePassword(String loginemail, String password) throws UserOnbestaandException {
		String salt = getSalt(loginemail);
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();

		if (session.get(User.class, loginemail) == null) {
			session.getTransaction().commit();
			throw new UserOnbestaandException();
		}

		Query q = session.createNativeQuery("UPDATE applogin SET password = :pass WHERE loginemail = :email");
		q.setParameter("pass", SHA512.encrypt(password, salt)).setParameter("email", loginemail);
		boolean updated = false;

		if (q.executeUpdate() == 1)
			updated = true;

		session.getTransaction().commit();

		return updated;
	}

	// Deze methode checkt of er een User bestaat met de ingegeven credentials en
	// stuurt de User terug (null indien niet gevonden)
	public static User connect(String login, String password) {

		User u = null;
		String salt = getSalt(login);

		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();

		// createNativeQuery: standaard SQL-taal van je database, gebruiken voor
		// speciale INSERT als session.save() niet voldoet
		// createQuery: HQL, zoek maar op internet ik heb hier niet genoeg plaats xD (je
		// kan met HQL geen INSERT doen)
		Query<User> q = session.createQuery("FROM User WHERE loginemail = :l AND password = :p", User.class);
		q.setParameter("l", login).setParameter("p", SHA512.encrypt(password, salt));
		List<User> users = q.getResultList();
		if (users.size() > 0)
			u = session.get(User.class, login); // session.get(<naamKlasse>.class, <ID>) is nog een speciale Hibernate
												// methode
												// Het return het object (User) met die ID (login), of null als het niet
												// bestaat

		session.getTransaction().commit();

		return u;
	}
	
	public static String getSalt(String email) {
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();
		
		Query q = session.createNativeQuery("Select salt from applogin where loginemail = :email");
		q.setParameter("email", email);
		String salt = (String) q.getSingleResult();
		
		return salt;
	}
	
//	//deze methode was om de inital users een salt te geven
//	public static void setSalt(String email) {
//		Session session = Main.factory.getCurrentSession();
//		if(session.getTransaction().isActive() == false) session.beginTransaction();
//		Query q = session.createNativeQuery("UPDATE applogin SET salt = :salt WHERE loginemail = :email");
//		q.setParameter("salt", SHA512.generateSalt()).setParameter("email", email);
//		boolean updated = false;
//
//		if (q.executeUpdate() == 1)
//			updated = true;
//
//		session.getTransaction().commit();
//	}

}
