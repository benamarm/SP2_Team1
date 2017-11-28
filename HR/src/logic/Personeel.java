package logic;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "personeel", catalog = "SP2Team01")
public class Personeel implements java.io.Serializable {

	private static final long serialVersionUID = 4691520798882102440L;
	private String persId;
	@Transient
	private String naam;
	@Transient
	private String achternaam;
	private Set<Vaardigheid> vaardigheden = new HashSet<Vaardigheid>(0);

	public Personeel() {
	}

	@Id
	@Column(name = "pers_id", unique = true, nullable = false, length = 50)
	public String getPersId() {
		return this.persId;
	}

	public void setPersId(String persId) {
		this.persId = persId;
	}

	@Transient
	public String getnaam() {
		return naam;
	}

	public void setnaam(String naam) {
		this.naam = naam;
	}

	@Transient
	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personeel")
	public Set<Vaardigheid> getVaardigheden() {
		return this.vaardigheden;
	}

	public void setVaardigheden(Set<Vaardigheid> vaardigheden) {
		this.vaardigheden = vaardigheden;
	}

}
