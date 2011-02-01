package com.example.reclaadmin;


import org.vaadin.console.Console;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Konsole extends VerticalLayout {
	
	
	private Console console;
	private Label label;
	
	/*
	 * Constructor
	 * @param NULL
	 */
	public Konsole(Panel panel) {
		label = new Label();
		label.setCaption("Pour afficher le 'Help', tapez la commande 'help'");
		
		panel.setSizeUndefined();
		panel.setIcon(new ThemeResource("icons/actions/openterm.png"));
		
		buildKonsole();
		
		addComponent(label);
		addComponent(console);
		
		//setComponentAlignment(label, "center");
		setComponentAlignment(console, "center");		
		
		setSpacing(true);
		setMargin(true);
	}
	
	
	/*
	 * Builds the Console
	 * @param NULL
	 */
	public int buildKonsole()
	{
		console = new Console();
		console.setPs("~$ ");
		console.setRows(20);
		console.setCols(70);
		console.setGreeting("Welcome to Decisia Konsole\nType 'help' for more information");
		console.focus(); 
		
		console.addCommand("clear", new Console.Command() {
			
			@Override
			public String getUsage(Console console, String[] argv) {
				return null;
			}
			
			@Override
			public Object execute(Console console, String[] argv) throws Exception {
				console.clear();
				return null;
			}
		});
	
		return 0;
	}
	
	
	

}
