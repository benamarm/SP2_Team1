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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "opleiding_boek", catalog = "SP2Team01", joinColumns = @JoinColumn(name = "opleiding_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "boek_id", nullable = false, updatable = false))
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

}
