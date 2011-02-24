package com.example.reclaadmin;

import java.net.UnknownHostException;

import com.vaadin.Application;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class ReclaadminApplication extends Application {

	Window mainwindow;
	ViewManager viewManager;

	@Override
	public void init() {

		mainwindow = new Window("Admin");
		setMainWindow(mainwindow);
		setTheme("reindeer");
		
		viewManager = new ViewManager(mainwindow);
		try {
			viewManager.switchScreen(LoginScreen.class.getName(),
					new LoginScreen(this));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/*
	 * getters & setters for ViewManager
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}

	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

}
