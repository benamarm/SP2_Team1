package database;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import logic.Personeel;
import logic.odata;

public class PersoneelDAO {

	public static List<Personeel> getAllNoAccount() {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query<Personeel> q = session.createQuery("FROM Personeel", Personeel.class);
		List<Personeel> account = q.getResultList();
		session.getTransaction().commit();

		List<Personeel> noAccount = new ArrayList<Personeel>();
		List<Personeel> all = odata.getAll();

		for (Personeel p : all) {
			boolean gevonden = false;
			for (Personeel p2 : account) {
				if (p2.getPersId() == p.getPersId()) {
					gevonden = true;
					break;
				}
			}
			if (!gevonden)
				noAccount.add(p);
		}

		return noAccount;
	}

	public static List<Personeel> getAllAccount() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query<Personeel> q = session.createQuery("FROM Personeel", Personeel.class);
		List<Personeel> ps = q.getResultList();
		session.getTransaction().commit();

		for (Personeel p : ps)
			odata.setInfo(p);

		return ps;
	}

	public static List<Personeel> getDeelnemers(int eventId) {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query<Personeel> q = session.createQuery(
				"SELECT personeel FROM Vaardigheid WHERE event_id = " + eventId + " AND checked = TRUE",
				Personeel.class);
		List<Personeel> ps = q.getResultList();
		session.getTransaction().commit();

		for (Personeel p : ps)
			odata.setInfo(p);

		return ps;
	}
	
	public static boolean save(Personeel p) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			session.save(p);
			session.getTransaction().commit();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
