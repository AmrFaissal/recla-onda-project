package ma.onda.reclamations;

import java.util.Arrays;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import entities.DQSPServer;
import entities.DQSPServerI;

@SuppressWarnings("serial")
public class ReclamationsApplication extends Application {

	// main window
	Window main;

	// Different layouts
	static CustomLayout custom = new CustomLayout("client");
	static VerticalLayout l1 = new VerticalLayout();
	static VerticalLayout l2 = new VerticalLayout();
	static VerticalLayout l3 = new VerticalLayout();
	static VerticalLayout l4 = new VerticalLayout();
	static HorizontalLayout hlayout = new HorizontalLayout();
	static HorizontalLayout hlayout1 = new HorizontalLayout();
	static HorizontalLayout hlayout2 = new HorizontalLayout();
	static HorizontalLayout hlayout3 = new HorizontalLayout();

	// icons for tabs
	static final ThemeResource icon1 = new ThemeResource(
			"icons/actions/ledyellow.png");
	static final ThemeResource icon2 = new ThemeResource(
			"icons/actions/ledgreen.png");
	static final ThemeResource icon3 = new ThemeResource(
			"icons/actions/ledred.png");
	static final ThemeResource icon4 = new ThemeResource(
			"icons/actions/ledblue.png");

	// main tab sheet
	TabSheet tabSheet = new TabSheet();
	Button submitButton = new Button("Submit");

	Passager passager = new Passager();
	GPassager gpassager = new GPassager();

	// Different forms
	Form form1 = new Form();
	Form form2 = new Form();
	Form form3 = new Form();
	Form form4 = new Form();
	Form form5 = new Form();

	// Generate a random ID
	int id = IdGenerator.generateID();

	// Instances of server implementation
	DQSPServer _serverI = new DQSPServerI();
	DQSPServer __serverI = new DQSPServerI();

	@SuppressWarnings("deprecation")
	@Override
	public void init() {

		main = new Window("Welcome");
		main.setContent(custom);
		setMainWindow(main);

		tabSheet.setImmediate(true);
		tabSheet.setHeight("460px");
		tabSheet.setWidth("700px");

		l1.setMargin(true);
		l1.setSpacing(true);

		BeanItem<Passager> item = new BeanItem<Passager>(passager);
		form1.setCaption("Si vous désirez une réponse de notre part, veuillez nous laissez vos coordonnées");
		form1.setFormFieldFactory(new PassagerFieldFactory());
		form1.setItemDataSource(item);
		// setting an order inside the form
		form1.setVisibleItemProperties(Arrays.asList(new String[] { "gender",
				"nom", "nationalite", "email", "adresse", "codePostale",
				"phone" }));

		Button website = new Button("www.onda.ma");
		website.setStyleName(Button.STYLE_LINK);
		hlayout.setSpacing(true);
		hlayout.addComponent(new Label(
				"Pour tout complément d'informations, veuillez visiter notre site web: "));
		hlayout.addComponent(website);
		l1.addComponent(form1);
		l1.addComponent(new Label(""));
		l1.addComponent(hlayout);

		l2.setMargin(true);
		l2.setSpacing(true);
		BeanItem<GPassager> item1 = new BeanItem<GPassager>(gpassager);
		form2.setCaption("Quelques informations");
		form2.setFormFieldFactory(new GPassagerFieldFactory());
		form2.setItemDataSource(item1);
		form2.setVisibleItemProperties(Arrays
				.asList(new String[] { "typeReclamateur", "date", "nVol",
						"provenance", "destination" }));
		l2.addComponent(form2);
		Button website1 = new Button("www.onda.ma");
		website1.setStyleName(Button.STYLE_LINK);
		hlayout1.setSpacing(true);
		hlayout1.addComponent(new Label(
				"Pour tout complément d'informations, veuillez visiter notre site web: "));
		hlayout1.addComponent(website1);
		l2.addComponent(new Label(""));
		l2.addComponent(hlayout1);

		l3.setMargin(true);
		l3.addComponent(form3);
		form3.setCaption("N'hésitez pas à donner plus de détails");
		form3.addField("airport",
				new ComboBox("1. Vous êtes à l'aéroport de: "));
		form3.addField("terminal",
				new ComboBox("2. Précisez, le cas échéant: "));

		final ComboBox cb = (ComboBox) form3.getField("airport");
		cb.setInputPrompt("Aéroport");
		for (String s : __serverI.listOfAirports()) {
			cb.addItem(s);
		}

		final ComboBox cb1 = (ComboBox) form3.getField("terminal");
		cb1.setInputPrompt("Terminale");
		cb1.addItem("Terminale 1");
		cb1.addItem("Terminale 2");
		cb1.addItem("Terminale 3");

		form4.setCaption("Quelle est la zone concernée par votre remarque ?");
		l3.addComponent(form4);

		hlayout2.setMargin(true);
		hlayout2.setSpacing(true);

		final ComboBox zones = new ComboBox("Zone Départ");
		zones.setInputPrompt("Zone Départ");
		zones.setImmediate(true);
		zones.addItem("Parkings");
		zones.addItem("Hall Public Départ");
		zones.addItem("Comptoirs d'information");
		zones.addItem("Comptoirs d'enregistrement");
		zones.addItem("Commerces");
		zones.addItem("Restauration");
		zones.addItem("Blocs sanitaires");
		zones.addItem("Douane");
		zones.addItem("Police");
		zones.addItem("Embarquement");
		zones.addItem("Comptoir d'informations touristiques");
		hlayout2.addComponent(zones);

		final ComboBox _zones = new ComboBox("Zone Arrivée");
		_zones.setInputPrompt("Zone Arrivée");
		_zones.addItem("Débarquement");
		_zones.addItem("Transit et correspondance");
		_zones.addItem("Police");
		_zones.addItem("Livraison bagages");
		_zones.addItem("Douane");
		_zones.addItem("Commerces");
		_zones.addItem("Restauration");
		_zones.addItem("Blocs sanitaires");
		_zones.addItem("Comptoir d'informations touristiques");
		_zones.addItem("Parkings");
		_zones.setImmediate(true);
		hlayout2.addComponent(_zones);

		l3.addComponent(hlayout2);

		form5.setCaption("Vos suggestions ou remarques concernent");
		l3.addComponent(form5);
		final ComboBox suggest = new ComboBox("Thèmes");
		suggest.setImmediate(true);
		suggest.setInputPrompt("Thèmes");
		suggest.addItem("Liaison ville/aérogare");
		suggest.addItem("La signalisation routière");
		suggest.addItem("La signalisation des services");
		suggest.addItem("L'accès à l'aéroport");
		suggest.addItem("Circulation verticale");
		suggest.addItem("Tableaux d'affichage");
		suggest.addItem("Les chariots à bagages");
		suggest.addItem("Les compagnies aériennes");
		suggest.addItem("Comportement des agents");
		suggest.addItem("Tapis livraison bagages");
		suggest.addItem("Les facilitations aux Personnes à Mobilité Réduite");
		suggest.addItem("Les facilitations MRE (marocains résidents à l'étranger)");
		suggest.addItem("Les moyens de transport");
		suggest.addItem("Passerelles");
		suggest.addItem("Horloges");
		suggest.addItem("Apparence/Visiblité");
		suggest.addItem("Eclairages");
		suggest.addItem("Salles d'embarquement");
		suggest.addItem("Salles de débarquement");
		suggest.addItem("Espaces Verts");

		hlayout3.setMargin(true);
		hlayout3.setSpacing(true);
		hlayout3.addComponent(suggest);
		l3.addComponent(hlayout3);

		l4.setMargin(true);
		l4.setSpacing(true);
		Form desc = new Form();
		desc.setCaption("Vous voulez ajoutez plus de details?*");
		l4.addComponent(desc);
		final TextField details = new TextField();
		details.setWidth("620px");
		details.setHeight("310px");
		l4.addComponent(details);
		l4.setComponentAlignment(details, "center");
		l4.addComponent(new Label(
				"(*) Vos details sont très utils lors de la prise de décision"));

		tabSheet.addTab(l1, "Etape 1", icon1);
		tabSheet.addTab(l2, "Etape 2", icon2);
		tabSheet.addTab(l3, "Etape 3", icon3);
		tabSheet.addTab(l4, "Etape 4", icon4);

		submitButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// adding the reclamation
				if (!form1.getField("email").getValue().equals("")) {
					// adding the passenger
					__serverI
							.addPassager(id, (String) form1.getField("gender")
									.getValue(), form1.getField("nom")
									.getValue().toString(),
									form1.getField("email").getValue()
											.toString(),
									form1.getField("adresse").getValue()
											.toString(),
									form1.getField("codePostale").getValue()
											.toString(), form1
											.getField("phone").getValue()
											.toString(),
									form2.getField("typeReclamateur")
											.getValue().toString(), form2
											.getField("nVol").getValue()
											.toString(),
									form2.getField("provenance").getValue()
											.toString(),
									form2.getField("destination").getValue()
											.toString(),
									form1.getField("nationalite").getValue()
											.toString());
				}

				if (!cb.getValue().toString().equals("")) {
					_serverI.addReclamation(
							id,
							new java.sql.Date(((java.util.Date) form2.getField(
									"date").getValue()).getTime()), cb
									.getValue().toString(), cb1.getValue()
									.toString(), new ServiceParser()
									.parseService(zones.getValue().toString()),
							suggest.getValue().toString(), String
									.valueOf(details.getValue()), zones
									.getValue().toString());

					__serverI.addAction(new ServiceParser().parseService(zones
							.getValue().toString()), suggest.getValue()
							.toString(), String.valueOf(details.getValue()), cb
							.getValue().toString());

				} else {
					cb.setRequired(true);
				}
				main.getApplication().close();
			}
		});

		custom.addComponent(tabSheet, "tabSheet");
		custom.addComponent(submitButton, "submitButton");

	}
}
