package server;



public interface DQSPServer {

	public void addPassager(int idPassager, String sex, String nom,
			String mail, String adresse, String codePostale, String phone,
			String typeReclamateur, String numVol, String prov, String dest,
			String nationalite);

	public void addReclamation(int idPassager, java.sql.Date date, String nomAeroport,
			String terminale, String nomService, String remarque,
			String descriptif);
	
	public java.util.List<String> listOfAirports();

}
