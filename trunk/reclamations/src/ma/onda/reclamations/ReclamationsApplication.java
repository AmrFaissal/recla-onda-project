package ma.onda.reclamations;

import java.util.Arrays;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import entities.DQSPServer;
import entities.DQSPServerI;

@SuppressWarnings("serial")
public class ReclamationsApplication extends Application {

	private Window main;
	private CustomLayout custom = new CustomLayout("client");
	// private CustomLayout custom1 = new CustomLayout("avis");
	private VerticalLayout l1 = new VerticalLayout();
	private VerticalLayout l2 = new VerticalLayout();
	private VerticalLayout l3 = new VerticalLayout();
	private VerticalLayout l4 = new VerticalLayout();
	private static HorizontalLayout hlayout = new HorizontalLayout();
	private static HorizontalLayout hlayout1 = new HorizontalLayout();
	private static HorizontalLayout hlayout2 = new HorizontalLayout();
	private static HorizontalLayout hlayout3 = new HorizontalLayout();
	private static final ThemeResource icon1 = new ThemeResource(
			"icons/actions/ledyellow.png");
	private static final ThemeResource icon2 = new ThemeResource(
			"icons/actions/ledgreen.png");
	private static final ThemeResource icon3 = new ThemeResource(
			"icons/actions/ledred.png");
	private static final ThemeResource icon4 = new ThemeResource(
			"icons/actions/ledblue.png");
	private TabSheet tabSheet = new TabSheet();
	private Button submitButton = new Button("Submit");
	private Form form1 = new Form();
	private Passager passager = new Passager();
	private Form form2 = new Form();
	private GPassager gpassager = new GPassager();
	private Form form3 = new Form();
	private Form form4 = new Form();
	private Form form5 = new Form();
	/*
	 * private OptionGroup options1 = new OptionGroup(); private OptionGroup
	 * options2 = new OptionGroup(); private OptionGroup options3 = new
	 * OptionGroup();
	 */

	private int id = IdGenerator.generateID();

	private DQSPServer __serverI = new DQSPServerI();

	@SuppressWarnings("deprecation")
	@Override
	public void init() {

		main = new Window("Welcome");
		main.setContent(custom);
		setMainWindow(main);

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
		zones.addItem("Parking voitures");
		zones.addItem("Hall Public Départ");
		zones.addItem("Comptoirs d'information");
		zones.addItem("Comptoirs d'enregistrement");
		zones.addItem("Commerces");
		zones.addItem("Restauration");
		zones.addItem("Blocs sanitaires");
		zones.addItem("Douane");
		zones.addItem("Police");
		zones.addItem("Embarquement");
		hlayout2.addComponent(zones);
		zones.setImmediate(true);

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
		_zones.addItem("Parking voitures");
		_zones.setImmediate(true);
		hlayout2.addComponent(_zones);

		l3.addComponent(hlayout2);
		
		zones.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {	
				_zones.setEnabled(false);
				}
			});
		
		_zones.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				zones.setEnabled(false);
			}
		});
		// custom1.addComponent(form3, "form3");
		// custom1.addComponent(form4, "form4");

		/*
		 * options1.setCaption("Zone Départ"); options1.setMultiSelect(false);
		 * 
		 * options2.setCaption("Zone Arrivée"); options2.setMultiSelect(false);
		 * // filling out options1 options1.addItem("Parking voitures");
		 * options1.addItem("Hall Public Départ");
		 * options1.addItem("Comptoirs d'information");
		 * options1.addItem("Comptoirs d'enregistrement");
		 * options1.addItem("Commerces"); options1.addItem("Restauration");
		 * options1.addItem("Blocs sanitaires"); options1.addItem("Douane");
		 * options1.addItem("Police"); options1.addItem("Embarquement");
		 * 
		 * // filling out options2 options2.addItem("Débarquement");
		 * options2.addItem("Transit et correspondance");
		 * options2.addItem("Police"); options2.addItem("Livraison bagages");
		 * options2.addItem("Douane"); options2.addItem("Commerces");
		 * options2.addItem("Restauration");
		 * options2.addItem("Blocs sanitaires");
		 * options2.addItem("Comptoir d'informations touristiques");
		 * options2.addItem("Parking voitures");
		 * 
		 * custom1.addComponent(options1, "options1");
		 * custom1.addComponent(options2, "options2");
		 */

		form5.setCaption("Vos suggestions ou remarques concernent");
		l3.addComponent(form5);
		final ComboBox suggest = new ComboBox("Thèmes");
		suggest.setInputPrompt("Thèmes");
		suggest.addItem("La signalisation routière");
		suggest.addItem("L'accès à l'aéroport");
		suggest.addItem("Tableaux d'affichage");
		suggest.addItem("Les chariots à bagages");
		suggest.addItem("Les compagnies aériennes");
		suggest.addItem("Les facilitations aux Personnes à Mobilité Réduite");
		suggest.addItem("Les facilitations MRE (marocains résidents à l'étranger)");
		suggest.addItem("Les moyens de transport");
		suggest.addItem("Papier");
		suggest.addItem("L'éclairage");
		
		hlayout3.setMargin(true);
		hlayout3.setSpacing(true);
		hlayout3.addComponent(suggest);
		l3.addComponent(hlayout3);
		// custom1.addComponent(form5, "form5");

		// filling out options 3
		/*
		 * options3.setMultiSelect(true);
		 * options3.addItem("La signalisation routière");
		 * options3.addItem("L'accès à l'aéroport");
		 * options3.addItem("Les chariots à bagages");
		 * options3.addItem("Les compagnies aériennes");
		 * options3.addItem("Les facilitations aux Personnes à Mobilité Réduite"
		 * ); options3.addItem(
		 * "Les facilitations MRE (marocains résidents à l'étranger)");
		 * options3.addItem("Les moyens de transport");
		 * custom1.addComponent(options3, "options3");
		 */

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
					__serverI.addReclamation(
							id,
							new java.sql.Date(((java.util.Date) form2.getField(
									"date").getValue()).getTime()), cb
									.getValue().toString(), cb1.getValue()
									.toString(), new ServiceParser()
									.parseService(zones.getValue().toString()),
							suggest.getValue().toString(), String
									.valueOf(details.getValue()), zones
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
