package logic;
import java.util.concurrent.CompletionStage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class odata {
	public odata() {
		
	}
	public static void getEverything(Client client) {
	client = ClientBuilder.newClient();
	WebTarget target = client.target("http://services.odata.org/V2/Northwind/Northwind.svc/Employees");
	String s=target.request(MediaType.APPLICATION_JSON).get(String.class);

	System.out.println(s);
	}
//employeid,lastname,firstname,title,
	
	public static String getFirstName(Client client, int EmployeeID) {
		client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V2/Northwind/Northwind.svc/Employees('"+EmployeeID+"')/FirstName?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);

		return( s);
		}
	
	public static String getLastName(Client client, String id) {
		client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V2/Northwind/Northwind.svc/Employees('"+id+"')/LastName?$format=application/json;odata.metadata=none");
		String s= target.request(MediaType.APPLICATION_JSON).get(String.class);
		return s;
		}
	

	

	

		private static void UsernameArray()
		{
			int EmployeeID = 7;
		   String[] names  = new String[7];

		    names[0] = "scottketchum";
		    names[1] = "russellwhyte";
		    names[2] = "ronaldmundy";
		    names[3] = "javieralfred";
		    names[4] = "willieashmore";
		    names[5] = "vincentcalabrese";
		    names[6]=	"clydeguess";

		    for (String element:names) {
		   
		    Client client = ClientBuilder.newClient();
		    String met=getLastName(client,element);
	    String sub=met.substring(10);
	    sub=sub.substring(0, sub.length()-2);
		System.out.println(sub);

		String metho=getFirstName(client,EmployeeID);
	    String subs=metho.substring(10);
	    subs=subs.substring(0, subs.length()-2);
		System.out.println(subs);

		/*String methode=getEmail(client,element);
		    String submethode=methode.substring(10);
		    submethode=submethode.substring(0, submethode.length()-2);
		System.out.println(submethode);

		String m=getAddress(client,element);
		    String s=m.substring(10);
		    s=s.substring(0, s.length()-2);*/
	    }
		    }
		    
			public  static void main(String[]args) {
				Client client = ClientBuilder.newClient();
				 getEverything(client);
		    	
		    	
		    	

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
