package com.example.reclaadmin;

import java.text.SimpleDateFormat;

import server.DQSPServer;
import server.DQSPServerI;
import entities.AnalyseModel;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

import entities.AnalyseTable;

@SuppressWarnings("serial")
public class TableauAnalyse extends VerticalLayout {
	
	Table table;
	SimpleDateFormat formatter;
	AnalyseModel am;
	Button save;
	Button edit;
	ComboBox airports;
	DQSPServer _server;
	HorizontalLayout hlayout;
	ReclaadminApplication __app;
	
	public TableauAnalyse(ReclaadminApplication application, final Panel panel)
	{
		__app = application;
		//panel.setWidth("635px");
		//panel.setHeight("620px");
		panel.setSizeUndefined();
		panel.setCaption("Sélectionnez un aéroport");
		panel.setIcon(new ThemeResource("icons/actions/toggle_log.png"));
		
		formatter = new SimpleDateFormat("d/MM/yyyy");
		table = new Table("Tableau d'analyse - " + formatter.format(new java.util.Date()));
		table.setVisible(false);
		edit = new Button("Editer");
		save = new Button("Enregistrer");
		hlayout = new HorizontalLayout();
		hlayout.setVisible(false);
		am = new AnalyseModel();
		_server = new DQSPServerI();
		
		airports = new ComboBox();
		airports.setNullSelectionAllowed(false);
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		for (String s : _server.listOfAirports()){
			airports.addItem(s);
		}
		
		airports.setInputPrompt("Aéroport Concerné");
		airports.setImmediate(true);
		addComponent(airports);
		setComponentAlignment(airports, "right");
		
		airports.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				//filling out the container
				IndexedContainer container = new IndexedContainer(); 
				container.addContainerProperty("Service", String.class, null);
				container.addContainerProperty("Thème", String.class, null);
				container.addContainerProperty("Nombre de Réclamations", Integer.class, null);
				container.addContainerProperty("Observations", String.class, null);
				
				if (am.buildAnalyseTable(String.valueOf(airports.getValue())).size() != 0)
				{
					panel.setCaption("Tableau d'analyse");
					for (AnalyseTable at : am.buildAnalyseTable(String.valueOf(airports.getValue())))
					{
						Item item = container.addItem(at);
						item.getItemProperty("Service").setValue(at.getService());
						item.getItemProperty("Thème").setValue(at.getTheme());
						item.getItemProperty("Nombre de Réclamations").setValue(at.getNbreRecla());
						item.getItemProperty("Observations").setValue(at.getDescriptif());
					}
					table.setContainerDataSource(container);
					table.setPageLength(0);
					table.setFooterVisible(true);
					table.setVisible(true);
				} else {
					panel.setCaption("Sélectionnez un aéroport");
					table.setVisible(false);
					__app.getMainWindow().showNotification("Notification", "Pas de réclamations pour cet aéroport", Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
				//hlayout.setVisible(true);
			}
		});
		
		table.setWidth("100%");
		table.setSelectable(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		
		setMargin(true);
		setSpacing(true);
		table.setWriteThrough(false);
		
		table.setColumnIcon("Service", new ThemeResource("icons/actions/power.png"));
		table.setColumnIcon("Thème", new ThemeResource("icons/actions/misc.png"));
		table.setColumnIcon("Nombre de Réclamations", new ThemeResource("icons/actions/signature.png"));
		table.setColumnIcon("Observations", new ThemeResource("icons/actions/messagebox_warning.png"));
		
		
		addComponent(table);
		
		hlayout.setMargin(true);
		hlayout.setSpacing(true);
		hlayout.addComponent(save);
		hlayout.addComponent(edit);
		
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
		
		edit.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				table.setEditable(!table.isEditable());
				edit.setEnabled(false);
			}
		});
		
		save.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				edit.setEnabled(true);
				table.setEditable(!table.isEditable());
			}
		});
		
	}
	
	
	
}
