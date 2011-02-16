package ma.onda.reclamations;

public class ServiceParser {

	public ServiceParser() {

	}

	/*
	 * Parse different zones and returns the appropriate service
	 * 
	 * @param zone
	 * 
	 * @return service
	 */
	public String parseService(String zone) {

		String service = null;

		if (zone.equals("Douane") || zone.equals("Police")
				|| zone.equals("Embarquement")) {
			service = "Service d'immigration";
		}

		if (zone.equals("Comptoirs d'enregistrement")) {
			service = "Compagnies Aériennes";
		}

		if (zone.equals("Comptoirs d'informations")) {
			service = "Service HT/MT";
		}

		if (zone.equals("Blocs sanitaires")) {
			service = "ONDA";
		}

		if (zone.equals("Hall Public Départ")) {
			service = "ONDA";
		}

		if (zone.equals("Commerces")) {
			service = "ONDA";
		}

		if (zone.equals("Restauration")) {
			service = "ONDA";
		}

		if (zone.equals("Comptoir d'informations touristiques")) {
			service = "ONDA";
		}
		
		if (zone.equals("Parkings")){
			service = "ONDA";
		}

		return service;
	}

}
