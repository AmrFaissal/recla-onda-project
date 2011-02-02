package ma.onda.reclamations;

import java.util.Locale;

import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class GPassagerFieldFactory extends DefaultFieldFactory {
	
	ComboBox passager = new ComboBox("Vous êtes?");
	ComboBox countries = new ComboBox("Provenance");
	ComboBox _countries = new ComboBox("Destination");
	final String[] locales = Locale.getISOCountries();
	
	public GPassagerFieldFactory()
	{
		//Felling out countries
		 for (int i=0; i<locales.length;i++)
		 {
			 countries.addItem(locales[i]);
		 }
		 
		 for (int i=0; i<locales.length;i++)
		 {
			 _countries.addItem(locales[i]);
		 }
		 
		 //felling out passenger type
		 passager.setInputPrompt("Vous êtes?");
		 passager.addItem("Passager");
		 passager.addItem("Usager");
		 passager.addItem("Attendant");
		 passager.addItem("Accompagnateur");
		
	}
	
	
	@Override
	public Field createField(Item item, Object propertyId,Component uiContext) 
	{
		if ("provenance".equals(propertyId))
		{
			return countries;
		}
		
		if ("destination".equals(propertyId))
		{
			return _countries;
		}
		
		if ("typeReclamateur".equals(propertyId))
		{
			return passager;
		}
		
		Field f = super.createField(item, propertyId, uiContext);
		 
		if ("date".equals(propertyId))
		 {
			 DateField pdate = (DateField)f;
			 pdate.setRequired(true);
			 pdate.setLocale(new Locale("fr","FR"));
			 pdate.setDateFormat("yyyy-MM-dd");
			 pdate.setWidth("14em");
		 }
		 else if ("nVol".equals(propertyId))
		 {
			 TextField tf = (TextField)f;
			 tf.setCaption("N° Vol");
			 tf.setInputPrompt("Numéro de vol");
			 tf.setRequired(true);
			 tf.setWidth("14em");
		 }
		 else if ("provenance".equals(propertyId))
		 {
			 ComboBox cb = (ComboBox)f;	 
			 cb.setRequired(true);
			 cb.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		 }

		 return f;
	}
	
	

}
