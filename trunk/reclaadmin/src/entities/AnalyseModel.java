package entities;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalyseModel {
	
	
	
	
	public AnalyseModel()
	{
		
	}
	
	/*
	 * getting a list of all elements
	 */
	public List<AnalyseTable> buildAnalyseTable(String airport)
	{
		List<AnalyseTable> resultList = new ArrayList<AnalyseTable>();
		String query = "SELECT nomService,theme,descriptif FROM reclamation WHERE nomAeroport=? GROUP BY theme";
		
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//create statement
		try {
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, airport); 
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				AnalyseTable at = new AnalyseTable();
				at.setService(rs.getString("nomService"));
				at.setTheme(rs.getString("theme"));
				at.setNbreRecla(new AnalyseModel().nombreReclaParTheme(rs.getString("theme")));
				at.setDescriptif(rs.getString("descriptif"));
				resultList.add(at);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return resultList;
	}
	
	public int nombreReclaParTheme(String theme){
		
		int nombre = 0;
		
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//prepare statement
		try {
			PreparedStatement ps = c.prepareStatement("SELECT count(*) num FROM reclamation where theme=?");
			ps.setString(1,theme);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				nombre = rs.getInt("num");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nombre;
	}
}
