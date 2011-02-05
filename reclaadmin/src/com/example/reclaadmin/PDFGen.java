package com.example.reclaadmin;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	private static Font CATFONT = new Font(Font.getFamily(""), 16, Font.ITALIC);


	/*
	 * Constructor
	 */
	public PDFGen() {

	}

	/*
	 * Creates a PdfTable
	 * 
	 * @param NULL
	 */
	public PdfPTable createTable() {

		// Creation of PdfTable with 3 columns
		final PdfPTable table = new PdfPTable(3);

		final PdfPCell cell1 = new PdfPCell(new Paragraph("Service", CATFONT));
		final PdfPCell cell2 = new PdfPCell(new Paragraph("Réclamation", CATFONT));
		final PdfPCell cell3 = new PdfPCell(new Paragraph("Action", CATFONT));
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);

		// Filling the table from database
		Connection c = DBConnexion.getConnection();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT nomService, descriptif, action FROM reclamation");

			while (rs.next()) {
				table.addCell(rs.getString("nomService"));
				table.addCell(rs.getString("descriptif"));
				table.addCell(rs.getString("action"));
				table.setSpacingBefore(15f);
			}	
			//closing
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return table;
	}

	/*
	 * Adds an empty line
	 */
	public void ajouterLigneVide(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	/*
	 * Generates the PV__PDF
	 */
	public int generate() {

		Document doc = null;
		Paragraph p1 = new Paragraph(
				"                             A   Monsieur le directeur de l'aéroport", CATFONT);
		ajouterLigneVide(p1, 2);
		Paragraph p2 = new Paragraph("Objet : Signature à apposer sur Procés-Verbal", CATFONT);
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
		ajouterLigneVide(p5, 1);
		Paragraph p6 = new Paragraph("Nous vous prions d'agréer, Monsieur, nos salutations distinguées.", CATFONT);

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
		String file = "/home/matrix/Desktop/PV_"+formatter.format(new Date())+".pdf";

		try {
			
			doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_5);
			
			//opening the document for writing
			doc.open();
			
			Image _logo = Image.getInstance("logo.gif");
			_logo.setAlignment(1);
			_logo.scalePercent(50);
			
			Image logo = Image.getInstance("slogon.gif");
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
			doc.add(createTable());
			doc.add(p6);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Closing the document
		//doc.close();

		return 0;
	}

}
