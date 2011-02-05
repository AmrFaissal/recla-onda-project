package entities;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnalyseModel {
	
	
	
	
	public AnalyseModel()
	{
		
	}
	
	/*
	 * getting a list of all elements
	 */
	public List<AnalyseTable> buildAnalyseTable()
	{
		List<AnalyseTable> resultList = new ArrayList<AnalyseTable>();
		
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//create statement
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nomService,theme,descriptif FROM reclamation GROUP BY theme");
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
			stmt.close();
			
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
	
	/*
	 * update actions
	 */
	public int updateAction( String action, String theme)
	{
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//prepare statement
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE soc.analyseTable SET action=? WHERE theme=?");
			ps.setString(1,action);
			ps.setString(2, theme);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;	
	}
}
