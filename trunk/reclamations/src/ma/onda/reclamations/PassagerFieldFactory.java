package ma.onda.reclamations;

import java.util.Locale;

import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PassagerFieldFactory  extends DefaultFieldFactory {
	
	final ComboBox gender = new ComboBox("Vous êtes?");
	final ComboBox nationalite = new ComboBox("Nationalité");
	final String[] locales = Locale.getISOCountries();
	

	/*
	 * Constructor
	 */
	public PassagerFieldFactory()
	{
		gender.setInputPrompt("Vous êtes?");
		gender.setWidth("13em");
		gender.addItem("Mme");
		gender.addItem("Mlle");
		gender.addItem("M");
		gender.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		
		nationalite.setWidth("13em");
		//felling nationalities
		for (int i=0; i<locales.length; i++)
		{
			nationalite.addItem(locales[i]);
		}
		gender.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
	}
	
	@Override
	public Field createField(Item item, Object propertyId,Component uiContext) 
	{
		 if ("gender".equals(propertyId))
		 {
			 return gender;
		 }
		 
		 if ("nationalite".equals(propertyId))
		 {
			 nationalite.setInputPrompt("Nationalité");
			 return nationalite;
		 }
		 
		 Field f = super.createField(item, propertyId, uiContext);
		 
		 if ("phone".equals(propertyId))
		 {
			 TextField txt = (TextField) f;
			 txt.setCaption("Téléphone");
			 txt.setInputPrompt("(+111) xxx-xxx-xxx");
		 }
		 
		 return f;
	}

}
