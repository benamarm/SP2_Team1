package logic;

import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class odata {
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
	
	
	public static String getTitle(Client client,int id) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees("+id+")/Title?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println(s);
		return s;
	}
	public static int getMaxid(Client client) {
		client=ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees/$count");
		String s= target.request(MediaType.TEXT_PLAIN).get(String.class);
		int x = Integer.valueOf(s);
		return x+1 ;
	}
	
	public static int countEmployees(Client client) {
		int aantal = 0;
		
		
		return aantal;
	}
//employeid,lastname,firstname,title,
	//p.setAchternaam(s.substring(114, 118));

//	format=application/json;odata.metadata=none

	public static ArrayList<Personeel> getAll () {
		ArrayList<Personeel> pers = new ArrayList<Personeel>();
		ArrayList<String> avoornaam = new ArrayList<String>();
		ArrayList<String> aachternaam = new ArrayList<String>();
	
		ArrayList<String> atitel = new ArrayList<String>();
		Client client = ClientBuilder.newClient();
		Personeel p = null;
		for(int id=1; id<getMaxid(client); id++) {	
		p = new Personeel();
		String achternaam =(getSingle(client,id));
		String Sub = achternaam.substring(10 , achternaam.length()-2);
		p.setAchternaam(Sub);
		aachternaam.add(Sub);
		
		
		String voornaam =(getFirstName(client,id));
		String Subvoornaam =voornaam.substring(10,voornaam.length()-2);
		p.setVoornaam(Subvoornaam);
		avoornaam.add(Subvoornaam);
	
		p.setPersId(id);
		
		
		
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
			
			
		 
			String titel=(getTitle(client,ID));
			String subtitel=titel.substring(10,titel.length()-2);
			
			ps.SetTitel(subtitel);
		return ps ;
	}
	public static void setInfo(Personeel p) {
		Client client = ClientBuilder.newClient();
		 String achternaam =(getSingle(client,p.getPersId()));
			String Sub = achternaam.substring(10 , achternaam.length()-2);
			p.setAchternaam(Sub);
			
			String voornaam =(getFirstName(client,p.getPersId()));
			String Subvoornaam =voornaam.substring(10,voornaam.length()-2);
			p.setVoornaam(Subvoornaam);	
			
			
		 
			String titel=(getTitle(client,p.getPersId()));
			String subtitel=titel.substring(10,titel.length()-2);
			
			p.SetTitel(subtitel);
	}
	
	
		
		    
	
			public  static void main(String[]args) {
				
//				WebTarget target = client.target("http://services.odata.org/V4/Northwind/Northwind.svc/Employees(7)/LastName ; filter=startswith(LastName, 'K')");
//				String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
				ArrayList<Personeel> maina = getAll();
				
				for(int id=1; id<maina.size()+1; id++) {	
				
				
				System.out.println("----------------");
				System.out.println("id :"+ id);
			
			System.out.println("voornaam: "+maina.get(id-1).getVoornaam());
			System.out.println("achternaam: "+maina.get(id-1).getAchternaam());
			System.out.println("titel:  "+maina.get(id-1).getTitel());
				}
				int ID =7 ;
				Personeel pmain = getPSingle(ID);
				System.out.println("*****************");
				System.out.println("id :"+ ID);
				
				System.out.println("voornaam:  "+pmain.getVoornaam());
				System.out.println("achternaam:  "+pmain.getAchternaam());
				System.out.println("titel:  "+pmain.getTitel());
				
				

	    
	   


			}
}