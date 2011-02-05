package com.example.reclaadmin;

import server.DQSPServer;
import server.DQSPServerI;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import entities.Reclamation;
import entities.ReclamationModel;

@SuppressWarnings("serial")
public class TableauReclamation extends VerticalLayout {
	
	Table table;
	ReclamationModel rm;
	ComboBox airports;
	DQSPServer _server;
	
	public TableauReclamation(Panel panel){
		
		//panel.setWidth("635px");
		//panel.setHeight("620px");
		panel.setSizeUndefined();
		
		table = new Table("Tableau de Réclamation");
		table.setVisible(false);
		rm = new ReclamationModel();
		_server = new DQSPServerI();
		
		airports = new ComboBox();
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		for (String s : _server.listOfAirports()){
			airports.addItem(s);
		}
		
		airports.setInputPrompt("Aéroport Concerné");
		airports.setImmediate(true);
		airports.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				//filling out the container
				IndexedContainer container = new IndexedContainer();
				container.addContainerProperty("ID Passager", Integer.class, null);
				container.addContainerProperty("Date", java.sql.Date.class, null);
				container.addContainerProperty("Terminale", String.class, null);
				container.addContainerProperty("Descriptif", String.class, null);
				for (Reclamation a : rm.getAirportsReclamations(String.valueOf(airports.getValue())))
				{
					Item item = container.addItem(a);
					item.getItemProperty("ID Passager").setValue(a.getIdPassager());
					item.getItemProperty("Date").setValue(a.getDate());
					item.getItemProperty("Terminale").setValue(a.getTerminale());
					item.getItemProperty("Descriptif").setValue(a.getDescriptif());
				}
				
				table.setContainerDataSource(container);
				table.setFooterVisible(true);
				table.setColumnFooter("ID Passager", "Total");
				table.setColumnFooter("Descriptif", String.valueOf(rm.getAirportsReclamations(String.valueOf(airports.getValue())).size()));
				table.setPageLength(0);
				table.setVisible(true);
			}
		});
			
		addComponent(airports);
		setComponentAlignment(airports, "right");
		
		table.setWidth("100%");
		table.setSelectable(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		
		table.setColumnIcon("ID Passager", new ThemeResource("icons/actions/power.png"));
		table.setColumnIcon("Date", new ThemeResource("icons/actions/misc.png"));
		table.setColumnIcon("Terminale", new ThemeResource("icons/actions/signature.png"));
		table.setColumnIcon("Descriptif", new ThemeResource("icons/actions/messagebox_warning.png"));
		
		setMargin(true);
		setSpacing(true);
		
		addComponent(table);
	}
	

	
	
	
	
}
