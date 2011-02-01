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
	public List<AnalyseTable> getAll()
	{
		List<AnalyseTable> resultList = new ArrayList<AnalyseTable>();
		
		//getting a connection
		Connection c = DBConnexion.getConnection();
		
		//create statement
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT num, theme, probleme FROM soc.analyseTable");
			while(rs.next())
			{
				AnalyseTable at = new AnalyseTable();
				at.setNum(rs.getInt("num"));
				at.setTheme(rs.getString("theme"));
				at.setProbleme(rs.getString("probleme"));
				resultList.add(at);
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return resultList;
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
