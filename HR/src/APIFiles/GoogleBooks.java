package APIFiles;
//import testing.ClientCredentials;

/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;

import APIFiles.GoogleBooks;
import logic.Boek;

import com.google.api.services.books.model.Volumes;

//import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GoogleBooks{
	
	private static final String APPLICATION_NAME = "HR Application";
	  
	  private static final String API_KEY = "AIzaSyBl9G3y9xs86tKZ_610SKNNgeXA0xcAH4k";
	  private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
	  
	  public static ArrayList<Boek> executeQuery(JsonFactory jsonFactory, GoogleBooksExecutableQuery query) throws Exception {
	    //ClientCredentials.errorIfNotSpecified();
		    
		    // Set up Books client.
		    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
		        .setApplicationName(APPLICATION_NAME)
		        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
		        .build();
		    // Set query string and filter only Google eBooks.
		    List volumesList = books.volumes().list(query.getQuery());
		    volumesList.setFilter("ebooks");

		    // Execute the query.
		    Volumes volumes = volumesList.execute();
		    if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
		      System.out.println("No matches found.");
		      return null;
		    }
		    
		    //Lijst van boeken aanmaken en een boek om erin te steken
		    ArrayList<Boek> zoekresultaten = new ArrayList<Boek>();
		    Boek boek = null;

		    // Boek initialiseren stap per stap
		    for (Volume volume : volumes.getItems()) {
		    		boek = new Boek();
		      Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
		      Volume.SaleInfo saleInfo = volume.getSaleInfo();
		      
		      // de ISBN nummer
		      for(IndustryIdentifiers isbn: volumeInfo.getIndustryIdentifiers()) {
		    	  	//System.out.println(isbn.getIdentifier());
		    	  	if(isbn.getIdentifier() != null) {
		    	  		boek.setIsbn(isbn.getIdentifier());
		    	  		break;
		    	  	}
		      }
		      
		      // Taal
		      boek.setTaal(volumeInfo.getLanguage());
		      
		      // Uitgeverij
		      boek.setUitgeverij(volumeInfo.getPublisher());
		      
		      // Titel
		      boek.setTitel(volumeInfo.getTitle());
		      // Auteur(s)
		      java.util.List<String> authors = volumeInfo.getAuthors();
		      if (authors != null && !authors.isEmpty()) {
		        for(String a: authors) boek.addAuteur(a);
		      }
		      // Beschrijving
		      if (volumeInfo.getDescription() != null && volumeInfo.getDescription().length() > 0) {
		        boek.setBeschrijving(volumeInfo.getDescription());
		      }
		      // Rating
		      if (volumeInfo.getRatingsCount() != null && volumeInfo.getRatingsCount() > 0) {
		        int fullRating = (int) Math.round(volumeInfo.getAverageRating().doubleValue());
		        String rating = null;
		        for (int i = 0; i < fullRating; ++i) {
		          rating += "*";
		        }
		        boek.setRating(rating);
		      }
		      // Prijs
		      if (saleInfo != null && "FOR_SALE".equals(saleInfo.getSaleability())) {
		          boek.setPrijs(CURRENCY_FORMATTER.format(saleInfo.getListPrice().getAmount()));
		      }
		      
		      // En nu het boek toevoegen aan onze zoekresultaten en indien nodig opnieuw beginnen
		      zoekresultaten.add(boek);
		    }
		    
		    
		    return zoekresultaten;
		  }
	  
	  
	  
	  
//	  public static void main(String[] args) {
//		  JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//		  GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, "Banking");
//		    try {
//		      try {
//		        ArrayList<Boek> boeken = GoogleBooks.executeQuery(jsonFactory, query);
//		        for(Boek b: boeken) {
//		        	System.out.println(b.getTitel());
//		        	System.out.println(b.getIsbn());
//		        	System.out.println(b.getAuteurs());
//		        	System.out.println(b.getBeschrijving());
//		        	System.out.println(b.getPrijs());
//		        	System.out.println();
//		        }
//		        
//		        return;
//		      } catch (IOException e) {
//		        System.err.println(e.getMessage());
//		      }
//		    } catch (Throwable t) {
//		      t.printStackTrace();
//		    }
//		    System.exit(0);
//		  }
	
}
