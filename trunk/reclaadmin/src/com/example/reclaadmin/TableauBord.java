package com.example.reclaadmin;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.vaadin.hezamu.googlemapwidget.GoogleMap;
import org.vaadin.hezamu.googlemapwidget.GoogleMap.MapControl;
import org.vaadin.hezamu.googlemapwidget.overlay.BasicMarker;
import org.vaadin.touchmenu.TouchMenu;
import org.vaadin.touchmenu.TouchMenu.TouchMenuButton;
import org.vaadin.ui.JFreeChartWrapper;
import server.DQSPServer;
import server.DQSPServerI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import entities.DBConnexion;

@SuppressWarnings("serial")
public class TableauBord extends VerticalLayout {

	TouchMenu menu;
	ReclaadminApplication __app;
	Window map;
	// markers
	BasicMarker casablanca;
	BasicMarker rabat;
	BasicMarker guelmimAirBase;
	BasicMarker agadir; // 30.380894,-9.546196

	// Recurrences
	JFreeChartWrapper wrapChart;
	Window rec;
	DefaultCategoryDataset dataSet;
	JFreeChart chartOfThemes;
	ComboBox airports;
	Button save;
	VerticalLayout _vlayout = new VerticalLayout();
	Panel vpanel = new Panel();
	GridLayout glayout;
	DQSPServer _server;

	// Evolutions
	Window evolutions;
	ComboBox themes;
	ComboBox options;
	DQSPServer __server;
	VerticalLayout vlayout = new VerticalLayout();
	HorizontalLayout hlayout = new HorizontalLayout();
	Panel _vpanel = new Panel();
	TimeSeries times;
	JFreeChart evo;
	JFreeChartWrapper evoWrap;

	/*
	 * Constructor
	 * 
	 * @param panel, application
	 */
	public TableauBord(Panel panel, ReclaadminApplication application) {

		__app = application;

		panel.setSizeUndefined();
		panel.setCaption("Tableau de Bord");
		panel.setIcon(new ThemeResource("icons/actions/connect_established.png"));

		menu = new TouchMenu();
		menu.setImmediate(true);
		menu.setColumns(2);

		// Evolutions per theme
		menu.addButton("Evolutions", new TouchMenu.Command() {
			@Override
			public void menuSelected(TouchMenuButton selectedButton) {

				evolutions = new Window("Evolutions");
				evolutions.center();
				evolutions.setWidth("400px");
				evolutions.setHeight("200px");
				// evolutions.setSizeFull();
				evolutions.setImmediate(true);

				airports = new ComboBox("Aéroport");
				airports.setInputPrompt("Sélectionnez un aéroport");
				airports.setIcon(new ThemeResource("icons/actions/identity.png"));
				airports.setImmediate(true);
				airports.setNullSelectionAllowed(false);

				themes = new ComboBox("Thème");
				themes.setInputPrompt("Sélectionnez un thème");
				themes.setVisible(false);
				themes.setIcon(new ThemeResource(
						"icons/actions/connect_creating.png"));
				themes.setImmediate(true);
				themes.setNullSelectionAllowed(false);
				
				options = new ComboBox("Type d'évolution");
				options.addItem("Mensuelle");
				options.addItem("Annuelle");
				options.setInputPrompt("Choisissez un type");
				options.setImmediate(true);
				options.setNullSelectionAllowed(false);
				options.setVisible(false);

				save = new Button();
				save.setIcon(new ThemeResource("icons/actions/filesave.png"));
				save.setVisible(false);

				vlayout.setSpacing(true);
				vlayout.setMargin(true);
				vlayout.setImmediate(true);
				_vpanel.addComponent(vlayout);
				_vpanel.setVisible(false);

				hlayout.setSpacing(true);
				hlayout.setSpacing(true);
				hlayout.addComponent(airports);
				hlayout.setComponentAlignment(airports, Alignment.BOTTOM_LEFT);
				hlayout.addComponent(themes);
				hlayout.addComponent(options);
				hlayout.setComponentAlignment(options, Alignment.BOTTOM_RIGHT);
				hlayout.addComponent(save);
				hlayout.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

				evolutions.addComponent(hlayout);
				evolutions.addComponent(_vpanel);

				// filling the airports
				__server = new DQSPServerI();
				for (String s : __server.listOfAirports()) {
					airports.addItem(s);
				}

				// listener for airports
				airports.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						if (__server.listOfThemes(
								String.valueOf(airports.getValue())).size() != 0) {

							// filling themes
							for (String s : __server.listOfThemes(String
									.valueOf(airports.getValue()))) {
								themes.addItem(s);
							}

							themes.setVisible(true);
							evolutions.setWidth("690px");
							evolutions.setHeight("200px");
							themes.addListener(new Property.ValueChangeListener() {
								@Override
								public void valueChange(ValueChangeEvent event) {
									options.setVisible(true);
									vlayout.removeAllComponents();
									
									//creating and wrapping the chart
									evoWrap = new
									JFreeChartWrapper(createChart(
											createMonthlyDataset(String.valueOf(airports.getValue()), String.valueOf(themes.getValue())), themes.getValue()
											.toString(), "MMM-yyyy"));
									vlayout.addComponent(evoWrap);
									vlayout.setComponentAlignment(evoWrap, Alignment.MIDDLE_CENTER);
									_vpanel.setVisible(true);
									save.setVisible(true);
									evolutions.setSizeFull();
									evolutions.center();
									options.setValue("Mensuelle");
									
									//decide which type of evolution you want
									options.addListener(new Property.ValueChangeListener() {
										@Override
										public void valueChange(ValueChangeEvent event) {
											if(String.valueOf(options.getValue()).equals("Mensuelle")){
												
												vlayout.removeAllComponents();
												//creating and wrapping the chart
												evoWrap = new
												JFreeChartWrapper(createChart(
														createMonthlyDataset(String.valueOf(airports.getValue()), String.valueOf(themes.getValue())), themes.getValue()
														.toString(), "MMM-yyyy"));
												vlayout.addComponent(evoWrap);
												vlayout.setComponentAlignment(evoWrap, Alignment.MIDDLE_CENTER);
												_vpanel.setVisible(true);
												save.setVisible(true);
												evolutions.setSizeFull();
												evolutions.center();
											}
											else if(String.valueOf(options.getValue()).equals("Annuelle")){
												vlayout.removeAllComponents();
												//creating and wrapping the chart
												evoWrap = new
												JFreeChartWrapper(createChart(
														createAnnualDataset(String.valueOf(airports.getValue()), String.valueOf(themes.getValue())), themes.getValue()
														.toString(), "yyyy"));
												vlayout.addComponent(evoWrap);
												vlayout.setComponentAlignment(evoWrap, Alignment.MIDDLE_CENTER);
												_vpanel.setVisible(true);
												save.setVisible(true);
												evolutions.setSizeFull();
												evolutions.center();
											}
										}
									});
								}
							});

						} else {
							save.setVisible(false);
							themes.setVisible(false);
							options.setVisible(false);
							_vpanel.setVisible(false);
							evolutions.setWidth("400px");
							evolutions.setHeight("200px");
							__app.getMainWindow().showNotification(
									"Notification",
									"Pas de données pour cet aéroport",
									Window.Notification.TYPE_TRAY_NOTIFICATION);
						}

					}
				});
				__app.getMainWindow().addWindow(evolutions);
			}
		});

		// Recurrences
		menu.addButton("Récurrences", new TouchMenu.Command() {
			@Override
			public void menuSelected(TouchMenuButton selectedButton) {
				rec = new Window("Récurrences");
				rec.center();
				rec.setWidth("400px");
				rec.setHeight("200px");
				rec.setImmediate(true);

				airports = new ComboBox();
				airports.setInputPrompt("Sélectionnez un aéroport");
				airports.setIcon(new ThemeResource("icons/actions/identity.png"));
				airports.setImmediate(true);
				airports.setNullSelectionAllowed(false);

				save = new Button();
				save.setIcon(new ThemeResource("icons/actions/filesave.png"));
				save.setVisible(false);

				_vlayout.setSpacing(true);
				_vlayout.setMargin(true);
				_vlayout.setImmediate(true);
				vpanel.addComponent(_vlayout);
				vpanel.setVisible(false);

				glayout = new GridLayout(2, 2);
				glayout.setSpacing(true);
				glayout.setSpacing(true);
				glayout.addComponent(airports, 0, 0);
				glayout.setComponentAlignment(airports, Alignment.BOTTOM_LEFT);
				glayout.addComponent(save, 1, 0);
				glayout.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

				rec.addComponent(glayout);
				rec.addComponent(vpanel);

				// filling the airports
				_server = new DQSPServerI();
				for (String s : _server.listOfAirports()) {
					airports.addItem(s);
				}
				// listener for airports
				airports.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						if (_server.listOfThemes(
								String.valueOf(airports.getValue())).size() != 0) {
							rec.center();
							// rec.setWidth("780px");
							// rec.setHeight("700px");
							rec.setSizeFull();

							dataSet = new DefaultCategoryDataset();
							dataSet.clear();

							_vlayout.removeAllComponents();

							for (String s : _server.listOfThemes(String
									.valueOf(airports.getValue()))) {
								int val = _server.numberOfAppearancesPerMonth(
										s, String.valueOf(airports.getValue())) * 100 / 4;
								dataSet.setValue(val, s, s);
							}
							// for save button
							save.setVisible(true);
							save.addListener(new Button.ClickListener() {
								@Override
								public void buttonClick(ClickEvent event) {
									try {
										ChartUtilities
												.saveChartAsJPEG(
														new File(
																"/home/matrix/Desktop/graphs/graph.jpg"),
														chartOfThemes, 500, 300);
									} catch (IOException e) {
										e.printStackTrace();
									}
									__app.getMainWindow()
											.showNotification(
													"Notification",
													"Graphe Enregistré",
													Window.Notification.TYPE_TRAY_NOTIFICATION);
								}
							});
							chartOfThemes = ChartFactory.createBarChart3D(
									"Récurrences", "Thème", "Récurrence (%)",
									dataSet, PlotOrientation.VERTICAL, true,
									true, false);
							wrapChart = new JFreeChartWrapper(chartOfThemes);
							wrapChart.setImmediate(true);
							_vlayout.addComponent(wrapChart);
							_vlayout.setComponentAlignment(wrapChart,
									Alignment.MIDDLE_CENTER);
							vpanel.setVisible(true);
						} else {
							save.setVisible(false);
							vpanel.setVisible(false);
							rec.setWidth("400px");
							rec.setHeight("200px");
							rec.center();
							__app.getMainWindow().showNotification(
									"Notification",
									"Pas de données pour cet aéroport",
									Window.Notification.TYPE_TRAY_NOTIFICATION);
						}
					}
				});
				__app.getMainWindow().addWindow(rec);
			}
		});

		// Comparisons
		menu.addButton("Comparaisons", new TouchMenu.Command() {

			@Override
			public void menuSelected(TouchMenuButton selectedButton) {

			}
		});

		// Points Faibles/Forts
		menu.addButton("Points Forts/Faibles", new TouchMenu.Command() {

			@Override
			public void menuSelected(TouchMenuButton selectedButton) {
				map = new Window("Points Forts/Faibles");
				map.center();
				// map.setWidth("680px");
				// map.setHeight("560px");
				map.setSizeFull();
				map.addComponent(getMap());
				__app.getMainWindow().addWindow(map);
			}
		});

		addComponent(menu);
		setComponentAlignment(menu, "center");
	}

	/*
	 * builds the Map
	 * 
	 * @param NULL
	 */
	GoogleMap getMap() {
		GoogleMap googleMap = new GoogleMap(__app, new Point2D.Double(
				-9.546196, 30.380894), 6);
		googleMap.setWidth("1000px");
		googleMap.setHeight("730px");
		// googleMap.setSizeFull();

		casablanca = new BasicMarker(1L, new Point2D.Double(-7.589722,
				33.367222), "Aéroport Mohammed V");
		rabat = new BasicMarker(2L, new Point2D.Double(-6.750000, 34.050000),
				"Aéroport de Rabat/Salé");
		guelmimAirBase = new BasicMarker(3L, new Point2D.Double(-10.052284,
				29.025658), "Guelmim Air Base");
		agadir = new BasicMarker(4L, new Point2D.Double(-9.546196, 30.380894),
				"AGADIR AlMassira");

		// add Markers to Map
		googleMap.addMarker(casablanca);
		googleMap.addMarker(rabat);
		googleMap.addMarker(guelmimAirBase);
		googleMap.addMarker(agadir);

		googleMap.addControl(MapControl.MenuMapTypeControl);
		googleMap.addControl(MapControl.LargeMapControl);

		return googleMap;
	}

	/*
	 * creates a time series chart
	 * 
	 * @param dataset, title, dateFormat
	 * 
	 * @return chart
	 */
	JFreeChart createChart(XYDataset dataset, String title, String dateFormat) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Date",
				"Nombre de réclamation", dataset, true, true, false);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(0.5, 0.5, 0.5, 0.5));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new java.text.SimpleDateFormat(dateFormat));

		return chart;
	}

	/*
	 * Creates a data set, consisting of one series of monthly data
	 * 
	 * @return data set
	 */
	XYDataset createMonthlyDataset(String airport, String remarque) {

		TimeSeries s = new TimeSeries("Evolutions");
		// getting a Connection
		Connection con = DBConnexion.getConnection();

		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT r.`nomAeroport`, MONTH(r.`date`) mois, YEAR(r.`date`) `annee`, remarque, count(MONTH(r.`date`)) num FROM reclamation r WHERE nomAeroport=? AND remarque=? GROUP BY mois");
			ps.setString(1, airport);
			ps.setString(2, remarque);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s.add(new Month(rs.getInt("mois"), rs.getInt("annee")),
						rs.getInt("num"));
			}

			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s);

		return dataset;
	}
	
	
	/*
	 * Creates a data set, consisting of one series of monthly data
	 * 
	 * @return data set
	 */
	XYDataset createAnnualDataset(String airport, String remarque) {
		
		TimeSeries s = new TimeSeries("Evolutions");
		s.add(new Year(1999), 123.12);
		s.add(new Year(2000), 124.50);
		s.add(new Year(2001), 110.90);
		s.add(new Year(2002), 102.05);
		s.add(new Year(2003), 150.44);
		
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s);
		
		return dataset;
	}

}
