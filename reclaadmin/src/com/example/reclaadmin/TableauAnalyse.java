package com.example.reclaadmin;

import java.text.SimpleDateFormat;
import entities.AnalyseModel;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import entities.AnalyseTable;

@SuppressWarnings("serial")
public class TableauAnalyse extends VerticalLayout {
	
	Table table;
	SimpleDateFormat formatter;
	AnalyseModel am;
	Button button;
	ComboBox airports;
	
	
	public TableauAnalyse(Panel panel)
	{
		panel.setWidth("635px");
		panel.setHeight("620px");
		
		formatter = new SimpleDateFormat("d/MM/yyyy");
		table = new Table("Tableau d'analyse - " + formatter.format(new java.util.Date()));
		button = new Button("Editer");
		am = new AnalyseModel();
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
		
		//filling out the container
		BeanItemContainer <AnalyseTable> container = new BeanItemContainer<AnalyseTable>(AnalyseTable.class);
		for (AnalyseTable a : am.getAll())
		{
			container.addBean(a);
		}
		
		table.setContainerDataSource(container);
		table.setPageLength(0);
		table.setFooterVisible(true);
		
		table.setColumnIcon("num", new ThemeResource("icons/actions/power.png"));
		table.setColumnIcon("action", new ThemeResource("icons/actions/misc.png"));
		table.setColumnIcon("theme", new ThemeResource("icons/actions/signature.png"));
		table.setColumnIcon("probleme", new ThemeResource("icons/actions/messagebox_warning.png"));
		
		
		addComponent(table);
		addComponent(button);
		
		button.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				table.setEditable(!table.isEditable());
				button.setCaption((table.isEditable() ? "Enregistrer" : "Editer"));
			}
		});
		
		setComponentAlignment(button, "right");
	}
	
	
	
}
