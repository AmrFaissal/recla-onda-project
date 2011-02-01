package ma.onda.reclamations;

import java.util.Date;

public class GPassager {
	
	private String typeReclamateur;
	private Date date;
	private String nVol="";
	private String provenance="";
	private String destination="";
	
	
	public GPassager()
	{
		
	}

	
	public void setTypeReclamateur(String typeReclamateur) {
		this.typeReclamateur = typeReclamateur;
	}



	public String getTypeReclamateur() {
		return typeReclamateur;
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
 