//package testing;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import okhttp3.HttpUrl;
//import okhttp3.HttpUrl.Builder;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import logic.Personeel;
//
//public abstract class OData {
//	public enum PersoneelKey {EmployeeID, LastName, FirstName}
//	
//	public static Set<Personeel> getPersoneel(Map<PersoneelKey, Serializable> attributes) {
//		//String url = buildUrl(attributes);
//		JSONArray values = urlGetRequest("http://services.odata.org/V4/Northwind/Northwind.svc/Employees").getJSONArray("value");
//		Set<Personeel> Personeels = new HashSet<Personeel>();
//		for(int i = 0; i < values.length(); i++) {
//			Personeel p = new Personeel();
//			p.setPersId(""+values.getJSONObject(i).getInt("EmployeeID"));
//			p.setVoornaam(values.getJSONObject(i).getString("FirstName") + " " + values.getJSONObject(i).getString("LastName"));
//			p.setBirthDate(values.getJSONObject(i).getString("BirthDate"));
//			p.setRole(values.getJSONObject(i).getString("Title"));
//			p.setHiredate(values.getJSONObject(i).getString("HireDate"));
//			p.setAddress(values.getJSONObject(i).getString("Address"));
//			p.setCity(values.getJSONObject(i).getString("City"));
//			p.setPostcode(values.getJSONObject(i).getString("PostalCode"));
//			p.setCountry(values.getJSONObject(i).getString("Country"));
//			p.setPhoneNumber(values.getJSONObject(i).getString("HomePhone"));
//			
//			Personeels.add(p);
//		}
//		return Personeels;
//	}
//	
//	private static String buildUrl(Map<PersoneelKey, Serializable> filter) {
//		Builder builder = new HttpUrl.Builder()
//				.scheme("http")
//				.host("services.odata.org")
//				.addPathSegment("V3")
//				.addPathSegment("Northwind")
//				.addPathSegment("Northwind.svc")
//				.addPathSegment("Employees")
//				.addQueryParameter("$format", "json");
//		if(filter == null) return builder.build().toString();
//		for(Entry<PersoneelKey, Serializable> e : filter.entrySet() ) {
//			builder.addQueryParameter("$filter",e.getKey().toString() + " eq " + e.getValue());
//		}
//		HttpUrl url = builder.build();
//		return url.toString();
//	}
//	
//	private static JSONObject urlGetRequest(String url) {
//		OkHttpClient client = new OkHttpClient();
//		Request request = new Request.Builder()
//	      .url(url)
//	      .build();
//		Response response;
//		try {
//			response = client.newCall(request).execute();
//			return new JSONObject(response.body().string());
//		} catch (IOException e) {
//			return null;
//		} catch (NullPointerException e) {
//			return null;
//		}
//	}
//	
//	public static void main(String[] args) {
//		Set<Personeel> es = OData.getPersoneel(null);
////		for(Personeel e : es) {
////			System.out.println(e);
////		}
//		Map<PersoneelKey, Serializable> atts = new HashMap<PersoneelKey, Serializable>();
//		System.out.println();
//		atts.put(PersoneelKey.EmployeeID, "1");
//		es = OData.getPersoneel(atts);
//		for(Personeel e : es) {
//			System.out.println(e);
//		}
//	}
//}
