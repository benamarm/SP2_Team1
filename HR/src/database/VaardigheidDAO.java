package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import javafx.collections.ObservableList;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;
import logic.Vaardigheid;
import logic.odata;

public class VaardigheidDAO {

	public static List<Vaardigheid> getAanvragen() {
		List<Vaardigheid> aanvragen = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Vaardigheid> q = session.createQuery("FROM Vaardigheid WHERE checked = NULL", Vaardigheid.class);
			aanvragen = q.getResultList();
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

	public static List<Vaardigheid> getKomende(Personeel p, Opleiding o) {
		List<Vaardigheid> aanvragen = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Vaardigheid> q = session.createQuery(
					"FROM Vaardigheid WHERE personeel.persId = :p AND event.opleiding.opleidingId = :o AND event.startdatum > CURRENT_DATE() ORDER BY event.startdatum",
					Vaardigheid.class);
			q.setParameter("p", p.getPersId()).setParameter("o", o.getOpleidingId());
			aanvragen = q.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return aanvragen;
	}

	public static List<Vaardigheid> getVoorbije(Personeel p, Opleiding o) {
		List<Vaardigheid> aanvragen = null;

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			Query<Vaardigheid> q = session.createQuery(
					"FROM Vaardigheid WHERE checked = TRUE AND personeel.persId = :p AND event.opleiding.opleidingId = :o AND event.einddatum < CURRENT_DATE() ORDER BY event.startdatum",
					Vaardigheid.class);
			q.setParameter("p", p.getPersId()).setParameter("o", o.getOpleidingId());
			aanvragen = q.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return aanvragen;
	}

	public static boolean uploadCertificaat(Vaardigheid v, File path) {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			FileInputStream fin = new FileInputStream(path);
			Blob certificaat = Hibernate.getLobCreator(session).createBlob(fin, path.length());

			v.setCertificaat(certificaat);
			v.setExtensie(path.getName().substring(path.getName().lastIndexOf(".") + 1));
			session.update(v);
			session.getTransaction().commit();

			certificaat.free();

		} catch (Exception e) {
			if (session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean downloadCertificaat(Vaardigheid v, File path) {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		odata.setInfo(v.getPersoneel());

		try {
			Blob certificaat = v.getCertificaat();
			byte[] bytes = certificaat.getBytes(1, (int) certificaat.length());
			FileOutputStream fout = new FileOutputStream(path.getAbsolutePath() + "\\" + v.getPersoneel().getVolleNaam()
					+ "_" + v.getEvent().getEventId() + "." + v.getExtensie());
			fout.write(bytes);
			fout.close();

			session.getTransaction().commit();

		} catch (Exception e) {
			if (session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
