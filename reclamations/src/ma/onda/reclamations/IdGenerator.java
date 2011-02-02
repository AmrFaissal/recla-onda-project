package ma.onda.reclamations;

public class IdGenerator {
	
	private static int id;
	
	
	static 
	{
		id = (int) (Math.random()*10000);
		
	}
	
	
	/*
	 * generates a random ID
	 * @param NULL
	 */
	public static int generateID()
	{
		return id;
	}

}
