package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vaardigheden", catalog = "SP2Team01")
public class Vaardigheid implements java.io.Serializable {

	private static final long serialVersionUID = 3287611026780952906L;
	private Integer vaardigheidId;
	private Event event;
	private Personeel personeel;
	private boolean checked;
	private byte[] certificaat;

	public Vaardigheid() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vaardigheid_id", unique = true, nullable = false)
	public Integer getVaardigheidId() {
		return this.vaardigheidId;
	}

	public void setVaardigheidId(Integer vaardigheidId) {
		this.vaardigheidId = vaardigheidId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id", nullable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pers_id", nullable = false)
	public Personeel getPersoneel() {
		return this.personeel;
	}

	public void setPersoneel(Personeel personeel) {
		this.personeel = personeel;
	}

	@Column(name = "checked", nullable = false)
	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Column(name = "certificaat")
	public byte[] getCertificaat() {
		return this.certificaat;
	}

	public void setCertificaat(byte[] certificaat) {
		this.certificaat = certificaat;
	}

}
