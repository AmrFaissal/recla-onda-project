package ma.onda.reclamations;

import java.util.Arrays;

import server.IdGenerator;
import server.ServerProvider;

import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;


@SuppressWarnings("serial")
public class ReclamationsApplication extends Application {
	
	private Window main;
	private CustomLayout custom = new CustomLayout("client");
	private CustomLayout custom1 = new CustomLayout("avis");
	private VerticalLayout l1 = new VerticalLayout();
	private VerticalLayout l2 = new VerticalLayout();
	private VerticalLayout l3 = new VerticalLayout();
	private static HorizontalLayout hlayout = new HorizontalLayout();
	private static final ThemeResource icon1 = new ThemeResource("icons/actions/ledyellow.png");
	private static final ThemeResource icon2 = new ThemeResource("icons/actions/ledgreen.png");
	private static final ThemeResource icon3 = new ThemeResource("icons/actions/ledred.png");
	private TabSheet tabSheet = new TabSheet();
	private Button submitButton = new Button("Submit");
	private Form form1 = new Form();
	private Passager passager = new Passager();
	private Form form2 = new Form();
	private GPassager gpassager = new GPassager();
	private Form form3 = new Form();
	private Form form4 = new Form();
	private Form form5 = new Form();
	private OptionGroup options1 = new OptionGroup();
	private OptionGroup options2 = new OptionGroup();
	private OptionGroup options3 = new OptionGroup();
	
	private int id = IdGenerator.generateID();
	 
	
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		
		main = new Window("Welcome");
		main.setContent(custom);
		setMainWindow(main);
		
		tabSheet.setHeight("620px");
		tabSheet.setWidth("700px");
		
		l1.setMargin(true);
		l1.setSpacing(true);
		
		
		BeanItem<Passager> item = new BeanItem<Passager>(passager);
		form1.setCaption("Si vous désirez une réponse de notre part, veuillez nous laissez vos coordonnées");
		form1.setFormFieldFactory(new PassagerFieldFactory());
		form1.setItemDataSource(item);
		//setting an order inside the form
		form1.setVisibleItemProperties(Arrays.asList(new String[] {
				"gender","nom", "nationalite", "email","adresse","codePostale","phone"
		}));
		
		
		Button website = new Button("www.onda.ma");
		website.setStyleName(Button.STYLE_LINK);
		hlayout.setSpacing(true);
		hlayout.addComponent(new Label("Pour tout complément d'informations, veuillez visiter notre site web: "));
		hlayout.addComponent(website);
		l1.addComponent(form1);
		l1.addComponent(new Label(""));
		l1.addComponent(hlayout);
		
		
		l2.setMargin(true);
		l2.setSpacing(true);
		BeanItem<GPassager> item1 = new BeanItem<GPassager>(gpassager);
		form2.setCaption("Vous êtes");
		form2.setFormFieldFactory(new GPassagerFieldFactory());
		form2.setItemDataSource(item1);
		form2.setVisibleItemProperties(Arrays.asList(new String[]{
				"passager","usager", "attendant", "accompagnateur", "date", "nVol", "provenance", "destination"
		}));
		l2.addComponent(form2);
		
		
		l3.setMargin(true);
		form3.setCaption("N'hésitez pas à donner plus de détails");
		form3.addField("airport", new ComboBox("1. Vous êtes à l'aéroport de: "));
		form3.addField("terminal", new ComboBox("2. Précisez, le cas échéant: "));
		
		ComboBox cb = (ComboBox)form3.getField("airport");
		cb.addItem("AGAD");
		cb.addItem("AGA");
		
		ComboBox cb1 = (ComboBox)form3.getField("terminal");
		cb1.addItem("Terminal 1");
		cb1.addItem("Terminal 2");
		cb1.addItem("Terminal 3");
		
		form4.setCaption("Quelle est la zone concernée par votre remarque ?");
		custom1.addComponent(form3, "form3");
		custom1.addComponent(form4, "form4");
		
		options1.setCaption("Zone Départ");
		options1.setMultiSelect(true);
		options2.setCaption("Zone Arrivée");
		options2.setMultiSelect(true);
		//filling out options1
		options1.addItem("Parking voitures");
		options1.addItem("Hall Public Départ");
		options1.addItem("Comptoirs d'information");
		options1.addItem("Comptoirs d'enregistrement");
		options1.addItem("Commerces");
		options1.addItem("Restauration");
		options1.addItem("Blocs sanitaires");
		options1.addItem("Douane");
		options1.addItem("Police");
		options1.addItem("Embarquement");
		
		//filling out options2
		options2.addItem("Débarquement");
		options2.addItem("Transit et correspondance");
		options2.addItem("Police");
		options2.addItem("Livraison bagages");
		options2.addItem("Douane");
		options2.addItem("Commerces");
		options2.addItem("Restauration");
		options2.addItem("Blocs sanitaires");
		options2.addItem("Comptoir d'informations touristiques");
		options2.addItem("Parking voitures");
		
		custom1.addComponent(options1, "options1");
		custom1.addComponent(options2, "options2");
		
		form5.setCaption("Vos suggestions ou remarques concernent");
		custom1.addComponent(form5, "form5");
		
		//filling out options 3
		options3.setMultiSelect(true);
		options3.addItem("La signalisation routière");
		options3.addItem("L'accès à l'aéroport");
		options3.addItem("Les chariots à bagages");
		options3.addItem("Les compagnies aériennes");
		options3.addItem("Les facilitations aux Personnes à Mobilité Réduite");
		options3.addItem("Les facilitations MRE (marocains résidents à l'étranger)");
		options3.addItem("Les moyens de transport");
		custom1.addComponent(options3, "options3");
		
		l3.addComponent(custom1);
		
		tabSheet.addTab(l1, "Etape 1", icon1);
		tabSheet.addTab(l2, "Etape 2", icon2);
		tabSheet.addTab(l3, "Etape 3", icon3);
		
		submitButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) 
			{
				//adding the reclamation
				if (!form1.getField("email").getValue().equals(""))
				{ 
					//adding the passenger
					//ServerProvider.get__serverPrx().addPassager(id, form1.getField("gender").getValue().toString(), form1.getField("nom").getValue().toString(), form1.getField("email").getValue().toString(), form1.getField("adresse").getValue().toString(), form1.getField("codePostale").getValue().toString(), form1.getField("phone").getValue().toString(), form1.getField("gender").getValue().toString(), numVol, prov, dest, nationalite)
					
				}
				
				
			}
		});
		
		custom.addComponent(tabSheet, "tabSheet");
		custom.addComponent(submitButton, "submitButton");
		
	}

}
