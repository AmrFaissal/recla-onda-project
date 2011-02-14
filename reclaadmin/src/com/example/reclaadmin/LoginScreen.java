package com.example.reclaadmin;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class LoginScreen extends VerticalLayout implements java.io.Serializable {

	ReclaadminApplication _app;
	InetAddress addr;

	/*
	 * Constructor
	 * 
	 * @param application
	 */
	public LoginScreen(ReclaadminApplication application) throws UnknownHostException {
		_app = application;
		setMargin(true);
		 
		addr = InetAddress.getLocalHost();

		CustomLayout custom = new CustomLayout("login");
		addComponent(custom);

		Panel loginPanel = new Panel("Login");
		loginPanel.setSizeUndefined();
		loginPanel.setStyleName("mystyle");
		LoginForm loginForm = new LoginForm();
		loginForm.addListener(new LoginListener() {

			@Override
			public void onLogin(LoginEvent event) {
				String username = event.getLoginParameter("username");
				String password = event.getLoginParameter("password");

				if (username.equals("admin") && password.equals("admin") && addr.getHostName().equals("stroika")) {
					_app.getViewManager().switchScreen(
							AdminView.class.getName(), new AdminView(_app));
				} else if (username.equals("respo") && password.equals("admin") && addr.getHostName().equals("stroika")) {
					_app.getViewManager().switchScreen(
							RespView.class.getName(), new RespView(_app));
				} else {

					_app.getMainWindow().showNotification("Login Error",
							"<br>Please verify your username or password",
							Window.Notification.TYPE_ERROR_MESSAGE);
				}
			}

		});

		loginPanel.addComponent(loginForm);
		custom.addComponent(loginPanel, "loginPanel");

	}
}
