package database;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.Personeel;
import logic.odata;

public class PersoneelDAO {

	public static List<Personeel> getAllNoAccount() {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("FROM Personeel");	
		List<Personeel> account = q.getResultList();
		session.getTransaction().commit();
		
		List<Personeel> noAccount = new ArrayList<Personeel>();
		List<Personeel> all = odata.getAll();
	
		for(Personeel p : all) {
			boolean gevonden = false;
			for(Personeel p2 : account) {
				if(p2.getPersId() == p.getPersId()) {
					gevonden = true;
					break;
				}
			}
			if(!gevonden) 
				noAccount.add(p);
		}		
		
		return noAccount;
	}
	
	public static List<Personeel> getAllAccount() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("FROM Personeel");	
		List<Personeel> ps = q.getResultList();		
		session.getTransaction().commit();
		
		for(Personeel p : ps) 
			odata.setInfo(p);
		
		return ps;
	}
	
	public static List<Personeel> getDeelnemers(int eventId) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("SELECT personeel FROM Vaardigheid WHERE event_id = " + eventId + " AND checked = TRUE");
		List<Personeel> ps = q.getResultList();		
		session.getTransaction().commit();
		
		for(Personeel p : ps)
			odata.setInfo(p);		
		
		return ps;
	}

}
