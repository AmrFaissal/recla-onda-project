package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DQSPServerI implements DQSPServer {

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
	public void addAction(String service, String theme, String descriptif, String idAeroport) {

		// getting a connection
		Connection c = DBConnexion.getConnection();

		try {
			// preparing the statement
			PreparedStatement ps = c
					.prepareStatement("INSERT INTO myActions VALUES (?,?,?,default,default,?)");
			ps.setString(1, service);
			ps.setString(2, theme);
			ps.setString(3, descriptif);
			ps.setString(4, idAeroport);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
