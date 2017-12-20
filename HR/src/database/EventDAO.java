package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import gui.Main;
import javafx.scene.chart.PieChart.Data;
import logic.Event;
import logic.Opleiding;

public class EventDAO {
	
	public static void initialize(Event e) {
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		session.refresh(e);		
		Hibernate.initialize(e.getVaardigheden());
		
		session.getTransaction().commit();

	}	
	public static List<Event> getAll()

    {

        List<Event> events = new ArrayList<Event>();
        Session session = Main.factory.getCurrentSession();
        session.beginTransaction();
        try
        {

            Query<Event> q = session.createQuery("FROM Event ORDER BY startdatum");

            events = q.getResultList();

            session.getTransaction().commit();

        } catch (Exception e)

        {
            e.printStackTrace();

            return null;
        }
        return events;

    }

    

    public static int getAantalDeelnemers(int id)

    {
    	List<Event> events = new ArrayList<Event>();
        Session session = Main.factory.getCurrentSession();

        session.beginTransaction();

        

        int aantalDeelnames = 0;

        

    try

            {

                Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ORDER BY startdatum");

                q.setParameter("id", id);

                events = q.getResultList();

                for (int i = 0; i < events.size(); i++) {
                    
                    
                    aantalDeelnames = events.get(i).getAantalDeelnames()+aantalDeelnames;
                    
                    } 

                

                session.getTransaction().commit();

            } catch (Exception e)

            {

                e.printStackTrace();

                

                return 0;

            }

            
    System.out.println(aantalDeelnames);
            return aantalDeelnames;

        }

    public static int getOpl(int id)

    {
    	List<Event> events = new ArrayList<Event>();
        Session session = Main.factory.getCurrentSession();

        session.beginTransaction();

        

       

        

        try

        {

            Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ");

            q.setParameter("id", id);

            events = q.getResultList();

            
           
            session.getTransaction().commit();

        } catch (Exception e)

        {

            e.printStackTrace();

            

            return 0;

        

        }

        

        return events.size();

    }

    public static int getMaxAantalDeelnemers(int id)

    {
    	List<Event> events = new ArrayList<Event>();
        Session session = Main.factory.getCurrentSession();

        session.beginTransaction();

        

        int maxDeelnames = 0;

        

        try

        {

            Query<Event> q = session.createQuery("FROM Event WHERE opleiding_id = :id ");

            q.setParameter("id", id);

            events = q.getResultList();

            
            for (int i = 0; i < events.size(); i++) {
                
             
            maxDeelnames = events.get(i).getMaxDeelnames()+maxDeelnames;
            System.out.println(maxDeelnames);
            }
            session.getTransaction().commit();

        } catch (Exception e)

        {

            e.printStackTrace();

            

            return 0;

        

        }

        

        return maxDeelnames;

    }
    public static List<Event> getNaaam()

    {
    	List<Event> events = new ArrayList<Event>();
        Session session = Main.factory.getCurrentSession();

        session.beginTransaction();

        

        int maxDeelnames = 0;

        

        try

        {

            Query<Event> q = session.createQuery("Distinct FROM Event ORDER BY startdatum");

            

            events = q.getResultList();

            
           
                
             
            	 events = q.getResultList();

                 session.getTransaction().commit();

             } catch (Exception e)

             {
                 e.printStackTrace();

                 return null;
             }
             return events;

         
    }
    

    public static String getOpleidingNaam(int EventId)

    {

        Session session = Main.factory.getCurrentSession();

        session.beginTransaction();

        String opleidingNaam = null;

        

        try

        {

            Query<Opleiding> q = session.createQuery("FROM Opleiding o JOIN Event e ON(o.opleiding_id = e.opleiding_id WHERE e.event_id = :id");

            q.setParameter("id", EventId);

            Opleiding opleiding = q.getSingleResult();

            opleidingNaam = opleiding.getNaam();

            

            session.getTransaction().commit();

        } catch (Exception e)

        {

            

            e.printStackTrace();

        

        }

        

        return opleidingNaam;

    }
}
