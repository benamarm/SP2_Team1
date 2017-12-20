package logic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import database.EventDAO;
@Entity
@Table(name = "events", catalog = "SP2Team01")
public class Event implements java.io.Serializable {

	private static final long serialVersionUID = 1921441815968252172L;
	private Integer eventId;
	private Adres adres;
	private  Opleiding opleiding;
	private String naamTrainer;
	private Date startdatum;
	private Date einddatum;
	private  int aantalDeelnames;
	private	 int maxDeelnames;
	private Boolean afgelast;
	private Set<Vaardigheid> vaardigheden = new HashSet<Vaardigheid>(0);
	public Event () {
		
	}
	public Event(Event e) {
		this.aantalDeelnames=e.getAantalDeelnames();
		this.maxDeelnames=e.getMaxDeelnames();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@ManyToOne
	@JoinColumn(name = "adres_id", nullable = false)
	public Adres getAdres() {
		return this.adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	@ManyToOne
	@JoinColumn(name = "opleiding_id", nullable = false)
	public  Opleiding getOpleiding() {
		return this.opleiding;
	}

	public void setOpleiding(Opleiding opleiding) {
		this.opleiding = opleiding;
	}

	@Column(name = "naam_trainer", length = 50)
	public String getNaamTrainer() {
		return this.naamTrainer;
	}

	public void setNaamTrainer(String naamTrainer) {
		this.naamTrainer = naamTrainer;
	}

	@Column(name = "startdatum", nullable = false)
	public Date getStartdatum() {
		return this.startdatum;
	}

	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}

	@Column(name = "einddatum", nullable = false)
	public Date getEinddatum() {
		return this.einddatum;
	}

	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}

	@Column(name = "aantal_deelnames", nullable = false)
	public  int getAantalDeelnames() {
		return this.aantalDeelnames;
	}

	public void setAantalDeelnames(int aantalDeelnames) {
		this.aantalDeelnames = aantalDeelnames;
	}

	@Column(name = "max_deelnames", nullable = false)
	public   int getMaxDeelnames() {
		return this.maxDeelnames;
	}

	public void setMaxDeelnames(int maxDeelnames) {
		this.maxDeelnames = maxDeelnames;
	}
	
	@Column(name = "afgelast")
	public Boolean isAfgelast() {
		return this.afgelast;
	}

	public void setAfgelast(Boolean afgelast) {
		this.afgelast = afgelast;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	public Set<Vaardigheid> getVaardigheden() {
		return this.vaardigheden;
	}

	public void setVaardigheden(Set<Vaardigheid> vaardigheden) {
		this.vaardigheden = vaardigheden;
	}
	
	@Transient
	public String getStringStartdatum() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(startdatum);
	}
	
	@Transient
	public String getStringEinddatum() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(einddatum);
	}

	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalDeelnames;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((einddatum == null) ? 0 : einddatum.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + maxDeelnames;
		result = prime * result + ((naamTrainer == null) ? 0 : naamTrainer.hashCode());
		result = prime * result + ((opleiding == null) ? 0 : opleiding.hashCode());
		result = prime * result + ((startdatum == null) ? 0 : startdatum.hashCode());
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
		Event other = (Event) obj;
		if (aantalDeelnames != other.aantalDeelnames)
			return false;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (einddatum == null) {
			if (other.einddatum != null)
				return false;
		} else if (!einddatum.equals(other.einddatum))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (maxDeelnames != other.maxDeelnames)
			return false;
		if (naamTrainer == null) {
			if (other.naamTrainer != null)
				return false;
		} else if (!naamTrainer.equals(other.naamTrainer))
			return false;
		if (opleiding == null) {
			if (other.opleiding != null)
				return false;
		} else if (!opleiding.equals(other.opleiding))
			return false;
		if (startdatum == null) {
			if (other.startdatum != null)
				return false;
		} else if (!startdatum.equals(other.startdatum))
			return false;
		return true;
	}

}
