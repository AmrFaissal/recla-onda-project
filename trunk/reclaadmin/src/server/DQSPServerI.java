package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import entities.DBConnexion;

@SuppressWarnings("serial")
public class DQSPServerI implements DQSPServer, java.io.Serializable {

	@Override
	public void addPassager(int idPassager, String sex, String nom,
			String mail, String adresse, String codePostale, String phone,
			String typeReclamateur, String numVol, String prov, String dest,
			String nationalite) {

		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// preparing the statement
			PreparedStatement ps = c
					.prepareStatement("INSERT INTO passager VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, idPassager);
			ps.setString(2, sex);
			ps.setString(3, nom);
			ps.setString(4, mail);
			ps.setString(5, adresse);
			ps.setString(6, codePostale);
			ps.setString(7, phone);
			ps.setString(8, typeReclamateur);
			ps.setString(9, numVol);
			ps.setString(10, prov);
			ps.setString(11, dest);
			ps.setString(12, nationalite);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> listOfAirports() {

		List<String> list = new ArrayList<String>();

		Connection c = DBConnexion.getConnection();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT a.`nomAeroport` FROM aeroport a ");
			while (rs.next()) {
				list.add(rs.getString("nomAeroport"));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addReclamation(int idPassager, Date date, String nomAeroport,
			String terminale, String nomService, String remarque,
			String descriptif, String theme) {

		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// preparing the statement
			PreparedStatement ps = c
					.prepareStatement("INSERT INTO reclamation VALUES (null,?,?,?,?,?,?,?,?)");
			ps.setInt(1, idPassager);
			ps.setDate(2, date);
			ps.setString(3, nomAeroport);
			ps.setString(4, terminale);
			ps.setString(5, nomService);
			ps.setString(6, remarque);
			ps.setString(7, descriptif);
			ps.setString(8, theme);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addAction(String service, String theme, String descriptif,
			String idAeroport) {

		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// preparing the statement
			PreparedStatement ps = c
					.prepareStatement("INSERT INTO myActions VALUES (?,?,?,default,default,?,null)");
			ps.setString(1, service);
			ps.setString(2, theme);
			ps.setString(3, descriptif);
			ps.setString(4, idAeroport);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> listOfObservationsPerAirport(String airport) {

		List<String> list = new ArrayList<String>();

		Connection c = DBConnexion.getConnection();
		try {
			PreparedStatement ps = c
					.prepareStatement("SELECT m.`observations` FROM myActions m WHERE idAeroport=? AND validation='EN COURS'");
			ps.setString(1, airport);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("observations"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateMyActions(String observations, String action) {

		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// preparing the statement
			PreparedStatement ps = c
					.prepareStatement("UPDATE myActions SET action=? WHERE observations=?");
			ps.setString(1, action);
			ps.setString(2, observations);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public String actionForObservation(String observation) {

		String action = null;

		if (!observation.equals("")) {
			Connection c = DBConnexion.getConnection();
			try {
				PreparedStatement ps = c
						.prepareStatement("SELECT m.`action` FROM myActions m WHERE observations=?");
				ps.setString(1, observation);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					action = rs.getString("action");
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			action = "";
		}
		return action;
	}

	@Override
	public int numberOfAppearancesPerMonth(String remarque, String airport) {

		int returnVal = 0;

		if (!remarque.equals("")) {
			// getting a connection
			Connection c = DBConnexion.getConnection();
			try {
				// preparing the statement
				PreparedStatement ps = c
						.prepareStatement("SELECT COUNT(DISTINCT(MONTH(r.`date`))) numMois FROM reclamation r WHERE remarque=? AND nomAeroport=?");
				ps.setString(1, remarque);
				ps.setString(2, airport);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					returnVal = rs.getInt("numMois");
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public List<String> listOfThemes(String airport) {

		List<String> list = new ArrayList<String>();
		// getting connection
		Connection c = DBConnexion.getConnection();
		try {
			PreparedStatement ps = c
					.prepareStatement("SELECT distinct(r.`remarque`) FROM reclamation r WHERE nomAeroport=?");
			ps.setString(1, airport);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("remarque"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int evolutionsReclamationsParTheme(String airport, String remarque,
			Date date) {

		int returnVal = 0;
		// getting a connection
		Connection c = DBConnexion.getConnection();
		try {
			PreparedStatement ps = c
					.prepareStatement("SELECT count(*) num FROM reclamation WHERE nomAeroport=? AND date=? AND remarque=?");
			ps.setString(1, airport);
			ps.setDate(2, date);
			ps.setString(3, remarque);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				returnVal = rs.getInt("num");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnVal;
	}

	/*
	 * returns the history of the specified airport
	 * 
	 * @param airport
	 * 
	 * @see server.DQSPServer#listOfYears(java.lang.String)
	 */
	@Override
	public List<Integer> listOfYears() {
		List<Integer> years = new ArrayList<Integer>();

		// getting a connection
		Connection con = DBConnexion.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT(YEAR(r.`date`)) history FROM reclamation r");
			while (rs.next()) {
				years.add(rs.getInt("history"));
			}
			// closing
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return years;
	}

	/*
	 * returns number of passenger of an airport
	 * 
	 * @param airport
	 * 
	 * @see server.DQSPServer#numberOfPassengers(java.lang.String)
	 */
	@Override
	public int numberOfPassengers(String airport) {
		int total = 0;

		// getting a connection
		Connection con = DBConnexion.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) num FROM passager p WHERE p.`idAeroport`=? AND  p.`typeReclameur`='Passager'");
			ps.setString(1, airport);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("num");
			}
			// closing
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	/*
	 * returns the number of claims for the specified airport
	 * 
	 * @param airport
	 * 
	 * @see server.DQSPServer#numberOfClaims(java.lang.String)
	 */
	@Override
	public int numberOfClaims(String airport) {
		int total = 0;

		// getting a connection
		Connection con = DBConnexion.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) number FROM reclamation r WHERE r.`nomAeroport`=?");
			ps.setString(1, airport);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("number");
			}
			// closing
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	/*
	 * returns a list of themes that were declared in claims for each airport
	 * @param airport
	 * @see server.DQSPServer#listOfThemesInClaims(java.lang.String)
	 */
	@Override
	public List<String> listOfThemesInClaims() {
		java.util.List<String> list = new java.util.ArrayList<String>();

		// getting a connection
		Connection con = DBConnexion.getConnection();
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("SELECT r.`remarque` FROM reclamation r GROUP BY r.`remarque`");
			while (rs.next()) {
				list.add(rs.getString("remarque"));
			}
			// closing
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}

}
