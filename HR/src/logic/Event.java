package logic;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events", catalog = "SP2Team01")
public class Event implements java.io.Serializable {

	private static final long serialVersionUID = 1921441815968252172L;
	private Integer eventId;
	private Adres adres;
	private Opleiding opleiding;
	private String naamTrainer;
	private Date startdatum;
	private Date einddatum;
	private int aantalDeelnames;
	private int maxDeelnames;

	public Event() {
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adres_id", nullable = false)
	public Adres getAdres() {
		return this.adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "opleiding_id", nullable = false)
	public Opleiding getOpleiding() {
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
	public int getAantalDeelnames() {
		return this.aantalDeelnames;
	}

	public void setAantalDeelnames(int aantalDeelnames) {
		this.aantalDeelnames = aantalDeelnames;
	}

	@Column(name = "max_deelnames", nullable = false)
	public int getMaxDeelnames() {
		return this.maxDeelnames;
	}

	public void setMaxDeelnames(int maxDeelnames) {
		this.maxDeelnames = maxDeelnames;
	}

}
