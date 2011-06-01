package com.example.reclaadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import server.DQSPServer;
import server.DQSPServerI;

import com.vaadin.addon.sqlcontainer.SQLContainer;
import com.vaadin.addon.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;
import com.vaadin.data.Property;

import entities.ActionsModel;
import entities.DBConnexion;

@SuppressWarnings("serial")
public class TableauActions extends VerticalLayout{
	
	SimpleDateFormat formatter;
	//Table table;
	Table table;
	Button send;
	ComboBox airports;
	HorizontalLayout hlayout = new HorizontalLayout();
	ReclaadminApplication __app;
	DQSPServer _server = new DQSPServerI();
	ActionsModel actionsModel = new ActionsModel();
	
	JDBCConnectionPool pool;
	SQLContainer container;
	FreeformQuery query;
	
	
	
	public TableauActions(ReclaadminApplication application, final Panel panel)
	{
		__app = application;
		
		panel.setSizeUndefined();
		panel.setCaption("Sélectionnez un aéroport");
		panel.setIcon(new ThemeResource("icons/actions/wizard.png"));
		hlayout.setVisible(false);
		
		formatter = new SimpleDateFormat("d/MM/yyyy");
		table = new Table("Tableau d'actions Entreprise - "+formatter.format(new java.util.Date()));
		table.setVisible(false);
		
		send = new Button("Envoyer");
		send.setIcon(new ThemeResource("icons/actions/mail_send.png"));
		
		airports = new ComboBox();
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		airports.setImmediate(true);
		airports.setNullSelectionAllowed(false);
		airports.setInputPrompt("Aéroport Concerné");
		for(String s : _server.listOfAirports()){
			airports.addItem(s);
		}
		
		//the application must detect which airport is this
		airports.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if (actionsModel.listOfActions(String.valueOf(airports.getValue())).size() != 0) {
					
					panel.setCaption("Tabeau Actions Entreprises");
					
					table.setImmediate(true);
					table.setWidth("100%");
					//clearing the table
					table.removeAllItems();
					
					//----------------------------------------
					table.addContainerProperty("service", String.class	, null);
					table.addContainerProperty("thème", String.class	, null);
					table.addContainerProperty("observations", String.class	, null);
					table.addContainerProperty("action", String.class, null);
					table.addContainerProperty("validation", String.class, null);
					table.addContainerProperty(" ", PopupView.class, null);
					
					// getting a connection
					Connection c = DBConnexion.getConnection();
					String SQLquery = "SELECT m.`service`, m.`theme`,m.`observations`, m.`action`, m.`validation` FROM myActions m WHERE validation='EN COURS' AND idAeroport=? GROUP BY theme";

					try {
						// creating the statement
						PreparedStatement ps = c.prepareStatement(SQLquery);
						ps.setString(1, String.valueOf(airports.getValue()));
						
						ResultSet rs = ps.executeQuery();
						int i =0;
						while(rs.next() && i<actionsModel.listOfActions(String.valueOf(airports.getValue())).size()){
							table.addItem(new Object[]{rs.getString("service"), rs.getString("theme"), rs.getString("observations"),rs.getString("action"), rs.getString("validation"),createPopUpView(table)}, i);
							i++;
						}
						
						ps.close();
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					//------------------------------------------
					table.setSelectable(true);
					table.setWriteThrough(true);
					table.setPageLength(0);
					table.setFooterVisible(true);
					table.setVisible(true);
					table.setColumnReorderingAllowed(true);
					table.setColumnCollapsingAllowed(true);
					hlayout.setVisible(true);
				} else{
					panel.setCaption("Sélectionnez un aéroport");
					table.removeAllItems();
					table.setVisible(false);
					hlayout.setVisible(false);
					__app.getMainWindow().showNotification("Notification","Pas d'actions pour cet aéroport",Window.Notification.TYPE_TRAY_NOTIFICATION);
				}

			}
		});
		
			
		addComponent(airports);
		setComponentAlignment(airports, "right");
		
		
		table.setColumnIcon("service", new ThemeResource("icons/actions/power.png"));
		table.setColumnIcon("thème", new ThemeResource("icons/actions/misc.png"));
		table.setColumnIcon("observations", new ThemeResource("icons/actions/messagebox_warning.png"));
		table.setColumnIcon("action", new ThemeResource("icons/actions/redo.png"));
		table.setColumnIcon("validation", new ThemeResource("icons/actions/apply.png"));
		table.setColumnIcon(" ", new ThemeResource("icons/actions/down.png"));
		
		setMargin(true);
		//setSpacing(true);
		
		addComponent(table);
		
		hlayout.setSpacing(true);
		hlayout.setMargin(true);

		
		//adding listener to buttons	
		send.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				__app.getMainWindow().showNotification("Notification","Message délivré au DQSP",Window.Notification.TYPE_TRAY_NOTIFICATION);
			}
		});
		
		hlayout.addComponent(send);
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
	}
	
	
	
	/*
	 * Gets a Container data source
	 * @param airport
	 * @return a container with items
	 */
	public SQLContainer getActionsContainer(String airport) {
		
		String sQuery = "SELECT service,theme,action,validation FROM myActions WHERE validation='EN COURS' AND idAeroport='"+airport+"'";
		
		try {
			pool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/pl", "root", "0");
			query = new FreeformQuery(sQuery, pool, "id");
			container = new SQLContainer(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return container;
	}
	
	
	/*
	 * Returns a PopupView with a ComboBox inside it
	 * @param table
	 */
	public PopupView createPopUpView(final Table table){
		
		Panel p = new Panel();
		p.setSizeUndefined();
		
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setSpacing(true);
		hlayout.setMargin(true);
		
		
		final ComboBox validation = new ComboBox();
		validation.setImmediate(true);
		validation.setInputPrompt("Choisissez un état");
		validation.setIcon(new ThemeResource("icons/actions/misc.png"));
		validation.setNullSelectionAllowed(false);
		validation.addItem("EN COURS");
		validation.addItem("FAITE");
		hlayout.addComponent(validation);
		hlayout.setComponentAlignment(validation, Alignment.BOTTOM_LEFT);
		
		Button b = new Button();
		b.setDescription("Modifier");
		b.setIcon(new ThemeResource("icons/actions/filesave.png"));
		hlayout.addComponent(b);
		hlayout.setComponentAlignment(b, Alignment.BOTTOM_RIGHT);
		b.addListener(new Button.ClickListener() {
			 
			@Override
			public void buttonClick(ClickEvent event) {
				Object rowId = table.getValue();
				if (!rowId.equals("")){
					String observation = String.valueOf(table.getContainerProperty(rowId, "observations").getValue());
					
					int returnVal = actionsModel.updateValidation(String.valueOf(validation.getValue()), observation);
					if (returnVal == 0){
						table.getContainerProperty(rowId, "validation").setValue(String.valueOf(validation.getValue()));
						__app.getMainWindow().showNotification("Notification","Modification(s) enregistrée(s)", Window.Notification.TYPE_TRAY_NOTIFICATION);
					}
				} else {
					__app.getMainWindow().showNotification("Notification","Sélectionnez la ligne à modifier", Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
			}
		});
		p.addComponent(hlayout);
		
		PopupView view = new PopupView("Modifier",p);
		view.setDescription("Sélectionnez une ligne de la table");
		
		return view;
	}

}
