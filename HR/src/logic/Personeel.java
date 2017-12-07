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
	private String voornaam;
	private String achternaam;
	private String birthDate;
	private String role;
	private String hiredate;
	private String address;
	private String city;
	private String postcode;
	private String country;
	private String phoneNumber;
	
	private Set<Vaardigheid> vaardigheden = new HashSet<Vaardigheid>(0);

	public Personeel() {
	}

	@Id
	@Column(name = "pers_id", unique = true, nullable = false, length = 50)
	public String getPersId() {
		return this.persId;
	}

	public void setPersId(String i) {
		this.persId = i;
	}

	@Transient
	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
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
	
	
	@Transient
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	@Transient
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	@Transient
	public String getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Transient
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Transient
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	@Transient
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Transient
	public String getVolleNaam() {
		return voornaam + " " + achternaam;
	}

	
}
