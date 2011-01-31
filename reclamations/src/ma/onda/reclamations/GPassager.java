package ma.onda.reclamations;

import java.util.Date;

public class GPassager {
	
	private boolean passager;
	private boolean usager;
	private boolean attendant;
	private boolean accompagnateur;
	private Date date;
	private String nVol="";
	private String provenance="";
	private String destination="";
	
	
	public GPassager()
	{
		
	}


	public boolean isPassager() {
		return passager;
	}


	public void setPassager(boolean passager) {
		this.passager = passager;
	}


	public boolean isUsager() {
		return usager;
	}


	public void setUsager(boolean usager) {
		this.usager = usager;
	}


	public boolean isAttendant() {
		return attendant;
	}


	public void setAttendant(boolean attendant) {
		this.attendant = attendant;
	}


	public boolean isAccompagnateur() {
		return accompagnateur;
	}


	public void setAccompagnateur(boolean accompagnateur) {
		this.accompagnateur = accompagnateur;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getnVol() {
		return nVol;
	}


	public void setnVol(String nVol) {
		this.nVol = nVol;
	}


	public String getProvenance() {
		return provenance;
	}


	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	

}
 