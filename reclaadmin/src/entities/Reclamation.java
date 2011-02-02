package entities;

public class Reclamation {

	
	private int idPassager;
	private java.sql.Date date;
	private String terminale;
	private String descriptif;
	
	
	public Reclamation(){
		
	}
	
	
	public int getIdPassager() {
		return idPassager;
	}
	
	public void setIdPassager(int idPassager) {
		this.idPassager = idPassager;
	}
	
	public java.sql.Date getDate() {
		return date;
	}
	
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	public String getTerminale() {
		return terminale;
	}
	
	public void setTerminale(String terminale) {
		this.terminale = terminale;
	}
	
	public String getDescriptif() {
		return descriptif;
	}
	
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	
}
