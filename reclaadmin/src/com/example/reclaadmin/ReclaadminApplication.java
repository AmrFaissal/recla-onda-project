package com.example.reclaadmin;

import com.vaadin.Application;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class ReclaadminApplication extends Application {
	
	private Window mainwindow;
	private ViewManager viewManager;
	
	@Override
	public void init() 
	{
		mainwindow = new Window("Admin");
		setMainWindow(mainwindow);
		
		viewManager = new ViewManager(mainwindow);
		viewManager.switchScreen(LoginScreen.class.getName(), new LoginScreen(this));
		
	}
	

	/*
	 * getters & setters for ViewManager
	 */
	public ViewManager getViewManager()
	{
		return viewManager;
	}

	public void setViewManager(ViewManager viewManager) 
	{
		this.viewManager = viewManager;
	}

}
 