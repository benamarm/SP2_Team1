package testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;

import org.junit.*;
import static org.junit.Assert.*;



import logic.Adres;
import logic.Boek;
import logic.Opleiding;
import logic.Personeel;
import logic.Vaardigheid;
import logic.Vraag;

public class AdresDatabaseTest {
	
	SessionFactory factory = new Configuration().configure().addAnnotatedClass(Adres.class).addAnnotatedClass(logic.Event.class).addAnnotatedClass(Opleiding.class).addAnnotatedClass(Boek.class).addAnnotatedClass(Vaardigheid.class).addAnnotatedClass(Personeel.class).addAnnotatedClass(Vraag.class).addAnnotatedClass(logic.WebUser.class).addAnnotatedClass(logic.Personeel.class).buildSessionFactory();
	Session s = factory.getCurrentSession();
	
	
	@Before
	
	public void before()
	{
		s.beginTransaction();
	}
	
	@Test
	
	public void testConstructorEnDatabaseconnectie()
	{
		Adres adres1 = new Adres();
		adres1.setStraat("Hibernateteststraat");
		adres1.setNummer(1);
		adres1.setPostcode(0000);
		adres1.setLand("Testland");
		
		s.save(adres1);
		
		assertNotNull(adres1.getAdresId());
		assertEquals("Hibernateteststraat",adres1.getStraat());
		assertEquals(1,adres1.getNummer());
		assertEquals(0000,adres1.getPostcode());
		assertEquals("Testland",adres1.getLand());
	
	}
	
	@After
	
	public void after()
	{
		s.close();
		factory.close();
	}

}
