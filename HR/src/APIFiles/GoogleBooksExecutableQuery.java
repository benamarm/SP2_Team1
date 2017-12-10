package APIFiles;

import javax.persistence.Transient;

public class GoogleBooksExecutableQuery {
	private String query;
	
	public GoogleBooksExecutableQuery(GoogleBooksQueryPrefix prefix, String query) {
		super();		
		this.query = createQuery(prefix, query);
	}



	public static String createQuery(GoogleBooksQueryPrefix prefix, String query) {
		
		switch(prefix) {
		case TITEL: query = "title:"+query; break;
		case ISBN: query = "isbn:"+query; break;
		case AUTEUR: query = "author:"+query; break;
		}
		
		return query;
	}


	@Transient
	public String getQuery() {
		return query;
	}
	
	
}
