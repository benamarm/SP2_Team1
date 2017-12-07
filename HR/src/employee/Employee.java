package employee;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import database.DatabaseObject;

public class Employee implements DatabaseObject{
	private int id;
	private String name;
	private String birthDate;
	private String role;
	private String hiredate;
	private String address;
	private String city;
	private String postcode;
	private String country;
	private String phoneNumber;
	
	private Set<Certification> certifications = new HashSet<Certification>();
	
	public Employee() {}
	public Employee(int id, String name, String role) {
		super();
		this.name = name;
		this.role = role;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<Certification> getCertifications() {
		return certifications;
	}
	public void setCertifications(Set<Certification> certifications) {
		this.certifications = certifications;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Employee [id= " + id + ", name= " + name + ", birthDate= " + birthDate + ", role= " + role + ", hiredate= "
				+ hiredate + ", address= " + address + ", city= " + city + ", postcode= " + postcode + ", country= "
				+ country + ", phoneNumber= " + phoneNumber + ", certifications= " + certifications + "]";
	}
	public Serializable getSId() {
		return getId();
	}
	public void setSId(Serializable id) {
		setId((int) id);
	}
}
