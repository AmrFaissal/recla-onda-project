package com.example.reclaadmin;

import java.awt.geom.Point2D;

import org.vaadin.hezamu.googlemapwidget.GoogleMap;
import org.vaadin.hezamu.googlemapwidget.GoogleMap.MapControl;
import org.vaadin.hezamu.googlemapwidget.overlay.BasicMarker;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TableauBord extends VerticalLayout{

	
	ReclaadminApplication __app;

	public TableauBord(Panel panel, ReclaadminApplication application){
		
		__app = application;
		panel.setSizeUndefined();
		panel.setCaption("Tableau de Bord");
		addComponent(getMap());
	}
	
	
	public GoogleMap getMap()
	{
		GoogleMap googleMap = new GoogleMap(__app, new Point2D.Double(33.22, 7.35), 2);	
		googleMap.setWidth("680px");		        
		googleMap.setHeight("520px");
					        	       
		BasicMarker basicMarker = new BasicMarker(1L, new Point2D.Double(33.22, 7.35), "Aéroport Mohammed V");
		
		basicMarker.setInfoWindowContent(null, new Label("Points Faibles/Forts"));
		
		googleMap.addMarker(basicMarker);
		googleMap.addControl(MapControl.MenuMapTypeControl);		        
		googleMap.addControl(MapControl.LargeMapControl);
		
		
		return googleMap;	
	}
}
