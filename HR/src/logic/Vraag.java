package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vragen", catalog = "SP2Team01")
public class Vraag implements java.io.Serializable {

	private static final long serialVersionUID = 1448250560970047486L;
	private Integer vraagId;
	private Survey survey;
	private String vraag;
	private int inx;

	public Vraag() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vraag_id", unique = true, nullable = false)
	public Integer getVraagId() {
		return this.vraagId;
	}

	public void setVraagId(Integer vraagId) {
		this.vraagId = vraagId;
	}

	@ManyToOne
	@JoinColumn(name = "survey_id", nullable = false)
	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Column(name = "vraag", nullable = false)
	public String getVraag() {
		return this.vraag;
	}

	public void setVraag(String vraag) {
		this.vraag = vraag;
	}

	@Column(name = "inx", nullable = false)
	public int getInx() {
		return this.inx;
	}

	public void setInx(int inx) {
		this.inx = inx;
	}

}
