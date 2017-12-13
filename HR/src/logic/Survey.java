package logic;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "surveys", catalog = "SP2Team01")
public class Survey implements java.io.Serializable {

	private static final long serialVersionUID = 4151423501873463579L;
	private Integer surveyId;
	private Opleiding opleiding;
	private String titel;
	private Set<Vraag> vragen = new HashSet<Vraag>(0);

	public Survey() {
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "survey_id")
	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	@ManyToOne
	@JoinColumn(name = "opleiding_id")
	public Opleiding getOpleiding() {
		return opleiding;
	}

	public void setOpleiding(Opleiding opleiding) {
		this.opleiding = opleiding;
	}

	@Column(name = "titel")
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "survey")
	public Set<Vraag> getVragen() {
		return vragen;
	}

	public void setVragen(Set<Vraag> vragen) {
		this.vragen = vragen;
	}	


}
