package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import java.sql.Blob;
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
	private Boolean checked;
	private Blob certificaat;
	private String extensie;

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

	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne
	@JoinColumn(name = "pers_id", nullable = false)
	public Personeel getPersoneel() {
		return this.personeel;
	}

	public void setPersoneel(Personeel personeel) {
		this.personeel = personeel;
	}

	@Column(name = "checked")
	public Boolean isChecked() {
		return this.checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Column(name = "certificaat")
	public Blob getCertificaat() {
		return this.certificaat;
	}

	public void setCertificaat(Blob certificaat) {
		this.certificaat = certificaat;
	}
	
	@Column(name = "extensie")
	public String getExtensie() {
		return this.extensie;
	}

	public void setExtensie(String extensie) {
		this.extensie = extensie;
	}

}
