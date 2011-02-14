package com.example.reclaadmin;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import entities.DBConnexion;

public class PDFGen {

	static Font CATFONT = new Font(Font.getFamily(""), 13, Font.ITALIC);
	static Font BFONT = new Font(Font.getFamily(""), 12, Font.BOLD);

	/*
	 * Constructor
	 */
	public PDFGen() {

	}

	/*
	 * Creates a PdfTable
	 * 
	 * @param airport
	 */
	public PdfPTable createTable(String airport) {

		// Creation of PdfTable with 4 columns
		final PdfPTable table = new PdfPTable(4);

		final PdfPCell cell1 = new PdfPCell(new Paragraph("Service", BFONT));
		cell1.setGrayFill(0.3f);
		final PdfPCell cell2 = new PdfPCell(new Paragraph("Thème", BFONT));
		cell2.setGrayFill(0.3f);
		final PdfPCell cell3 = new PdfPCell(
				new Paragraph("Observations", BFONT));
		cell3.setGrayFill(0.3f);
		final PdfPCell cell4 = new PdfPCell(new Paragraph("Action(s)", BFONT));
		cell4.setGrayFill(0.3f);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);

		table.setComplete(true);
		table.setWidthPercentage(100);

		// Filling the table from database
		String query = "SELECT m.`service`, m.`theme`,m.`observations`, m.`action` FROM myActions m WHERE idAeroport=? GROUP BY theme";

		// getting a connection
		Connection c = DBConnexion.getConnection();

		// create statement
		try {
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, airport);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				table.addCell(rs.getString("service"));
				table.addCell(rs.getString("theme"));
				table.addCell(rs.getString("observations"));
				table.addCell(rs.getString("action"));
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return table;
	}

	/*
	 * Adds a number of empty line(s)
	 * 
	 * @param paragraph, number
	 */
	public void ajouterLigneVide(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	/*
	 * Generates the PV__PDF
	 * 
	 * @param airport
	 */
	public int generate(String airport) {

		Document doc = null;
		Paragraph p1 = new Paragraph(
				"                             A   Monsieur le directeur de l'aéroport",
				CATFONT);
		ajouterLigneVide(p1, 2);
		Paragraph p2 = new Paragraph(
				"Objet : Signature à apposer sur Procés-Verbal", CATFONT);
		ajouterLigneVide(p2, 2);
		Paragraph p3 = new Paragraph("Monsieur,", CATFONT);
		ajouterLigneVide(p3, 1);
		Paragraph p4 = new Paragraph(
				"Nous avons élaboré le rapport des actions à faire suites aux réclamations que vous avez reçu dans votre aéroport. ",
				CATFONT);
		ajouterLigneVide(p4, 1);
		Paragraph p5 = new Paragraph(
				"Comme, nous vous remercions aux efforts que vous fournissez pour collaborer à ce travail. Nous insistons sur le faite de nous envoyer un rapport détaillé englobant toutes les Actions Entreprises Parmi celles incluent dans le tableau suivant :",
				CATFONT);
		ajouterLigneVide(p5, 2);
		Paragraph p6 = new Paragraph(
				"Nous vous prions d'agréer, Monsieur, nos salutations distinguées.",
				CATFONT);

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
		String file = "/home/matrix/Desktop/pvs/PV_" + formatter.format(new Date())
				+ "_" + airport + ".pdf";

		try {

			doc = new Document();

			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
					file));
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_6);

			// opening the document for writing
			doc.open();

			Image _logo = Image
					.getInstance("/home/matrix/apps/workspace/reclaadmin/WebContent/VAADIN/themes/reindeer/layouts/images/logo.gif");
			_logo.setAlignment(1);
			_logo.scalePercent(50);

			Image logo = Image
					.getInstance("/home/matrix/apps/workspace/reclaadmin/WebContent/VAADIN/themes/reindeer/layouts/images/slogon.gif");
			logo.setAlignment(1);
			logo.scalePercent(50);

			Paragraph p0 = new Paragraph();
			p0.add(_logo);
			p0.add(logo);

			ajouterLigneVide(p0, 3);

			doc.add(p0);
			doc.add(p1);
			doc.add(p2);
			doc.add(p3);
			doc.add(p4);
			doc.add(p5);
			doc.add(createTable(airport));
			doc.add(p6);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing the document
		doc.close();

		return 0;
	}
}
