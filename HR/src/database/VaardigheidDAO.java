package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

//import antlr.debug.Event;
import gui.Main;
import logic.Personeel;
import logic.Vaardigheid;

public class VaardigheidDAO {

	@SuppressWarnings("unchecked")
	public static List<Vaardigheid>  getUnchecked(Personeel p){
		List<Vaardigheid> teVerwervenVaardigheden = null;
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		 try {
			   Query q = session.createQuery("FROM Vaardigheid where checked = 0");
			    teVerwervenVaardigheden = (List<Vaardigheid>) q.list();
			  
		   } catch(Exception e) {
			   e.printStackTrace();
			   return null;
		   }
		
		return teVerwervenVaardigheden;
	}
	

}
