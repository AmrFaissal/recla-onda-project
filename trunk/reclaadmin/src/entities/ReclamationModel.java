package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReclamationModel {

	
	
	public ReclamationModel(){
		
	}
	
	
	
	public List<Reclamation> getAirportsReclamations(String airport)
	{
		List<Reclamation> resultList = new ArrayList<Reclamation>();
		
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//create statement
		try {
			PreparedStatement ps = c.prepareStatement("SELECT idPassager,date,terminale,descriptif FROM reclamation WHERE nomAeroport=?");
			ps.setString(1, airport);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Reclamation recla = new Reclamation();
				recla.setIdPassager(rs.getInt("idPassager"));
				recla.setDate(rs.getDate("date"));
				recla.setTerminale(rs.getString("terminale"));
				recla.setDescriptif(rs.getString("descriptif"));
				resultList.add(recla);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
	
}
