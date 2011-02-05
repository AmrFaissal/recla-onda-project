package com.example.reclaadmin;

import java.sql.SQLException;
import server.DQSPServer;
import server.DQSPServerI;

import com.vaadin.addon.sqlcontainer.SQLContainer;
import com.vaadin.addon.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DefineActions extends VerticalLayout {

	Table table;
	DQSPServer _server;
	ComboBox airports;
	Button edit;
	Button save;
	HorizontalLayout hlayout = new HorizontalLayout();
	JDBCConnectionPool pool;
	SQLContainer container;
	FreeformQuery query;
	ReclaadminApplication __app;

	public DefineActions(Panel panel, ReclaadminApplication application) {

		__app = application;
		
		// panel.setWidth("635px");
		// panel.setHeight("620px");
		panel.setSizeUndefined();
		panel.setIcon(new ThemeResource("icons/actions/misc.png"));

		table = new Table("Définition des Actions");
		table.setVisible(false);
		_server = new DQSPServerI();
		hlayout.setVisible(false);

		edit = new Button("Editer");
		edit.setIcon(new ThemeResource("icons/actions/edit.png"));
		save = new Button("Enregistrer");
		save.setIcon(new ThemeResource("icons/actions/filesave.png"));

		airports = new ComboBox();
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		for (String s : _server.listOfAirports()) {
			airports.addItem(s);
		}

		airports.setInputPrompt("Aéroport Concerné");
		airports.setImmediate(true);
		airports.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if (getActionsContainer(String.valueOf(airports.getValue())).size()!= 0){
					table.setContainerDataSource(getActionsContainer(String.valueOf(airports.getValue())));
					table.setFooterVisible(true);
					table.setPageLength(0);
					table.setVisible(true);
					hlayout.setVisible(true);
				}
				else{
					table.setVisible(false);
					hlayout.setVisible(false);
					__app.getMainWindow().showNotification("Notification", "pas de réclamations pour cet aéroport", Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
			}
		});
		

		addComponent(airports);
		setComponentAlignment(airports, "right");

		table.setWidth("100%");
		table.setSelectable(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);

		table.setColumnIcon("service", new ThemeResource("icons/actions/power.png"));
		table.setColumnIcon("theme", new ThemeResource("icons/actions/misc.png"));
		table.setColumnIcon("observations", new ThemeResource("icons/actions/messagebox_warning.png"));
		table.setColumnIcon("action", new ThemeResource("icons/actions/redo.png"));
		

		setMargin(true);
		setSpacing(true);

		addComponent(table);

		hlayout.setMargin(true);

		hlayout.addComponent(edit);
		hlayout.addComponent(save);

		// adding listener to buttons
		edit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				edit.setEnabled(false);
				table.setEditable(!table.isEditable());
				table.setWriteThrough(true);
			}
		});

		save.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				edit.setEnabled(true);
				table.setEditable(!table.isEditable());
				table.commit();

				try {
					container.commit();
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
	}

	/*
	 * @return a container with items
	 */
	public SQLContainer getActionsContainer(String airport) {

		String sQuery = "SELECT service,theme,observations,action FROM myActions WHERE validation='EN COURS' AND idAeroport='"+airport+"'";
		
		try {
			pool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306/pl", "root", "0");
			query = new FreeformQuery(sQuery, pool, "service");
			//query.setDelegate(new _FreeFormDelegate());
			container = new SQLContainer(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return container;
	}
}
