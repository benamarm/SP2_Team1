package logic;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Table;

@Entity
@Table(name = "boeken", catalog = "SP2Team01")
public class Boek implements java.io.Serializable {

	private static final long serialVersionUID = -2568800152414086025L;
	private Integer boekId;
	private String titel;
	private String beschrijving;
	private String auteur;
	private String isbn;
	private String taal;
	private String uitgeverij;
	private Set<Opleiding> opleidingen = new HashSet<Opleiding>(0);

	public Boek() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "boek_id", unique = true, nullable = false)
	public Integer getBoekId() {
		return this.boekId;
	}

	public void setBoekId(Integer boekId) {
		this.boekId = boekId;
	}

	@Column(name = "titel", nullable = false, length = 30)
	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	@Column(name = "beschrijving", nullable = false, length = 100)
	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	@Column(name = "auteur", length = 50)
	public String getAuteur() {
		return this.auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	@Column(name = "ISBN", length = 30)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "taal", length = 30)
	public String getTaal() {
		return this.taal;
	}

	public void setTaal(String taal) {
		this.taal = taal;
	}

	@Column(name = "uitgeverij", length = 30)
	public String getUitgeverij() {
		return this.uitgeverij;
	}

	public void setUitgeverij(String uitgeverij) {
		this.uitgeverij = uitgeverij;
	}

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "boeken")
	public Set<Opleiding> getOpleidingen() {
		return this.opleidingen;
	}

	public void setOpleidingen(Set<Opleiding> opleidingen) {
		this.opleidingen = opleidingen;
	}

}
