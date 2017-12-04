//Werkende test!!!!

package testing;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import logic.Adres;
import logic.Boek;
import logic.Event;
import logic.Opleiding;
import logic.Vraag;
//import java.util.Date;


public class EventTest {
	
	public static void main(String[] args) throws ParseException {
		
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Event.class).addAnnotatedClass(Adres.class).addAnnotatedClass(Opleiding.class).addAnnotatedClass(Boek.class).addAnnotatedClass(Vraag.class).buildSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //Bron: https://stackoverflow.com/questions/28335005/how-do-i-create-a-java-sql-date-variable
		java.sql.Date startdatum = new java.sql.Date(df.parse("03-12-2017").getTime());
		java.sql.Date einddatum = new java.sql.Date(df.parse("04-12-2017").getTime());
		
			Opleiding o = new Opleiding();
			
			o.setNaam("Opleiding naam");
			o.setBeschrijving("Beschrijving opleiding");
			
			Adres a = new Adres();
			
			a.setStraat("Teststraat");
			a.setNummer(1234);
			a.setPostcode(1000);
			a.setLand("Belgie");
		
		Event e = new Event();
		
		e.setOpleiding(o);
		e.setAdres(a);
		e.setNaamTrainer("Jordi");
		e.setStartdatum(startdatum);
		e.setEinddatum(einddatum);
		e.setAantalDeelnames(100);
		e.setMaxDeelnames(1600);
		

		
		s.save(e);
		
		s.getTransaction().commit();		
		factory.close();
	}

}
