package employee;

import java.io.Serializable;
import java.util.Calendar;

import database.DatabaseObject;

public class Certification implements DatabaseObject{
	private int id;
	private String type;
	private String name;
	private byte[] image;
	private Calendar date;
	private int trainingId;
	
	public Certification( ) {}
	public Certification(String type, String name, byte[] image, Calendar date) {
		super();
		this.type = type;
		this.name = name;
		this.image = image;
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public int getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
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
		Certification other = (Certification) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Certification [id=" + id + ", type=" + type + ", name=" + name + ", image=" + image + ", date=" + date.getTime()
				+ ", trainingId=" + trainingId + "]";
	}
	public Serializable getSId() {
		return getId();
	}
	public void setSId(Serializable id) {
		setId((int) id);
	}
}
