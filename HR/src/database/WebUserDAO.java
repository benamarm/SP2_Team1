package database;

import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import logic.Personeel;

public class WebUserDAO {

	@SuppressWarnings("rawtypes")
	public static boolean save(String loginemail, Personeel p, String password) {
		
		boolean added = false;
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createNativeQuery("INSERT INTO weblogin(loginemail, pers_id, password) VALUES(:l, :id, :p)");
		q.setParameter("l", loginemail).setParameter("id", p.getPersId()).setParameter("p", password);
		if (q.executeUpdate() == 1) {
			added = true;
		}
		
		session.getTransaction().commit();
		
		return added;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean updatePassword(String loginemail, String password) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createNativeQuery("UPDATE weblogin SET password = :pass WHERE loginemail = :email");
		q.setParameter("pass", password).setParameter("email", loginemail);
		boolean updated = false;

		if (q.executeUpdate() == 1)
			updated = true;

		session.getTransaction().commit();

		return updated;
	}
}
