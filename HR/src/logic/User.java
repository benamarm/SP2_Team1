package logic;

import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "applogin", catalog = "SP2Team01")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -5402123904414274306L;
	private String loginemail;
	private String naam;
	private String achternaam;
	private String positie;

	public User() {
	}

	@Id
	@Column(name = "loginemail", unique = true, nullable = false, length = 30)
	public String getLoginemail() {
		return this.loginemail;
	}

	public void setLoginemail(String loginemail) {
		this.loginemail = loginemail;
	}

	@Column(name = "naam", nullable = false, length = 30)
	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Column(name = "achternaam", nullable = false, length = 30)
	public String getAchternaam() {
		return this.achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	@Column(name = "positie", nullable = false, length = 5)
	public String getPositie() {
		return this.positie;
	}

	public void setPositie(String positie) {
		this.positie = positie;
	}
	
	@Transient
	public String getVolleNaam() {
		return naam + " " + achternaam;
	}
	
	@Transient
	public static String generatePassword() {
		String password = "";
		Random r = new Random();
		for (int i = 0; i < 8; i++) {
			password += (i % 2 == 0 ? String.valueOf((char) (r.nextInt(26) + 'a')) : (r.nextInt(10)));
		}
		return password;
	}	
	
}
