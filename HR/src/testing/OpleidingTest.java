//Werkende test!!!!

package testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import logic.Event;
import logic.Adres;
import logic.Boek;
import logic.Opleiding;
import logic.Vraag;

public class OpleidingTest {
	
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Opleiding.class).addAnnotatedClass(Boek.class).addAnnotatedClass(Event.class).addAnnotatedClass(Adres.class).addAnnotatedClass(Vraag.class).buildSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		
		Opleiding o = new Opleiding();
		
		o.setNaam("Opleiding test 1");
		o.setBeschrijving("Beschrijving opleiding test 1");
		o.setOpleidingId(1);
		
		s.save(o);
		
		s.getTransaction().commit();		
		factory.close();
		
	}

}