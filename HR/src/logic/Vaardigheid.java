package logic;

import java.util.ArrayList;
import java.sql.Date;

public class Vaardigheid {
	private int vaard_id;
	private String vaardnaam;
	private String beschrijving;
	private Boolean certificaat = false;

	public Vaardigheid(int vaard_id, String vaardnaam, String beschrijving) {
		super();
		this.vaard_id = vaard_id;
		this.vaardnaam = vaardnaam;
		this.beschrijving = beschrijving;
	}

	public int getVaard_id() {
		return vaard_id;
	}

	public void setVaard_id(int vaard_id) {
		this.vaard_id = vaard_id;
	}

	public String getVaardnaam() {
		return vaardnaam;
	}

	public void setVaardnaam(String vaardnaam) {
		this.vaardnaam = vaardnaam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Boolean getCertificaat() {
		return certificaat;
	}

	public void setCertificaat(Boolean certificaat) {
		this.certificaat = certificaat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beschrijving == null) ? 0 : beschrijving.hashCode());
		result = prime * result + vaard_id;
		result = prime * result + ((vaardnaam == null) ? 0 : vaardnaam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vaardigheid))
			return false;
		Vaardigheid other = (Vaardigheid) obj;
		if (beschrijving == null) {
			if (other.beschrijving != null)
				return false;
		} else if (!beschrijving.equals(other.beschrijving))
			return false;
		if (vaard_id != other.vaard_id)
			return false;
		if (vaardnaam == null) {
			if (other.vaardnaam != null)
				return false;
		} else if (!vaardnaam.equals(other.vaardnaam))
			return false;
		return true;
	}

}