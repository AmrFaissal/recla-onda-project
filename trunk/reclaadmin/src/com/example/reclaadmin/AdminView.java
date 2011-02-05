package com.example.reclaadmin;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminView extends VerticalLayout {

	ReclaadminApplication __app;
	CustomLayout custom = new CustomLayout("admin");
	MenuBar mainmenu = new MenuBar();
	private Panel centrale;

	/*
	 * Constructor
	 */
	public AdminView(ReclaadminApplication application) {
		__app = application;
		centrale = new Panel();

		custom.addComponent(mainmenu, "mainmenu");
		custom.addComponent(centrale, "centrale");
		addComponent(custom);

		centrale.setIcon(new ThemeResource("icons/actions/message.png"));
		centrale.setWidth("635px");
		centrale.setHeight("620px");
		// centrale.setSizeUndefined();
		centrale.setVisible(false);

		// adding items the Main Menu
		MenuBar.MenuItem email = mainmenu.addItem("Email", null);
		email.setIcon(new ThemeResource("icons/actions/message.png"));
		email.addItem("Nouveau Email", new ThemeResource(
				"icons/actions/mail_new.png"), mailCommand);
		email.addSeparator();
		email.addItem("Consulter", new ThemeResource(
				"icons/actions/mail_get.png"), null);

		MenuBar.MenuItem pv = mainmenu.addItem("Générer PV", generatePV);
		pv.setIcon(new ThemeResource("icons/actions/filenew.png"));

		MenuBar.MenuItem reclamation = mainmenu.addItem("Tableaux", null);
		reclamation.setIcon(new ThemeResource(
				"icons/actions/view_multicolumn.png"));
		reclamation.addItem("Tableau de Réclamation", new ThemeResource(
				"icons/actions/toggle_log.png"), getReclamations);
		reclamation.addSeparator();
		reclamation.addItem("Tableau d'analyse", new ThemeResource(
				"icons/actions/toggle_log.png"), getAnalyse);
		reclamation.addSeparator();
		reclamation.addItem("Définir les actions", new ThemeResource(
				"icons/actions/redo.png"), defineActions);

		MenuBar.MenuItem synthese = mainmenu.addItem("Générer les synthèses",
				null);
		synthese.setIcon(new ThemeResource("icons/actions/run.png"));
		synthese.addItem("Par mois", monthSynthese);
		synthese.addSeparator();
		synthese.addItem("Par année", annualSynthese);

		MenuBar.MenuItem tableauBord = mainmenu.addItem("Tableau de Bord",
				getTableauBord);
		tableauBord.setIcon(new ThemeResource("icons/actions/wizard.png"));

		MenuBar.MenuItem dConsole = mainmenu.addItem("Konsole", decisiaKonsole);
		dConsole.setIcon(new ThemeResource("icons/actions/openterm.png"));

		MenuBar.MenuItem print = mainmenu.addItem("Imprimer", null);
		print.setIcon(new ThemeResource("icons/actions/fileprint.png"));

		MenuBar.MenuItem help = mainmenu.addItem("Help", null);
		help.setIcon(new ThemeResource("icons/actions/help.png"));

		MenuBar.MenuItem logout = mainmenu.addItem("Logout", _logout);
		logout.setIcon(new ThemeResource("icons/actions/exit.png"));

		setMargin(true);

	}

	private Command _logout = new Command() {
		@Override
		public void menuSelected(MenuItem selectedItem) {
			__app.getViewManager().switchScreen(LoginScreen.class.getName(),
					new LoginScreen(__app));
		}
	};

	private Command mailCommand = new Command() {
		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new AdminMail(__app, centrale));
		}
	};

	private Command generatePV = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			int returnVal = new PDFGen().generate();
			if (returnVal == 0) {
				__app.getMainWindow().showNotification(
						"Procés Verbal généré avec succés");
			}
		}
	};

	private Command getAnalyse = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new TableauAnalyse(centrale));
		}
	};

	private Command getReclamations = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new TableauReclamation(centrale));
		}
	};

	private Command monthSynthese = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new MonthlyTimeLine(__app, centrale,
					"Synthèse du Mois"));
		}
	};

	private Command annualSynthese = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new AnnualTimeLine(__app, centrale,
					"Synthèse de l'année"));
		}
	};

	private Command decisiaKonsole = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new Konsole(centrale));
		}
	};

	private Command getTableauBord = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new TableauBord(centrale, __app));
		}
	};

	private Command defineActions = new Command() {

		@Override
		public void menuSelected(MenuItem selectedItem) {
			centrale.setVisible(true);
			switchView(centrale, new DefineActions(centrale, __app));
		}
	};

	/*
	 * a tool to switch between panel views
	 */
	public void switchView(Panel window, Layout newView) {
		window.setContent(newView);
	}

}
