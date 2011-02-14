package com.example.reclaadmin;

import java.util.HashMap;
import java.util.Stack;

import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

public class ViewManager {

	HashMap<String, Layout> views = new HashMap<String, Layout>();
	Stack<Layout> screenStack = new Stack<Layout>();
	Panel window;

	/*
	 * Constructor
	 * 
	 * @param window
	 */
	public ViewManager(Panel window) {
		this.window = window;
	}

	public void init() {

	}

	/*
	 * Switch current screen to new given screen
	 * 
	 * @param viewName, newView
	 */
	public void switchScreen(String viewName, Layout newView) {
		Layout view;
		if (newView != null) {
			view = newView;
			views.put(viewName, newView);
		} else {
			view = views.get("viewname");
		}
		window.setContent(view);
	}

	/*
	 * Switch to given screen and pushes the current screen to stack The pushed
	 * screen can be switched back to by calling popScreen() method
	 * 
	 * @param viewName, newView
	 */
	public void pushScreen(String viewName, Layout newView) {
		screenStack.push((Layout) window.getContent());
		switchScreen(viewName, newView);
	}

	public void popScreen() {
		window.setContent(screenStack.pop());
	}

	/*
	 * getter for panel 'window'
	 */
	public Panel getWindow() {
		return window;
	}
}
