package logic;

import static javax.persistence.GenerationType.IDENTITY;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "surveyPublicaties", catalog = "SP2Team01")
public class Publicatie implements java.io.Serializable {

	private static final long serialVersionUID = -8209665843159704871L;
	private Integer publicatieId;
	private Survey survey;
	private Date tot;
	private Boolean actief;
	
	public Publicatie() {
		
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "publicatie_id")
	public Integer getPublicatieId() {
		return publicatieId;
	}

	public void setPublicatieId(Integer publicatieId) {
		this.publicatieId = publicatieId;
	}

	@ManyToOne
	@JoinColumn(name = "survey_id", nullable = false)
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	@Column(name = "tot")
	public Date getTot() {
		return tot;
	}

	public void setTot(Date tot) {
		this.tot = tot;
	}

	@Column(name = "actief")
	public Boolean getActief() {
		return actief;
	}

	public void setActief(Boolean actief) {
		this.actief = actief;
	}
	
	@Transient
	public String getStringTot() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(tot);
	}
	
}