package ma.onda.reclamations;

public class ServiceParser {
	
	
	public ServiceParser(){
		
		
	}
	
	
	
	public String parseService(String zone){
		
		String service = null;
		
		if (zone.equals("Douane") || zone.equals("Police") || zone.equals("Embarquement")){
			service = "Service d'immigration";
		}
		
		if (zone.equals("Comptoirs d'enregistrement")){
			service = "Compagnies Aériennes";
		}
		
		if (zone.equals("Comptoirs d'informations")){
			service = "Service HT/MT";
		}
		
		if (zone.equals("Blocs sanitaires")){
			service = "ONDA";
		}
	
		return service;
	}

}
