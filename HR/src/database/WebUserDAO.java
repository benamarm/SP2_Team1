package database;

import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import logic.Personeel;
import logic.SHA512;
import logic.WebUser;

public class WebUserDAO {

	@SuppressWarnings("rawtypes")
	public static boolean save(String loginemail, Personeel p, String password) {
		
		boolean added = false;
		String salt = SHA512.generateSalt();
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();
		
		Query q = session.createNativeQuery("INSERT INTO weblogin(loginemail, pers_id, password, salt) VALUES(:l, :id, :p, :s)");
		q.setParameter("l", loginemail).setParameter("id", p.getPersId()).setParameter("p", SHA512.encrypt(password, salt)).setParameter("s", salt);
		if (q.executeUpdate() == 1) {
			added = true;
		}
		
		session.getTransaction().commit();
		
		return added;
	}
	
	public static void update(WebUser u) {
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();
		
		session.saveOrUpdate(u);
		
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean updatePassword(String loginemail, String password) {
		String salt = getSalt(loginemail);
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();

		Query q = session.createNativeQuery("UPDATE weblogin SET password = :pass WHERE loginemail = :email");
		q.setParameter("pass", SHA512.encrypt(password, salt)).setParameter("email", loginemail);
		boolean updated = false;

		if (q.executeUpdate() == 1)
			updated = true;

		session.getTransaction().commit();

		return updated;
	}
	
	@SuppressWarnings("rawtypes")
	public static String getSalt(String email) {
		Session session = Main.factory.getCurrentSession();
		if(session.getTransaction().isActive() == false) session.beginTransaction();
		
		Query q = session.createNativeQuery("SELECT salt FROM weblogin WHERE loginemail = :email");
		q.setParameter("email", email);
		String salt = (String) q.getSingleResult();
		
		return salt;
	}
	
}
