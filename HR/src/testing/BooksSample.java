package testing;

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
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.model.Volumes;
import logic.Boek;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * A sample application that demonstrates how Google Books Client Library for
 * Java can be used to query Google Books. It accepts queries in the command
 * line, and prints the results to the console.
 *
 * $ java com.google.sample.books.BooksSample [--author|--isbn|--title] "<query>"
 *
 * Please start by reviewing the Google Books API documentation at:
 * http://code.google.com/apis/books/docs/getting_started.html
 */
public class BooksSample {
  private static final String APPLICATION_NAME = "HR Application";
  
  private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
  //private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();

  private static ArrayList<Boek> queryGoogleBooks(JsonFactory jsonFactory, String query) throws Exception {
    ClientCredentials.errorIfNotSpecified();
    
    // Set up Books client.
    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
        .setApplicationName(APPLICATION_NAME)
        .setGoogleClientRequestInitializer(new BooksRequestInitializer("AIzaSyBl9G3y9xs86tKZ_610SKNNgeXA0xcAH4k"))
        .build();
    // Set query string and filter only Google eBooks.
    List volumesList = books.volumes().list(query);
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

  public static void main(String[] args) {
	  //te testen ISBN nummer: 978-0-323-03931-4
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    try {
//      // Verify command line parameters.
//      if (args.length == 0) {
//        System.err.println("Usage: BooksSample [--author|--isbn|--title] \"<query>\"");
//        System.exit(1);
//      }
       // Parse command line parameters into a query.
      // Query format: "[<author|isbn|intitle>:]<query>"
    	 //  Bv: prefix = "isbn: " & query = "9780141345642";
      String prefix = "title: ";
      String query = "Banking";
//      for (String arg : args) {
//        if ("--author".equals(arg)) {
//          prefix = "inauthor:";
//        } else if ("--isbn".equals(arg)) {
//          prefix = "isbn:";
//        } else if ("--title".equals(arg)) {
//          prefix = "intitle:";
//        } else if (arg.startsWith("--")) {
//          System.err.println("Unknown argument: " + arg);
//          System.exit(1);
//        } else {
//          query = arg;
//        }
//      }
      if (prefix != null) {
        query = prefix + query;
      }
      try {
        ArrayList<Boek> boeken = queryGoogleBooks(jsonFactory, query);
        for(Boek b: boeken) {
        	System.out.println(b.getTitel());
        	System.out.println(b.getIsbn());
        	System.out.println(b.getAuteurs());
        	System.out.println(b.getBeschrijving());
        	System.out.println(b.getPrijs());
        	System.out.println();
        }
        
        return;
      } catch (IOException e) {
        System.err.println(e.getMessage());
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(0);
  }
}