package logic;

import java.sql.Date;

class Event 
{
	private Opleiding opleiding;
	private Adres adres;
	private Date startDatum;
	private Date eindDatum;
	private Personeel aanmaker;
	private int maxAantal = 1;
	private int minAantal = 9999;
	private int event_id;
	private boolean status = true;
	
	public Opleiding getOpleiding() {
		return opleiding;
	}
	public void setOpleiding(Opleiding opleiding) {
		this.opleiding = opleiding;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public Date getStartDatum() {
		return startDatum;
	}
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}
	public Date getEindDatum() {
		return eindDatum;
	}
	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}
	public Personeel getAanmaker() {
		return aanmaker;
	}
	public void setAanmaker(Personeel aanmaker) {
		this.aanmaker = aanmaker;
	}
	public int getMaxAantal() {
		return maxAantal;
	}
	public void setMaxAantal(int maxAantal) {
		this.maxAantal = maxAantal;
	}
	public int getMinAantal() {
		return minAantal;
	}
	public void setMinAantal(int minAantal) {
		this.minAantal = minAantal;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Event()
	{}
	
	public Event(Opleiding opleiding, Adres adres, Date start, Date eind, Personeel aanmaker, int max, int min, int event, boolean status)
	{
		this.opleiding = opleiding;
		this.adres = adres;
		this.startDatum = start;
		this.eindDatum = eind;
		this.aanmaker = aanmaker;
		this.minAantal = min;
		this.maxAantal = max;
		this.event_id = event;
		this.status = status;
	}
	
	
	public String toString1()
	{
		return "De opleiding " + opleiding + " vindt plaats in " + adres + ".";
	}
	
	public String toString2()
	{
		return "De opleiding " + opleiding + " start op " + startDatum + " en eindigt op " + eindDatum + ".";
	}
	
}
