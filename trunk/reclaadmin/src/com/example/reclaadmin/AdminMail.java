package com.example.reclaadmin;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminMail extends VerticalLayout{
	
	ReclaadminApplication __app;
	CustomLayout custom = new CustomLayout("send_button");
	VerticalLayout vlayout = new VerticalLayout();
	VerticalLayout _vlayout = new VerticalLayout();
	VerticalLayout __vlayout = new VerticalLayout();
	HorizontalLayout hlayout = new HorizontalLayout();
	HorizontalLayout _hlayout = new HorizontalLayout();
	HorizontalLayout __hlayout = new HorizontalLayout();
	HorizontalLayout ___hlayout = new HorizontalLayout();
	HorizontalLayout ____hlayout = new HorizontalLayout();
	
	
	
	public AdminMail(ReclaadminApplication application, Panel panel) 
	{
		__app = application; 
		
		panel.setSizeUndefined();
		panel.setCaption("Email");
		panel.setIcon(new ThemeResource("icons/actions/message.png"));
		setSpacing(false);
		
		hlayout.setSpacing(true);
		hlayout.setMargin(true);
		
		_hlayout.setSpacing(true);

		
		TextField smtp = new TextField("SMTP");
		smtp.setRequired(true);
		smtp.setInputPrompt("msg.onda.ma");
		hlayout.addComponent(smtp);
		
		TextField port = new TextField("Port");
		port.setRequired(true);
		port.setInputPrompt("xxx");
		hlayout.addComponent(port);
		
		addComponent(hlayout);
		
		TextField user = new TextField("Utilisateur");
		user.setRequired(true);
		user.setInputPrompt("admin");
		__hlayout.addComponent(user);
		vlayout.addComponent(__hlayout);
		addComponent(vlayout);
		
		TextField email = new TextField("Email");
		email.setRequired(true);
		email.setInputPrompt("admin@onda.ma");
		_hlayout.addComponent(email);
		
		TextField password = new TextField("Password");
		password.setRequired(true);
		password.setSecret(true);
		_hlayout.addComponent(password);
		
		vlayout.addComponent(_hlayout);
		
		vlayout.setMargin(true);
		addComponent(vlayout);
		
		TextField to = new TextField("To");
		to.setRequired(true);
		___hlayout.addComponent(to);
		_vlayout.addComponent(___hlayout);
		
		TextField subject = new TextField("Subject");
		subject.setWidth("15em");
		____hlayout.addComponent(subject);
		_vlayout.addComponent(____hlayout);
		
		_vlayout.setMargin(true);
		
		RichTextArea body = new RichTextArea("Message");
		body.setWidth("530px");
		body.setWordwrap(true);
		_vlayout.addComponent(body);
		
		addComponent(_vlayout);
		
		Button send = new Button("Envoyer", this, "sendButtonClick");
		send.setIcon(new ThemeResource("icons/actions/mail_send.png"));
		custom.addComponent(send, "send");
		addComponent(custom);
		setComponentAlignment(custom, Alignment.MIDDLE_CENTER);
		setMargin(true);
	}
	
	
	/*
	 * Handler for send button
	 */
	public void sendButtonClick(Button.ClickEvent event)
	{
		__app.getMainWindow().showNotification("Message Envoyé avec Succès !");	
	}

}
