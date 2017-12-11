package logic;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "personeel", catalog = "SP2Team01")
public class Personeel implements java.io.Serializable {

	private static final long serialVersionUID = 4691520798882102440L;
	private int persId;
	private WebUser account;
	private Set<Vaardigheid> vaardigheden = new HashSet<Vaardigheid>(0);

	@Transient
	private String voornaam;
	@Transient
	private String achternaam;
	@Transient
	private String titel;

	public Personeel() {
	}

	@Id
	@Column(name = "pers_id", unique = true, nullable = false, length = 50)
	public int getPersId() {
		return this.persId;
	}

	public void setPersId(int persId) {
		this.persId = persId;
	}

	@OneToOne(mappedBy = "personeel", cascade = CascadeType.ALL)
	public WebUser getAccount() {
		return this.account;
	}

	public void setAccount(WebUser account) {
		this.account = account;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personeel")
	public Set<Vaardigheid> getVaardigheden() {
		return this.vaardigheden;
	}

	public void setVaardigheden(Set<Vaardigheid> vaardigheden) {
		this.vaardigheden = vaardigheden;
	}

	@Transient
	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	@Transient
	public String getTitel() {
		return titel;
	}

	public void SetTitel(String titel) {
		this.titel = titel;
	}

	@Transient
	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	@Transient
	public String getVolleNaam() {
		return voornaam + " " + achternaam;
	}

	public Personeel(int id, String sub, String subvoornaam, String subtitel) {
		// TODO Auto-generated method stub
		this.persId = id;
		this.voornaam = sub;
		this.achternaam = subvoornaam;
		this.titel = subtitel;

	}

}
