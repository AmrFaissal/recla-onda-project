package ma.onda.reclamations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import entities.DBConnexion;

@SuppressWarnings("serial")
public class Descriptif extends VerticalLayout {
	
	TextField details;
	Label label;
	Button okButton;
	
	public Descriptif(){
		label = new Label("Vous voulez ajouter plus de détails?");
		details = new TextField();
		okButton = new Button("Ok");
		
		details.setRows(15);
		details.setColumns(20);
		
		addComponent(label);
		addComponent(details);
		addComponent(okButton);
	}
	
	public String getDescriptif() {
		return String.valueOf(details.getValue());
	}
	
	
	public void updateDescriptif(String desc){
		
		Connection c = DBConnexion.getConnection();
		
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE reclamation SET descriptif=?");
			ps.setString(1, getDescriptif());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
