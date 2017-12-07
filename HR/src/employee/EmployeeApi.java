package employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class EmployeeApi {
	public enum EmployeeKey {EmployeeID, LastName, FirstName}
	
	public static Set<Employee> getEmployee(Map<EmployeeKey, Serializable> attributes) {
		String url = buildUrl(attributes);
		JSONArray values = urlGetRequest(url).getJSONArray("value");
		Set<Employee> employees = new HashSet<Employee>();
		for(int i = 0; i < values.length(); i++) {
			Employee e = new Employee();
			e.setId(values.getJSONObject(i).getInt("EmployeeID"));
			e.setName(values.getJSONObject(i).getString("FirstName") + " " + values.getJSONObject(i).getString("LastName"));
			e.setBirthDate(values.getJSONObject(i).getString("BirthDate"));
			e.setRole(values.getJSONObject(i).getString("Title"));
			e.setHiredate(values.getJSONObject(i).getString("HireDate"));
			e.setAddress(values.getJSONObject(i).getString("Address"));
			e.setCity(values.getJSONObject(i).getString("City"));
			e.setPostcode(values.getJSONObject(i).getString("PostalCode"));
			e.setCountry(values.getJSONObject(i).getString("Country"));
			e.setPhoneNumber(values.getJSONObject(i).getString("HomePhone"));
			
			employees.add(e);
		}
		return employees;
	}
	
	private static String buildUrl(Map<EmployeeKey, Serializable> filter) {
		Builder builder = new HttpUrl.Builder()
				.scheme("http")
				.host("services.odata.org")
				.addPathSegment("V3")
				.addPathSegment("Northwind")
				.addPathSegment("Northwind.svc")
				.addPathSegment("Employees")
				.addQueryParameter("$format", "json");
		if(filter == null) return builder.build().toString();
		for(Entry<EmployeeKey, Serializable> e : filter.entrySet() ) {
			builder.addQueryParameter("$filter",e.getKey().toString() + " eq " + e.getValue());
		}
		HttpUrl url = builder.build();
		return url.toString();
	}
	
	private static String JSONobject urlGetRequest(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
	      .url(url)
	      .build();
		Response response;
		try {
			response = client.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (IOException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Set<Employee> es = EmployeeApi.getEmployee(null);
//		for(Employee e : es) {
//			System.out.println(e);
//		}
		Map<EmployeeKey, Serializable> atts = new HashMap<EmployeeKey, Serializable>();
		System.out.println();
		atts.put(EmployeeKey.EmployeeID, "1");
		es = EmployeeApi.getEmployee(atts);
		for(Employee e : es) {
			System.out.println(e);
		}
	}
}
