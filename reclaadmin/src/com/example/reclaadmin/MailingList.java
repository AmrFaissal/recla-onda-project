package com.example.reclaadmin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import entities.DBConnexion;

@SuppressWarnings("serial")
public class MailingList extends VerticalLayout {

	ReclaadminApplication __app;
	Table table;
	HorizontalLayout hlayout = new HorizontalLayout();

	public MailingList(ReclaadminApplication application, Panel panel) {
		__app = application;

		panel.setWidth("625px");
		
		panel.setIcon(new ThemeResource("icons/actions/mail_replyall.png"));
		panel.setCaption("Liste des contacts");
		
		setMargin(true);

		table = new Table("Liste des contacts");
		table.setWidth("100%");
		table.setPageLength(0);

		addComponent(table);

		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("Nom", String.class, null);
		container.addContainerProperty("Email", String.class, null);

		for (Passager p : getPassagerMailList()) {
			Item item = container.addItem(p);
			item.getItemProperty("Nom").setValue(p.getNom());
			item.getItemProperty("Email").setValue(p.getEmail());
		}

		table.setContainerDataSource(container);
		table.setColumnReorderingAllowed(true);
		table.setColumnCollapsingAllowed(true);
		table.setSelectable(true);

		// setting the table footer
		table.setFooterVisible(true);
		table.setColumnFooter("Nom", "Total");
		table.setColumnFooter("Email",
				String.valueOf(getPassagerMailList().size()));
		
		table.setColumnIcon("Nom",new ThemeResource("icons/actions/identity.png"));
		table.setColumnIcon("Email",new ThemeResource("icons/actions/mail_generic.png"));

		Button send = new Button("Envoyer");
		send.setIcon(new ThemeResource("icons/actions/mail_replyall.png"));
		send.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Here code to reply to passengers
				__app.getMainWindow().showNotification(
						"Notification","Votre Message est délivré avec succés",Window.Notification.TYPE_TRAY_NOTIFICATION);
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
				button.setCaption((table.isEditable() ? "Enregistrer"
						: "Editer"));

			}
		});

		button.setVisible(false);
		addComponent(hlayout);
		setComponentAlignment(hlayout, "right");
	}

	/*
	 * @return a mailing list of all clients
	 */
	public List<Passager> getPassagerMailList() {
		List<Passager> list = new ArrayList<Passager>();

		// getting a connection
		Connection c = DBConnexion.getConnection();

		// prepare statement
		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT nom, email FROM passager WHERE email!=''");
			while (rs.next()) {
				Passager p = new Passager();
				p.setNom(rs.getString("nom"));
				p.setEmail(rs.getString("email"));
				list.add(p);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
