package logic;

public class Vraag {
	private int vraag_id;
	private String vraag;
	private String onderwerp;
	
	public Vraag(int vraag_id, String vraag, String onderwerp) {
		this.vraag_id = vraag_id;
		this.vraag = vraag;
		this.onderwerp = onderwerp;
	}
	
	public int getVraag_id() {
		return vraag_id;
	}
	public void setVraag_id(int vraag_id) {
		this.vraag_id = vraag_id;
	}
	public String getVraag() {
		return vraag;
	}
	public void setVraag(String vraag) {
		this.vraag = vraag;
	}
	public String getOnderwerp() {
		return onderwerp;
	}
	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public String toString(int vraag_id) {
		String vraag = "Vraag " + this.vraag_id + ": " + this.vraag + "\n Onderwerp: " + this.onderwerp + ".";
		return vraag;
	}
	
	
}