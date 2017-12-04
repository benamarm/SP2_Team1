package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import javafx.collections.ObservableList;
import logic.Vaardigheid;

public class VaardigheidDAO {

	@SuppressWarnings("unchecked")
	public static List<Vaardigheid> getUnchecked() {
		List<Vaardigheid> teVerwervenVaardigheden = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM Vaardigheid where checked = NULL");
			teVerwervenVaardigheden = (List<Vaardigheid>) q.list();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return teVerwervenVaardigheden;
	}
	
	public static boolean updateSingle(Vaardigheid v) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			session.update(v);
			session.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	public static boolean updateObservables(ObservableList<Vaardigheid> v) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			for(Vaardigheid vaard: v) session.update(vaard);
			session.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		
		return true;
	}

}
