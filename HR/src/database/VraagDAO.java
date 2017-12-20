package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import javafx.collections.ObservableList;
import logic.Survey;
import logic.Vraag;

public class VraagDAO {

	public static boolean save(Vraag v) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {			
			session.save(v);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean update(Vraag nieuw, Vraag oud) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {			
			session.save(nieuw);
			session.update(oud);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean delete(Vraag toDelete, ObservableList<Vraag> vragen) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			
			for(Vraag v : vragen) {
				if(v.getInx() == toDelete.getInx()) {
					
					int index = v.getInx();
					v.setInx(0);
					session.update(v);
					
					for(int i = index; i < vragen.size(); i++) {
						vragen.get(i).setInx(vragen.get(i).getInx() - 1);
						session.update(vragen.get(i));
					}
					break;
				}
			}			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static List<Vraag> getInactief(Survey s) {		
		List<Vraag> vragen = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Vraag> q = session.createQuery("FROM Vraag WHERE survey_id = " + s.getSurveyId()
					+ " AND inx > 0 ORDER BY inx", Vraag.class);
			vragen = q.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return vragen;
	}
	
	public static List<Vraag> getActief(Survey s) {		
		List<Vraag> vragen = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Vraag> q = session.createQuery("FROM Vraag WHERE survey_id = "
					+ s.getSurveyId() + " AND inx > 0 ORDER BY inx", Vraag.class);
			vragen = q.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return vragen;
	}
}
