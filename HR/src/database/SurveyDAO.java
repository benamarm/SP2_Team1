package database;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import logic.Opleiding;
import logic.Publicatie;
import logic.Survey;

public class SurveyDAO {

	public static boolean save(Survey s) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {			
			session.save(s);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean save(Publicatie p) {
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
	
	public static boolean update(Publicatie p) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try {			
			session.update(p);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static List<Survey> getInactief(Opleiding o) {		
		List<Survey> surveys = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Survey> q = session.createNativeQuery("SELECT * FROM surveys s WHERE opleiding_id = "
					+ o.getOpleidingId()
					+ " AND NOT EXISTS (SELECT 1 FROM surveyPublicaties p WHERE p.survey_id = s.survey_id AND tot > SYSDATE() AND actief = 1)", Survey.class);
			surveys = q.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return surveys;
	}
	
	public static List<Publicatie> getActief(Opleiding o) {		
		List<Publicatie> publicaties = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Publicatie> q = session.createQuery(
					"FROM Publicatie p WHERE p.publicatieId = (SELECT MAX(p2.publicatieId) from Publicatie p2 WHERE p2.survey.surveyId = p.survey.surveyId AND p2.survey.opleiding.opleidingId = "
							+ o.getOpleidingId() + ") AND p.actief = TRUE ORDER BY tot", Publicatie.class);
			publicaties = q.getResultList();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return publicaties;
	}
}
