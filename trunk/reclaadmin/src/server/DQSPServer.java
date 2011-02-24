package server;

public interface DQSPServer {

	public void addPassager(int idPassager, String sex, String nom,
			String mail, String adresse, String codePostale, String phone,
			String typeReclamateur, String numVol, String prov, String dest,
			String nationalite);

	public void addReclamation(int idPassager, java.sql.Date date,
			String nomAeroport, String terminale, String nomService,
			String remarque, String descriptif, String theme);

	public void addAction(String service, String theme, String descriptif,
			String idAeroport);

	public java.util.List<String> listOfObservationsPerAirport(String airport);

	public int updateMyActions(String observations, String action);

	public String actionForObservation(String observation);

	public java.util.List<String> listOfAirports();

	public java.util.List<String> listOfThemes(String airport);
	
	public java.util.List<String> listOfThemesInClaims();

	public java.util.List<Integer> listOfYears();

	public int numberOfAppearancesPerMonth(String remarque, String airport);

	public int evolutionsReclamationsParTheme(String airport, String remarque,
			java.sql.Date date);

	public int numberOfPassengers(String airport);

	public int numberOfClaims(String airport);

}
