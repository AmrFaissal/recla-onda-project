package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionsModel {

	/*
	 * Constructor
	 */
	public ActionsModel() {

	}

	/*
	 * Returns a list of actions
	 * 
	 * @param airport
	 * 
	 * @return list
	 */
	public List<AnalyseTable> listOfActions(String airport) {

		List<AnalyseTable> list = new ArrayList<AnalyseTable>();
		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// creating the statement
			PreparedStatement ps = c
					.prepareStatement("SELECT service,theme,action,validation FROM myActions WHERE validation='EN COURS' AND idAeroport=?");
			ps.setString(1, airport);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AnalyseTable at = new AnalyseTable();
				at.setService(rs.getString("service"));
				at.setTheme(rs.getString("theme"));
				at.setAction(rs.getString("action"));
				list.add(at);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * updates the 'validation' attribute in Enterprise Actions
	 * 
	 * @param newValue, observations
	 */
	public int updateValidation(String newValue, String observations) {

		// getting a connection
		Connection c = DBConnexion.getConnection();
		try {
			PreparedStatement ps = c
					.prepareStatement("UPDATE myActions SET validation=? WHERE observations=?");
			ps.setString(1, newValue);
			ps.setString(2, observations);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
