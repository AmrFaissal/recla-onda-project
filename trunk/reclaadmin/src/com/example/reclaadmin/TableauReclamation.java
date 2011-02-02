package com.example.reclaadmin;

import server.DQSPServer;
import server.DQSPServerI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
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
		rm = new ReclamationModel();
		_server = new DQSPServerI();
		
		airports = new ComboBox();
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		for (String s : _server.listOfAirports()){
			airports.addItem(s);
		}
		
		airports.setInputPrompt("Aéroport Concerné");
		airports.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				//filling out the container
				BeanItemContainer <Reclamation> container = new BeanItemContainer<Reclamation>(Reclamation.class);
				for (Reclamation a : rm.getAirportsReclamations(String.valueOf(airports.getValue())))
				{
					container.addBean(a);
				}
				
				table.setContainerDataSource(container);
				table.setPageLength(0);
				table.setFooterVisible(true);
			}
		});
			
		addComponent(airports);
		setComponentAlignment(airports, "right");
		
		table.setWidth("100%");
		table.setSelectable(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		
		setMargin(true);
		setSpacing(true);
		table.setWriteThrough(false);
		
		addComponent(table);
	}
	

	
	
	
	
}
