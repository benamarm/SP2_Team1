package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Table;


@Table(name = "boeken", catalog = "SP2Team01")
public class Boek implements java.io.Serializable {

	private static final long serialVersionUID = -2568800152414086025L;
	//private Integer boekId;
	private String titel;
	private String beschrijving;
	private ArrayList<String> auteurs = new ArrayList<String>();
	private String isbn;
	private String prijs;
	private String rating;
	private String taal;
	private String uitgeverij;
	private Set<Opleiding> opleidingen = new HashSet<Opleiding>(0);

	public Boek() {
	}


	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public ArrayList<String> getAuteurs() {
		return auteurs;
	}


	public void setAuteurs(ArrayList<String> auteurs) {
		this.auteurs = auteurs;
	}
	
	public void addAuteur(String auteur) {
		if(auteur != null) {
			this.auteurs.add(auteur);
		}
	}


	public String getPrijs() {
		return prijs;
	}
	
	public String getTaal() {
		return taal;
	}


	public void setTaal(String taal) {
		this.taal = taal;
	}

	public String getUitgeverij() {
		return uitgeverij;
	}


	public void setUitgeverij(String uitgeverij) {
		this.uitgeverij = uitgeverij;
	}


	public void setPrijs(String prijs) {
		if(prijs != null) this.prijs = prijs.substring(3);
	}

	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		if(rating != null) this.rating = rating;
	}


	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		if(beschrijving != null) this.beschrijving = beschrijving;
	}
	
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		if(isbn != null) this.isbn = isbn;
	}

	public Set<Opleiding> getOpleidingen() {
		return this.opleidingen;
	}

	public void setOpleidingen(Set<Opleiding> opleidingen) {
		this.opleidingen = opleidingen;
	}
	
	
	
	@Override
	public String toString() {
		return "Boek [titel=" + titel + ", beschrijving=" + beschrijving + ", auteurs=" + auteurs + ", isbn=" + isbn
				+ ", prijs=" + prijs + ", rating=" + rating + ", taal=" + taal + ", uitgeverij=" + uitgeverij
				+ ", opleidingen=" + opleidingen + "]";
	}


}
