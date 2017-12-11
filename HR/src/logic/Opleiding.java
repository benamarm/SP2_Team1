package logic;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "opleidingen", catalog = "SP2Team01")
public class Opleiding implements java.io.Serializable {

	private static final long serialVersionUID = -8328719132114270840L;
	private Integer opleidingId;
	private String naam;
	private String beschrijving;
	private Set<Boek> boeken = new HashSet<Boek>(0);
	private Set<Vraag> vragen = new HashSet<Vraag>(0);
	private Set<Event> events = new HashSet<Event>(0);

	public Opleiding() {
	}

	public Opleiding(Opleiding o) {
		this.opleidingId = o.getOpleidingId();
		this.naam = o.getNaam();
		this.beschrijving = o.getBeschrijving();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "opleiding_id", unique = true, nullable = false)
	public Integer getOpleidingId() {
		return this.opleidingId;
	}

	public void setOpleidingId(Integer opleidingId) {
		this.opleidingId = opleidingId;
	}

	@Column(name = "naam", nullable = false, length = 65535)
	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Column(name = "beschrijving", nullable = false, length = 65535)
	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	@Transient
	public Set<Boek> getBoeken() {
		return this.boeken;
	}

	public void setBoeken(Set<Boek> boeken) {
		this.boeken = boeken;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opleiding")
	public Set<Vraag> getVragen() {
		return this.vragen;
	}

	public void setVragen(Set<Vraag> vragen) {
		this.vragen = vragen;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opleiding")
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beschrijving == null) ? 0 : beschrijving.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((opleidingId == null) ? 0 : opleidingId.hashCode());
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
		Opleiding other = (Opleiding) obj;
		if (beschrijving == null) {
			if (other.beschrijving != null)
				return false;
		} else if (!beschrijving.equals(other.beschrijving))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (opleidingId == null) {
			if (other.opleidingId != null)
				return false;
		} else if (!opleidingId.equals(other.opleidingId))
			return false;
		return true;
	}
	
	

}
