package logic;
import java.util.ArrayList;
import java.sql.Date;

public class Personeel {
	private int pers_id;
	private String loginEmail;
	//private Departement dept;
	private ArrayList<Vaardigheid> vaardigheden;
	private String vnaam;
	private String naam;
	private String titel;
	private String prefix;
	private Date gebdate;
	/* Ik gebruik hier geen ArrayList van adressen omdat we het adres krijgen van de ERP 
	   en daar lijkt elk personeelslid maar 1 adres te kunnen hebben */
	private Adres adres;
	private int telefoon;
	private String photopath;
	private String notes;
	
	public Personeel(int pers_id, String loginEmail, String vnaam, String naam, String titel, String prefix,
			Date gebdate, Adres adres, int telefoon) {
		super();
		this.vaardigheden = new ArrayList<Vaardigheid>();
		this.pers_id = pers_id;
		this.loginEmail = loginEmail;
		this.vnaam = vnaam;
		this.naam = naam;
		this.titel = titel;
		this.prefix = prefix;
		this.gebdate = gebdate;
		this.adres = adres;
		this.telefoon = telefoon;
	}

	public int getPers_id() {
		return pers_id;
	}

	public void setPers_id(int pers_id) {
		this.pers_id = pers_id;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public ArrayList<Vaardigheid> getVaardigheden() {
		return vaardigheden;
	}

	public void addVaardigheid(Vaardigheid v) {
		vaardigheden.add(v);
	}
	
	public void setVaardigheden(ArrayList<Vaardigheid> vaardigheden) {
		this.vaardigheden = vaardigheden;
	}

	public String getVnaam() {
		return vnaam;
	}

	public void setVnaam(String vnaam) {
		this.vnaam = vnaam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getGebdate() {
		return gebdate;
	}

	public void setGebdate(Date gebdate) {
		this.gebdate = gebdate;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public int getTelefoon() {
		return telefoon;
	}

	public void setTelefoon(int telefoon) {
		this.telefoon = telefoon;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Personeel [pers_id=" + pers_id + ", " + (loginEmail != null ? "loginEmail=" + loginEmail + ", " : "")
				+ (vnaam != null ? "vnaam=" + vnaam + ", " : "") + (naam != null ? "naam=" + naam + ", " : "")
				+ (titel != null ? "titel=" + titel + ", " : "") + (prefix != null ? "prefix=" + prefix + ", " : "")
				+ (gebdate != null ? "gebdate=" + gebdate + ", " : "") + "telefoon=" + telefoon + ", "
				+ (photopath != null ? "photopath=" + photopath + ", " : "") + (notes != null ? "notes=" + notes : "")
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gebdate == null) ? 0 : gebdate.hashCode());
		result = prime * result + ((loginEmail == null) ? 0 : loginEmail.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + pers_id;
		result = prime * result + telefoon;
		result = prime * result + ((vnaam == null) ? 0 : vnaam.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Personeel))
			return false;
		Personeel other = (Personeel) obj;
		if (gebdate == null) {
			if (other.gebdate != null)
				return false;
		} else if (!gebdate.equals(other.gebdate))
			return false;
		if (loginEmail == null) {
			if (other.loginEmail != null)
				return false;
		} else if (!loginEmail.equals(other.loginEmail))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (pers_id != other.pers_id)
			return false;
		if (telefoon != other.telefoon)
			return false;
		if (vnaam == null) {
			if (other.vnaam != null)
				return false;
		} else if (!vnaam.equals(other.vnaam))
			return false;
		return true;
	}
	
	
	
	
	
	
}
