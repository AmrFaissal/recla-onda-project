package com.example.reclaadmin;

import server.DQSPServer;
import server.DQSPServerI;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class _DefineActions extends VerticalLayout{
	
	
	ComboBox observations;
	ComboBox airports;
	TextField actions;
	Button save;
	Button edit;
	ReclaadminApplication __app;
	DQSPServer _server;
	HorizontalLayout hlayout = new HorizontalLayout();
	
	public _DefineActions(final Panel panel, ReclaadminApplication application){
		

		__app = application;
		
		// panel.setWidth("635px");
		// panel.setHeight("620px");
		panel.setSizeUndefined();
		panel.setIcon(new ThemeResource("icons/actions/misc.png"));
		panel.setCaption("Sélectionnez un aéroport");

		_server = new DQSPServerI();
		hlayout.setVisible(false);

		edit = new Button("Editer");
		edit.setIcon(new ThemeResource("icons/actions/edit.png"));
		save = new Button("Enregistrer");
		save.setIcon(new ThemeResource("icons/actions/filesave.png"));
		
		actions = new TextField("Action(s) Prise(s)");
		actions.setIcon(new ThemeResource("icons/actions/apply.png"));
		actions.setWordwrap(true);
		actions.setVisible(false);
		actions.setNullRepresentation("");
		actions.setRows(15);
		actions.setColumns(40);
		

		airports = new ComboBox();
		airports.setNullSelectionAllowed(false);
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		for (String s : _server.listOfAirports()) {
			airports.addItem(s);
		}
		
		observations = new ComboBox();
		observations.setVisible(false);
		observations.setIcon(new ThemeResource("icons/actions/2downarrow.png"));
		observations.setImmediate(true);
		observations.setNullSelectionAllowed(false);
		observations.setSizeFull();
		observations.setInputPrompt("Sélectionnez une réclamation");

		airports.setInputPrompt("Aéroport Concerné");
		airports.setImmediate(true);
		airports.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				observations.removeAllItems();
				if (_server.listOfObservationsPerAirport(String.valueOf(airports.getValue())).size() != 0) {
					panel.setCaption("Sélectionnez une réclamation");
					observations.setVisible(true);
					for (String s : _server.listOfObservationsPerAirport(String.valueOf(airports.getValue()))){
						observations.addItem(s);
					}
				} else {
					panel.setCaption("Sélectionnez un aéroport");
					observations.setVisible(false);
					actions.setVisible(false);
					hlayout.setVisible(false);
					__app.getMainWindow().showNotification("Notification", "Pas de réclamations pour cet aéroport", Window.Notification.TYPE_TRAY_NOTIFICATION);
				}	
			}
		});
		

		addComponent(airports);
		setComponentAlignment(airports, "left");
		
		
		observations.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				actions.setVisible(true);
				//set the action(s) for each observation
				if (actions.isReadOnly() == false){
					actions.setValue(_server.actionForObservation(String.valueOf(observations.getValue())));
					hlayout.setVisible(true);
				} else {
					hlayout.setVisible(false);
				}
			}
		});
		addComponent(observations);
		setComponentAlignment(observations,"left");

		
		addComponent(actions);
		setComponentAlignment(actions,"left");
		
		setMargin(true);
		setSpacing(true);

		//hlayout.setMargin(true);
		hlayout.setSpacing(true);

		hlayout.addComponent(edit);
		hlayout.addComponent(save);

		// adding listener to buttons
		edit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				edit.setEnabled(false);
				actions.setReadOnly(false);
			}
		});

		save.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				edit.setEnabled(true);
				int returnVal = _server.updateMyActions(String.valueOf(observations.getValue()), String.valueOf(actions.getValue()));
				if (returnVal == 0){
					__app.getMainWindow().showNotification("Notification", "Action(s) Ajoutée(s)", Window.Notification.TYPE_TRAY_NOTIFICATION);
				}
				actions.setReadOnly(true);
			}
		}); 
		
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
	}

}
