package logic;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "adressen", catalog = "SP2Team01")
public class Adres implements java.io.Serializable {

	private static final long serialVersionUID = 5715835891205576913L;
	private Integer adresId;
	private String straat;
	private int nummer;
	private int postcode;
	private String land;
	private Set<Event> events = new HashSet<Event>(0);

	public Adres() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "adres_id", unique = true, nullable = false)
	public Integer getAdresId() {
		return this.adresId;
	}

	public void setAdresId(Integer adresId) {
		this.adresId = adresId;
	}

	@Column(name = "straat", nullable = false, length = 30)
	public String getStraat() {
		return this.straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	@Column(name = "nummer", nullable = false)
	public int getNummer() {
		return this.nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	@Column(name = "postcode", nullable = false)
	public int getPostcode() {
		return this.postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	@Column(name = "land", nullable = false, length = 30)
	public String getLand() {
		return this.land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adres")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@Transient
	@Override
	public String toString() {
		return (straat + " " + nummer + ", " + postcode + " " + land);
	}
	
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresId == null) ? 0 : adresId.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + nummer;
		result = prime * result + postcode;
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		return result;
	}

	@Transient
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adres other = (Adres) obj;
		if (adresId == null) {
			if (other.adresId != null)
				return false;
		} else if (!adresId.equals(other.adresId))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (nummer != other.nummer)
			return false;
		if (postcode != other.postcode)
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}

}
