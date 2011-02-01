package com.example.reclaadmin;

import java.util.Random;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class MailingList extends VerticalLayout {
	
	 
	public static Object PERSON_PROPERTY_FIRSTNAME = "Prénom";
	public static Object PERSON_PROPERTY_LASTNAME = "Nom";
	private static String[] firstnames = new String[] { "Ahmed", "Ayman",
        "Karim", "Abedallah", "Mohammed", "Omar", "", "Redouane", "Reda", "Faissal",
        "Rabiaae", "Mohammed", "Youssef", "Othmane", "Nawfal", "Adil", "Yasser",
            "Aziz" };
	private static String[] lastnames = new String[] { "Adali",
        "Soufiani", "Assali", "Zniber", "Sajid", "Aabid", "Sadiq",
        "Firdawssi", "Fawzi", "Khalil", "Berada", "Boudali", "Mousstawli", "Karmim", "Kazimi", "Farid" };
	
	ReclaadminApplication __app;
	Table table;
	HorizontalLayout hlayout = new HorizontalLayout();
	
	
	public MailingList(ReclaadminApplication application)
	{
		__app = application;
		
		setSpacing(true);
		setMargin(true);
		
		table = new Table("Liste des contacts");
		table.setWidth("100%");
		table.setPageLength(19);
		
		addComponent(table);
		
		//connect to data source
		table.setContainerDataSource(getPersonContainer());
		
		
		//generate email link
		table.addGeneratedColumn("Email", new Table.ColumnGenerator() {
			public Component generateCell(Table source, Object itemId, Object columnId) 
			{
                Item item = table.getItem(itemId);
                String fn = (String) item.getItemProperty(PERSON_PROPERTY_FIRSTNAME).getValue();
                String ln = (String) item.getItemProperty(PERSON_PROPERTY_LASTNAME).getValue();
                String email = fn.toLowerCase() + "." + ln.toLowerCase()
                        + "@exemple.com";
                // the Link -component:
                Link emailLink = new Link(email, new ExternalResource("mailto:" + email));
                return emailLink;
            }
        });
		
		
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		
		table.setWriteThrough(false);
		table.setSelectable(true);
		
		//setting the table footer
		table.setFooterVisible(true);
		table.setColumnFooter(PERSON_PROPERTY_FIRSTNAME, "Total");
		table.setColumnFooter("Email", String.valueOf(getPersonContainer().size()));
		
		Button send = new Button("Envoyer");
		send.setIcon(new ThemeResource("icons/actions/mail_replyall.png"));
		send.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				__app.getMainWindow().showNotification("Votre Message est délivré avec succés");
			}
		});
		
		hlayout.setSpacing(true);
		hlayout.setMargin(true);
		
		hlayout.addComponent(send);
		
		final Button button = new Button("Editer");
		button.setIcon(new ThemeResource("icons/actions/edit.png"));
		hlayout.addComponent(button);
		button.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				table.setEditable(!table.isEditable());
				button.setCaption((table.isEditable() ? "Enregistrer" : "Editer"));
				
			}
		});
		
		addComponent(hlayout);	
		setComponentAlignment(hlayout, "right");
	}
	
	
   
   	   
	/*
	 * @return Indexed Container of persons
	 */
	public IndexedContainer getPersonContainer() {
            
		IndexedContainer contactContainer = new IndexedContainer();    
		contactContainer.addContainerProperty(PERSON_PROPERTY_FIRSTNAME, String.class, "");        
		contactContainer.addContainerProperty(PERSON_PROPERTY_LASTNAME,String.class, "");
    	
    	        
		Random r = new Random(5);
		for (int i = 0; i < 50;) {
    	            
			String fn = firstnames[(int) (r.nextDouble() * firstnames.length)];        
			String ln = lastnames[(int) (r.nextDouble() * lastnames.length)];        
			String id = fn + ln;        
			Item item = contactContainer.addItem(id);        
			if (item != null) {    
				i++;
				item.getItemProperty(PERSON_PROPERTY_FIRSTNAME).setValue(fn);
				item.getItemProperty(PERSON_PROPERTY_LASTNAME).setValue(ln);        
			}        
		}        
		return contactContainer;    
	}
}
