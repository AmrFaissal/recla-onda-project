package com.example.reclaadmin;

import java.text.SimpleDateFormat;
import java.util.Random;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class TableauActions extends VerticalLayout{
	
	public static Object PERSON_PROPERTY_THEME = "Thème";
	public static Object PERSON_PROPERTY_ACTION = "Action Pris";
	public static Object PERSON_PROPERTY_ETAT = "Validation";
	private static String[] themes = new String[] { "theme1", "theme2",
        "theme3", "theme4", "theme5", "theme6", "theme7", "theme8", "", "",
        "theme9", "theme10", "theme11", "theme12", "theme13", "theme14", "",
            "Aziz" };
	private static String[] actions = new String[] { "action1",
        "action2", "", "action4", "action5", "action6", "action7",
        "", "action9", "action10", "action10", "action11", "action12", "", "action13", "action14" };
	
	Table table;
	SimpleDateFormat formatter;
	Button button;
	Button send;
	ComboBox airports;
	HorizontalLayout hlayout = new HorizontalLayout();
	ReclaadminApplication __app;
	
	
	public TableauActions(ReclaadminApplication application)
	{
		__app = application;
		
		formatter = new SimpleDateFormat("d/MM/yyyy");
		table = new Table("Tableau d'actions Entreprise - "+formatter.format(new java.util.Date()));
		button = new Button("Editer");
		button.setIcon(new ThemeResource("icons/actions/edit.png"));
		send = new Button("Envoyer");
		send.setIcon(new ThemeResource("icons/actions/mail_send.png"));
		
		airports = new ComboBox();
		airports.setIcon(new ThemeResource("icons/actions/identity.png"));
		airports.addItem("Aéroport 1");
		airports.addItem("Aéroport 2");
		airports.addItem("Aéroport 3");
		airports.addItem("Aéroport 4");
		airports.addItem("Aéroport 5");
		
		airports.setInputPrompt("Aéroport Concerné");
			
		addComponent(airports);
		setComponentAlignment(airports, "right");
		
		table.setWidth("100%");
		table.setSelectable(true);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		
		setMargin(true);
		setSpacing(true);
		table.setWriteThrough(false);
		
		//filling the table & footer configuration
		table.setContainerDataSource(getActionsContainer());
		table.setPageLength(17);
		table.setFooterVisible(true);
		
		
		addComponent(table);
		
		
		hlayout.setSpacing(true);
		hlayout.setMargin(true);
		
		hlayout.addComponent(button);
		
		button.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				table.setEditable(!table.isEditable());
				button.setCaption((table.isEditable() ? "Enregistrer" : "Editer"));
			}
		});
		
		hlayout.addComponent(send);
		send.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				__app.getMainWindow().showNotification("Message délivré au DQSP");
			}
		});
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
	}
	
	
	/*
	 * @return Indexed Container of persons
	 */
	public IndexedContainer getActionsContainer() {
            
		IndexedContainer contactContainer = new IndexedContainer();    
		contactContainer.addContainerProperty(PERSON_PROPERTY_THEME, String.class, "");        
		contactContainer.addContainerProperty(PERSON_PROPERTY_ACTION,  String.class, "");
		contactContainer.addContainerProperty(PERSON_PROPERTY_ETAT,  Boolean.class, false);
    	
    	        
		Random r = new Random(5);
		for (int i = 0; i < 50;) {
    	            
			String fn = themes[(int) (r.nextDouble() * themes.length)];        
			String ln = actions[(int) (r.nextDouble() * actions.length)];        
			String id = fn + ln;        
			Item item = contactContainer.addItem(id);        
			if (item != null) {    
				i++;
				item.getItemProperty(PERSON_PROPERTY_THEME).setValue(fn);
				item.getItemProperty(PERSON_PROPERTY_ACTION).setValue(ln);        
			}        
		}        
		return contactContainer;    
	}

}
