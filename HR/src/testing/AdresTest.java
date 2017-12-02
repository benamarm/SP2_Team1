//Werkende test

package testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import logic.Adres;

public class AdresTest {

	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Adres.class).buildSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		
		Adres a = new Adres();
		a.setLand("test");
		a.setNummer(5);
		a.setPostcode(1000);
		a.setStraat("test");
		
		s.save(a);
		
		s.getTransaction().commit();		
		factory.close();

		
	}
}