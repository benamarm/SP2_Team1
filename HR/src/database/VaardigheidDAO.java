package database;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import javafx.collections.ObservableList;
import logic.Event;
import logic.Vaardigheid;
import logic.odata;

public class VaardigheidDAO {

	@SuppressWarnings("unchecked")
	public static List<Vaardigheid> getAanvragen() {
		List<Vaardigheid> aanvragen = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query q = session.createQuery("FROM Vaardigheid where checked = NULL");
			aanvragen = (List<Vaardigheid>) q.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		for (Vaardigheid v : aanvragen)
			odata.setInfo(v.getPersoneel());

		return aanvragen;
	}

	public static boolean updateSingle(Vaardigheid v) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			session.update(v);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean updateObservables(ObservableList<Vaardigheid> vs) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {

			if (vs.get(0).isChecked())
				for (Vaardigheid v : vs)
					session.update(v);

			else {
				for (Vaardigheid v : vs) {
					Event e = session.get(Event.class, v.getEvent().getEventId());
					e.setAantalDeelnames(e.getAantalDeelnames() - 1);
					session.update(e);
					session.update(v);
				}
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
