//Werkende test!!!!

package testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import logic.User;

public class UserTest {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		
		
		Query q = s.createNativeQuery(
				"INSERT INTO applogin(loginemail, naam, achternaam, positie, password) VALUES('jordi.hemelaer@student.ehb.be', 'Jordi', 'Hemelaer', 'HR', :p)");
		q.setParameter("p", User.generatePassword());
		q.executeUpdate();
		
		
		
		s.getTransaction().commit();		
		factory.close();
		
	}

}