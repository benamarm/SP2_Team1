package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "weblogin", catalog = "SP2Team01")
public class WebUser implements java.io.Serializable {

	private static final long serialVersionUID = -4508378230330051971L;
	private String loginemail;
	private Personeel personeel;

	public WebUser() {
	}

	@Id
	@Column(name = "loginemail", unique = true, nullable = false, length = 30)
	public String getLoginemail() {
		return this.loginemail;
	}

	public void setLoginemail(String loginemail) {
		this.loginemail = loginemail;
	}

	@OneToOne
	@JoinColumn(name = "pers_id", nullable = false)
	public Personeel getPersoneel() {
		return this.personeel;
	}

	public void setPersoneel(Personeel personeel) {
		this.personeel = personeel;
	}
	
}
