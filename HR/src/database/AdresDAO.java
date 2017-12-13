package database;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import gui.Main;
import logic.Adres;

public class AdresDAO {
	
	public static boolean save(Adres a)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		try
		{
			session.save(a);
			session.getTransaction().commit();
		} catch(Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public static List<Adres> getAllOrdered()
	{
		List<Adres> adressen = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try
		{
			Query q = session.createQuery("FROM Adres ORDER BY straat");
			adressen = (List<Adres>) q.getResultList();
			session.getTransaction().commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			
			
			return null;
		}
		
		return adressen;
	}

}
