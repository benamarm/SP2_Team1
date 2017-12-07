package logic;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.research.ws.wadl.Request;

public class odata extends Personeel {
	public odata() {
		
	}
	public static void getEverything(Client client) {
	client = ClientBuilder.newClient();
	WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees");
	String s=target.request(MediaType.APPLICATION_JSON).get(String.class);

	System.out.println(s);
	}
	
	public static String getSingle(Client client, int id) {
	
	
	client = ClientBuilder.newClient();
	WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees("+id+")/LastName?$format=application/json;odata.metadata=none");
	String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
	//System.out.println(s);
	return s;
	}
	public static String getFirstName(Client client,int id) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees("+id+")/FirstName?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(s);
		return s;
		
	}
	public static String getPersid(Client client , String naam, String ANaam) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees?$filter=FirstName=("+naam +") and LastName=("+ANaam+") format=application/json;odata.metadata=none; ");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(s);
		return s;
		
		
	}
	public static String getCountry(Client client,int id) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees("+id+")/Country?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(s);
		return s;
	}
	public static String getTitle(Client client,int id) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees("+id+")/Title?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(s);
		return s;
	}
//employeid,lastname,firstname,title,
	//p.setAchternaam(s.substring(114, 118));

//	format=application/json;odata.metadata=none

	public static ArrayList<Personeel> getAll () {
		ArrayList<Personeel> pers = new ArrayList<Personeel>();
		ArrayList<String> avoornaam = new ArrayList<String>();
		ArrayList<String> aachternaam = new ArrayList<String>();
		ArrayList<String> aland = new ArrayList<String>();
		ArrayList<String> atitel = new ArrayList<String>();
		
		for(int id=1; id<8; id++) {	
		
		Client client = ClientBuilder.newClient();
		 Personeel p = new Personeel();
		String achternaam =(getSingle(client,id));
		String Sub = achternaam.substring(10 , achternaam.length()-2);
		p.setAchternaam(Sub);
		aachternaam.add(Sub);
		
		
		String voornaam =(getFirstName(client,id));
		String Subvoornaam =voornaam.substring(10,voornaam.length()-2);
		p.setVoornaam(Subvoornaam);
		avoornaam.add(Subvoornaam);
	
		
		String land =(getCountry(client,id));
		String subland =land.substring(10,land.length()-2);
		p.SetLand(subland);
		aland.add(subland);
		
		
		String titel=(getTitle(client,id));
		String subtitel=titel.substring(10,titel.length()-2);
		atitel.add(subtitel);
		p.SetTitel(subtitel);
		
		
		
		
		pers.add(p);
		
		}
		
		return pers;
		
		
	}
	public static Personeel getPSingle(int ID) {
		Client client = ClientBuilder.newClient();
		 Personeel ps = new Personeel();
		 String achternaam =(getSingle(client,ID));
			String Sub = achternaam.substring(10 , achternaam.length()-2);
			ps.setAchternaam(Sub);
			
			String voornaam =(getFirstName(client,ID));
			String Subvoornaam =voornaam.substring(10,voornaam.length()-2);
			ps.setVoornaam(Subvoornaam);	
			
			String land =(getCountry(client,ID));
			String subland =land.substring(10,land.length()-2);
			ps.SetLand(subland);
		 
			String titel=(getTitle(client,ID));
			String subtitel=titel.substring(10,titel.length()-2);
			
			ps.SetTitel(subtitel);
		return ps ;
	}
	
	
		
		    
	
			public  static void main(String[]args) {
				Client client = ClientBuilder.newClient();
				
//				WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees(7)/LastName ; filter=startswith(LastName, 'K')");
//				String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
				ArrayList<Personeel> maina = getAll();
				Personeel pep = new Personeel() ;
				for(int id=1; id<8; id++) {	
				
				pep= maina.get(id-1);
				System.out.println("----------------");
			System.out.println(pep.getLand()) ;
			System.out.println(pep.getVoornaam());
			System.out.println(pep.getAchternaam());
			System.out.println(pep.getTitel());
				}
				
				Personeel pmain = getPSingle(7);
				System.out.println("*****************");
				System.out.println(pmain.getLand()) ;
				System.out.println(pmain.getVoornaam());
				System.out.println(pmain.getAchternaam());
				System.out.println(pmain.getTitel());
				
//				Personeel p = new Personeel();
//				String achternaam =(getSingle(client,7));
//				String Sub = achternaam.substring(10 , achternaam.length()-2);
//				p.setAchternaam(Sub);
//				System.out.println(p.getAchternaam());
//				String voornaam =(getFirstName(client,7));
//				String Subvoornaam =voornaam.substring(10,voornaam.length()-2);
//				p.setVoornaam(Subvoornaam);
//				System.out.println(p.getVoornaam());
//				String land =(getCountry(client,7));
//				String subland =land.substring(10,land.length()-2);
//				p.SetLand(subland);
//				System.out.println(p.getLand());
//				String titel=(getTitle(client,7));
//				String subtitel=titel.substring(10,titel.length()-2);
//				p.SetTitel(subtitel);
				
//				Client client = ClientBuilder.newClient();
//				 getSingle(client, 7);
//		    	Personeel p = new Personeel();
//		    	p.setVoornaam(getSingle(client,7));
		    	
		    	

		    	/*String met=getLastName(client,"scottketchum");
		    	String sub=met.substring(10);
		    	sub=sub.substring(0, sub.length()-2);
	    	System.out.println(sub);

	    	String metho=getFirstName(client,"scottketchum");
		    	String subs=metho.substring(10);
	    	subs=subs.substring(0, subs.length()-2);
		    	System.out.println(subs);
//
*///		    	String methode=getEmail(client,"scottketchum");
//		    	String submethode=methode.substring(10);
//		    	submethode=submethode.substring(0, submethode.length()-2);
//		    	System.out.println(submethode);
//
//		    	String m=getAddress(client,"scottketchum");
//		    	String s=m.substring(10);
//		    	s=s.substring(0, s.length()-2);
//		    	System.out.println(s);

	    
	   


			}
}