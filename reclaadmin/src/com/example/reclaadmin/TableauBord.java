package com.example.reclaadmin;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import entities.DBConnexion;

@SuppressWarnings("serial")
public class TableauBord extends VerticalLayout {

	TouchMenu menu;
	ReclaadminApplication __app;

	// maps & markers
	Window map;
	GoogleMap __map;
	VerticalLayout mapLayout = new VerticalLayout();
	Panel mapPanel = new Panel();
	BasicMarker casablanca;
	BasicMarker rabat;
	BasicMarker oujda;
	BasicMarker fes;
	BasicMarker marrakech;
	BasicMarker tanger;
	BasicMarker hoceima;
	BasicMarker errachidia;
	BasicMarker tetouan;
	BasicMarker zate;
	BasicMarker nador;
	BasicMarker essaouira;
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
	ComboBox eairports;
	DQSPServer __server;
	VerticalLayout vlayout = new VerticalLayout();
	HorizontalLayout hlayout = new HorizontalLayout();
	Panel _vpanel = new Panel();
	JFreeChart evo;
	JFreeChartWrapper evoWrap;
	Button esave;

	// Comparisons
	Window compa;
	ComboBox compaType;
	ComboBox _history;
	ComboBox compaTheme;
	HorizontalLayout __hlayout = new HorizontalLayout();
	VerticalLayout __vlayout = new VerticalLayout();
	Panel __vpanel = new Panel();
	JFreeChart compChart;
	JFreeChartWrapper compChartWrap;
	Button saveComp;
	DQSPServer ___server;

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

				eairports = new ComboBox("Aéroport");
				eairports.setInputPrompt("Sélectionnez un aéroport");
				eairports.setIcon(new ThemeResource(
						"icons/actions/identity.png"));
				eairports.setImmediate(true);
				eairports.setNullSelectionAllowed(false);

				themes = new ComboBox("Thème");
				themes.setInputPrompt("Sélectionnez un thème");
				themes.setVisible(false);
				themes.setIcon(new ThemeResource(
						"icons/actions/connect_creating.png"));
				themes.setImmediate(true);
				themes.setNullSelectionAllowed(false);

				options = new ComboBox("Type d'évolution");
				options.setIcon(new ThemeResource("icons/actions/history.png"));
				options.addItem("Mensuelle");
				options.addItem("Annuelle");
				options.setInputPrompt("Choisissez un type");
				options.setImmediate(true);
				options.setNullSelectionAllowed(false);
				options.setVisible(false);

				esave = new Button();
				esave.setIcon(new ThemeResource("icons/actions/filesave.png"));
				esave.setVisible(false);

				vlayout.setSpacing(true);
				vlayout.setMargin(true);
				vlayout.setImmediate(true);
				_vpanel.addComponent(vlayout);
				_vpanel.setVisible(false);

				// to ensure the repaint of the horizontal layout
				hlayout.removeAllComponents();

				hlayout.setSpacing(true);
				hlayout.setSpacing(true);
				hlayout.addComponent(eairports);
				hlayout.setComponentAlignment(eairports, Alignment.BOTTOM_LEFT);
				hlayout.addComponent(themes);
				hlayout.addComponent(options);
				hlayout.setComponentAlignment(options, Alignment.BOTTOM_RIGHT);
				hlayout.addComponent(esave);
				hlayout.setComponentAlignment(esave, Alignment.BOTTOM_RIGHT);

				evolutions.addComponent(hlayout);
				evolutions.addComponent(_vpanel);

				// filling the airports
				__server = new DQSPServerI();
				for (String s : __server.listOfAirports()) {
					eairports.addItem(s);
				}

				// listener for airports
				eairports.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						// checking if there are any claims for the selected
						// airport, so the existence of themes
						if (__server.listOfThemes(
								String.valueOf(eairports.getValue())).size() != 0) {

							// filling themes
							for (String s : __server.listOfThemes(String
									.valueOf(eairports.getValue()))) {
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

									// Default behavior
									// creating and wrapping the chart
									evoWrap = new JFreeChartWrapper(
											createChart(
													createMonthlyDataset(
															String.valueOf(eairports
																	.getValue()),
															String.valueOf(themes
																	.getValue())),
													String.valueOf(themes
															.getValue()),
													"Nombre de Réclamations",
													"MMM-yyyy"));
									vlayout.addComponent(evoWrap);
									vlayout.setComponentAlignment(evoWrap,
											Alignment.MIDDLE_CENTER);
									_vpanel.setVisible(true);
									esave.setVisible(true);
									evolutions.setSizeFull();
									evolutions.center();
									options.setValue("Mensuelle");
									// listener for the save button
									esave.addListener(new Button.ClickListener() {
										@Override
										public void buttonClick(ClickEvent event) {
											if (String.valueOf(
													options.getValue()).equals(
													"Mensuelle")) {
												// saving a monthly chart
												try {
													ChartUtilities
															.saveChartAsJPEG(
																	new File(
																			"/home/matrix/Desktop/graphs/m_evoGraph.jpg"),
																	createChart(
																			createMonthlyDataset(
																					String.valueOf(eairports
																							.getValue()),
																					String.valueOf(themes
																							.getValue())),
																			themes.getValue()
																					.toString(),
																			"Nombre de Réclamations",
																			"MMM-yyyy"),
																	500, 300);

												} catch (IOException e) {
													e.printStackTrace();
												}
												__app.getMainWindow()
														.showNotification(
																"Notification",
																"Graphe Enregistré",
																Window.Notification.TYPE_TRAY_NOTIFICATION);
											}
										}
									});

									// decide which type of evolution you want
									options.addListener(new Property.ValueChangeListener() {
										@Override
										public void valueChange(
												ValueChangeEvent event) {
											if (String.valueOf(
													options.getValue()).equals(
													"Mensuelle")) {

												vlayout.removeAllComponents();
												// creating and wrapping the
												// chart
												evoWrap = new JFreeChartWrapper(
														createChart(
																createMonthlyDataset(
																		String.valueOf(eairports
																				.getValue()),
																		String.valueOf(themes
																				.getValue())),
																themes.getValue()
																		.toString(),
																"Nombre de Réclamations",
																"MMM-yyyy"));
												vlayout.addComponent(evoWrap);
												vlayout.setComponentAlignment(
														evoWrap,
														Alignment.MIDDLE_CENTER);
												_vpanel.setVisible(true);
												esave.setVisible(true);
												evolutions.setSizeFull();
												evolutions.center();
											} else if (String.valueOf(
													options.getValue()).equals(
													"Annuelle")) {
												vlayout.removeAllComponents();
												// creating and wrapping the
												// chart
												evoWrap = new JFreeChartWrapper(
														createChart(
																createAnnualDataset(
																		String.valueOf(eairports
																				.getValue()),
																		String.valueOf(themes
																				.getValue())),
																themes.getValue()
																		.toString(),
																"Nombre de Réclamations",
																"yyyy"));
												vlayout.addComponent(evoWrap);
												vlayout.setComponentAlignment(
														evoWrap,
														Alignment.MIDDLE_CENTER);
												_vpanel.setVisible(true);
												esave.setVisible(true);
												evolutions.setSizeFull();
												evolutions.center();
												// setting a listener for the
												// save button for annual_data
												esave.addListener(new Button.ClickListener() {
													@Override
													public void buttonClick(
															ClickEvent event) {
														// saving an annual
														// chart
														try {
															ChartUtilities
																	.saveChartAsJPEG(
																			new File(
																					"/home/matrix/Desktop/graphs/a_evoGraph.jpg"),
																			createChart(
																					createMonthlyDataset(
																							String.valueOf(eairports
																									.getValue()),
																							String.valueOf(themes
																									.getValue())),
																					themes.getValue()
																							.toString(),
																					"Nombre de Réclamations",
																					"yyyy"),
																			500,
																			300);

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
											}
										}
									});
								}
							});

						} else {
							esave.setVisible(false);
							themes.setVisible(false);
							options.setVisible(false);
							_vpanel.setVisible(false);
							evolutions.setWidth("400px");
							evolutions.setHeight("200px");
							evolutions.center();
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

				airports = new ComboBox("Aéroport");
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

				compa = new Window("Comparaisons");
				compa.center();
				compa.setWidth("400px");
				compa.setHeight("200px");
				compa.setImmediate(true);
				compa.removeAllComponents();
				// connecting to server
				___server = new DQSPServerI();

				// to ensure the repaint of the horizontal layout
				__hlayout.removeAllComponents();

				compaType = new ComboBox("Type de Comparaison");
				compaType.setImmediate(true);
				compaType.setIcon(new ThemeResource("icons/actions/misc.png"));
				compaType.setInputPrompt("Choisissez un type");
				compaType.addItem("globale");
				compaType.addItem("thématique");
				compaType.setNullSelectionAllowed(false);
				__hlayout.addComponent(compaType);
				__hlayout.setComponentAlignment(compaType,
						Alignment.BOTTOM_LEFT);

				_history = new ComboBox("Année");
				_history.setImmediate(true);
				_history.setInputPrompt("Année");
				_history.setIcon(new ThemeResource("icons/actions/history.png"));
				_history.setVisible(false);
				_history.setNullSelectionAllowed(false);
				__hlayout.addComponent(_history);
				__hlayout.setComponentAlignment(_history,
						Alignment.BOTTOM_RIGHT);
				for (Integer i : ___server.listOfYears()) {
					_history.addItem(i);
				}

				compaTheme = new ComboBox("Thème");
				compaTheme
						.setIcon(new ThemeResource("icons/actions/blend.png"));
				compaTheme.setVisible(false);
				compaTheme.setImmediate(true);
				compaTheme.setNullSelectionAllowed(false);
				compaTheme.setInputPrompt("Choisissez un thème");
				// filling themes
				for (String s : ___server.listOfThemesInClaims()) {
					compaTheme.addItem(s);
				}
				__hlayout.addComponent(compaTheme);
				__hlayout.setComponentAlignment(compaTheme,
						Alignment.BOTTOM_RIGHT);

				saveComp = new Button();
				saveComp.setIcon(new ThemeResource("icons/actions/filesave.png"));
				saveComp.setDescription("Enregistrer");
				saveComp.setVisible(false);
				__hlayout.addComponent(saveComp);
				__hlayout.setComponentAlignment(saveComp,
						Alignment.BOTTOM_RIGHT);

				__hlayout.setSpacing(true);
				compa.addComponent(__hlayout);

				__vlayout.setSpacing(true);
				__vlayout.setMargin(true);
				__vlayout.setImmediate(true);
				__vpanel.addComponent(__vlayout);
				__vpanel.setVisible(false);
				compa.addComponent(__vpanel);

				// adding listener to comparison type
				compaType.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {

						/*
						 * _history.setVisible(true);
						 * __vlayout.removeAllComponents();
						 * compaTheme.setVisible(false); //_history.setValue(new
						 * Integer(2011)); compChart =
						 * createChart(createGlobalDataset(2011),
						 * "Comparaisons Globale",
						 * "Nombre de Réclamations/Nombre de passagers traités"
						 * ,"MMM-yyyy"); compChartWrap = new
						 * JFreeChartWrapper(compChart);
						 * __vlayout.addComponent(compChartWrap);
						 * __vlayout.setComponentAlignment(compChartWrap,
						 * Alignment.MIDDLE_CENTER); compa.setSizeFull();
						 * compa.center(); __vpanel.setVisible(true); //for save
						 * button saveComp.setVisible(true);
						 * saveComp.addListener(new Button.ClickListener() {
						 * 
						 * @Override public void buttonClick(ClickEvent event) {
						 * try { ChartUtilities.saveChartAsJPEG(new
						 * File("/home/matrix/Desktop/graphs/graph.jpg"),
						 * compChart, 600, 500); } catch (IOException e) {
						 * e.printStackTrace(); } //displaying a notification
						 * __app.getMainWindow() .showNotification(
						 * "Notification", "Graphe Enregistré",
						 * Window.Notification.TYPE_TRAY_NOTIFICATION); } });
						 */

						if (String.valueOf(compaType.getValue()).equals(
								"globale")) {
							__vlayout.removeAllComponents();
							_history.setVisible(true);
							_history.setValue(new Integer(2011));
							compaTheme.setVisible(false);

							// Default Behavior
							compChart = createChart(
									createGlobalDataset((Integer) _history
											.getValue()),
									"Évolution annuelle du nombre totale de réclamations",
									"Nombre de Réclamations/Nombre de passagers traités",
									"MMM-yyyy");
							compChartWrap = new JFreeChartWrapper(compChart);
							__vlayout.addComponent(compChartWrap);
							__vlayout.setComponentAlignment(compChartWrap,
									Alignment.MIDDLE_CENTER);
							compa.setSizeFull();
							compa.center();
							__vpanel.setVisible(true);
							// for save button
							saveComp.setVisible(true);
							saveComp.addListener(new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									try {
										ChartUtilities
												.saveChartAsJPEG(
														new File(
																"/home/matrix/Desktop/graphs/comp_graph_g.jpg"),
														compChart, 500, 400);
									} catch (IOException e) {
										e.printStackTrace();
									}
									// displaying a notification
									__app.getMainWindow()
											.showNotification(
													"Notification",
													"Graphe Enregistré",
													Window.Notification.TYPE_TRAY_NOTIFICATION);
								}
							});
							// ------------------------------------------------------------
							_history.addListener(new Property.ValueChangeListener() {

								@Override
								public void valueChange(ValueChangeEvent event) {

									compChart = createChart(
											createGlobalDataset((Integer) _history
													.getValue()),
											"Évolution annuelle du nombre totale de réclamations",
											"Nombre de Réclamations/Nombre de passagers traités",
											"MMM-yyyy");
									compChartWrap = new JFreeChartWrapper(
											compChart);
									__vlayout.addComponent(compChartWrap);
									__vlayout.setComponentAlignment(
											compChartWrap,
											Alignment.MIDDLE_CENTER);
									compa.setSizeFull();
									compa.center();
									__vpanel.setVisible(true);
									// for save button
									saveComp.setVisible(true);
									saveComp.addListener(new Button.ClickListener() {

										@Override
										public void buttonClick(ClickEvent event) {
											try {
												ChartUtilities
														.saveChartAsJPEG(
																new File(
																		"/home/matrix/Desktop/graphs/comp_graph_g.jpg"),
																compChart, 500,
																400);
											} catch (IOException e) {
												e.printStackTrace();
											}
											// displaying a notification
											__app.getMainWindow()
													.showNotification(
															"Notification",
															"Graphe Enregistré",
															Window.Notification.TYPE_TRAY_NOTIFICATION);
										}
									});
								}
							});

						}

						if (String.valueOf(compaType.getValue()).equals(
								"thématique")) {
							__vlayout.removeAllComponents();
							_history.setVisible(true);
							compaTheme.setVisible(true);
							__vpanel.setVisible(false);
							compaTheme
									.addListener(new Property.ValueChangeListener() {

										@Override
										public void valueChange(
												ValueChangeEvent event) {
											__vlayout.removeAllComponents();
											compChart = createChart(
													createThemeDataset(
															(Integer) _history
																	.getValue(),
															String.valueOf(compaTheme
																	.getValue())),
													"Comparaisons thématique '"
															+ compaTheme
																	.getValue()
																	.toString()
															+ "'",
													"Nombre de Réclamations/Nombre de passagers traités",
													"MMM-yyyy");
											compChartWrap = new JFreeChartWrapper(
													compChart);
											__vlayout
													.addComponent(compChartWrap);
											__vlayout.setComponentAlignment(
													compChartWrap,
													Alignment.MIDDLE_CENTER);
											__vpanel.setVisible(true);
											// for save button
											saveComp.setVisible(true);
											saveComp.addListener(new Button.ClickListener() {

												@Override
												public void buttonClick(
														ClickEvent event) {
													try {
														ChartUtilities
																.saveChartAsJPEG(
																		new File(
																				"/home/matrix/Desktop/graphs/comp_graph_t.jpg"),
																		compChart,
																		500,
																		400);
													} catch (IOException e) {
														e.printStackTrace();
													}
													// displaying a notification
													__app.getMainWindow()
															.showNotification(
																	"Notification",
																	"Graphe Enregistré",
																	Window.Notification.TYPE_TRAY_NOTIFICATION);
												}
											});
										}
									});

							_history.addListener(new Property.ValueChangeListener() {

								@Override
								public void valueChange(ValueChangeEvent event) {
									compaTheme.setVisible(true);
									compa.setSizeFull();
									compa.center();
								}
							});
						}

					}
				});

				__app.getMainWindow().addWindow(compa);
			}
		});

		// Points Faibles/Forts
		menu.addButton("Points Forts/Faibles", new TouchMenu.Command() {

			@Override
			public void menuSelected(TouchMenuButton selectedButton) {
				map = new Window("Points Forts/Faibles");
				map.center();
				__app.getMainWindow().removeWindow(map);
				// map.setWidth("680px");
				// map.setHeight("560px");
				map.setSizeFull();
				map.setImmediate(true);
				__map = getMap();
				if (__map == null) {
					__app.getMainWindow().showNotification("Google Map",
							"Connection au serveur réfusée",
							Window.Notification.TYPE_TRAY_NOTIFICATION);
					return;
				}
				mapLayout.removeAllComponents();
				mapLayout.addComponent(__map);
				mapLayout.setComponentAlignment(__map, Alignment.MIDDLE_CENTER);
				map.addComponent(mapLayout);

				__app.getMainWindow().addWindow(map);
			}
		});

		addComponent(menu);
		setComponentAlignment(menu, "center");
	}

	/*
	 * Checks if an Internet connection can be established
	 * 
	 * @param null
	 */
	boolean isServerReacheable() {
		InetAddress inet = null;
		try {
			inet = InetAddress.getByName("maps.google.com");
			if (inet == null) {
				return false;
			}
		} catch (UnknownHostException e) {
			return false;
		}
		return true;
	}

	/*
	 * builds the Map
	 * 
	 * @param NULL
	 */
	GoogleMap getMap() {

		GoogleMap googleMap = null;

		// verify is there an Internet connection
		if (!isServerReacheable()) {
			return null;
		} else {

			googleMap = new GoogleMap(__app, new Point2D.Double(-9.546196,
					30.380894), 6);

			googleMap.setWidth("1000px");
			googleMap.setHeight("730px");
			// googleMap.setSizeFull();
			googleMap.setImmediate(true);

			casablanca = new BasicMarker(1L, new Point2D.Double(-7.589722,
					33.367222), "Aéroport international de Mohamed V");
			casablanca.setDraggable(false);
			casablanca.setInfoWindowContent(null,
					createInfoWindow(casablanca.getTitle()));

			rabat = new BasicMarker(2L,
					new Point2D.Double(-6.750000, 34.050000),
					"Aéroport de Rabat/Salé");
			rabat.setDraggable(false);
			rabat.setInfoWindowContent(null, createInfoWindow(rabat.getTitle()));

			guelmimAirBase = new BasicMarker(3L, new Point2D.Double(-10.052284,
					29.025658), "Guelmim Air Base");
			guelmimAirBase.setDraggable(false);
			guelmimAirBase.setInfoWindowContent(null,
					createInfoWindow(guelmimAirBase.getTitle()));

			agadir = new BasicMarker(4L, new Point2D.Double(-9.546196,
					30.380894), "AGADIR Al Massira");
			agadir.setDraggable(false);
			agadir.setInfoWindowContent(null,
					createInfoWindow(agadir.getTitle()));

			oujda = new BasicMarker(5L,
					new Point2D.Double(-1.925556, 34.786111), "Aéroport Angads");
			oujda.setDraggable(false);
			oujda.setInfoWindowContent(null, createInfoWindow(oujda.getTitle()));

			fes = new BasicMarker(6L, new Point2D.Double(-4.979167, 33.927222),
					"Aéroport de Saiss");
			fes.setDraggable(false);
			fes.setInfoWindowContent(null, createInfoWindow(fes.getTitle()));

			marrakech = new BasicMarker(7L, new Point2D.Double(-8.036284,
					31.606975), "Aéroport international de Menara");
			marrakech.setDraggable(false);
			marrakech.setInfoWindowContent(null,
					createInfoWindow(marrakech.getTitle()));

			tanger = new BasicMarker(8L, new Point2D.Double(-5.922038,
					35.729202), "Aéroport international Ibn Batouta");
			tanger.setDraggable(false);
			tanger.setInfoWindowContent(null,
					createInfoWindow(tanger.getTitle()));

			hoceima = new BasicMarker(9L, new Point2D.Double(-3.839891,
					35.179557), "Aéroport Idrissi Cherif");
			hoceima.setDraggable(false);
			hoceima.setInfoWindowContent(null,
					createInfoWindow(hoceima.getTitle()));

			errachidia = new BasicMarker(10L, new Point2D.Double(-4.399053,
					31.947011), "Aéroport de Moulay Ali Cherif");
			errachidia.setDraggable(false);
			errachidia.setInfoWindowContent(null,
					createInfoWindow(errachidia.getTitle()));

			tetouan = new BasicMarker(11L, new Point2D.Double(-5.316667,
					35.583332), "Aéroport de Sania Ramel");
			tetouan.setDraggable(false);
			tetouan.setInfoWindowContent(null,
					createInfoWindow(tetouan.getTitle()));

			zate = new BasicMarker(12L,
					new Point2D.Double(-6.909488, 30.939036),
					"Aéroport Ouarzazate");
			zate.setDraggable(false);
			zate.setInfoWindowContent(null, createInfoWindow(zate.getTitle()));

			nador = new BasicMarker(13L, new Point2D.Double(-3.029180,
					34.989125), "Aéroport international de Nador");
			nador.setDraggable(false);
			nador.setInfoWindowContent(null, createInfoWindow(nador.getTitle()));

			essaouira = new BasicMarker(14L, new Point2D.Double(-9.683933,
					31.396647), "Aéroport de Mogador");
			essaouira.setDraggable(false);
			essaouira.setInfoWindowContent(null,
					createInfoWindow(essaouira.getTitle()));

			// add Markers to Map
			googleMap.addMarker(casablanca);
			googleMap.addMarker(rabat);
			googleMap.addMarker(guelmimAirBase);
			googleMap.addMarker(agadir);
			googleMap.addMarker(oujda);
			googleMap.addMarker(fes);
			googleMap.addMarker(marrakech);
			googleMap.addMarker(tanger);
			googleMap.addMarker(hoceima);
			googleMap.addMarker(errachidia);
			googleMap.addMarker(tetouan);
			googleMap.addMarker(zate);
			googleMap.addMarker(nador);
			googleMap.addMarker(essaouira);

			// specifying some controls
			googleMap.addControl(MapControl.MenuMapTypeControl);
			googleMap.addControl(MapControl.LargeMapControl);
			googleMap.addControl(MapControl.OverviewMapControl);
			googleMap.addControl(MapControl.ScaleControl);

		}
		return googleMap;
	}

	/*
	 * returns a list of remarks touched by claims per airport
	 * 
	 * @param airport
	 * 
	 * @return myList
	 */
	public java.util.List<String> listOfPointsFaibles(String airport) {
		java.util.List<String> myList = new java.util.ArrayList<String>();

		// connecting to database
		Connection c = DBConnexion.getConnection();

		try {
			PreparedStatement ps = c
					.prepareStatement("SELECT r.`remarque` FROM reclamation r WHERE r.`nomAeroport`=? GROUP BY r.`remarque`");
			ps.setString(1, airport);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				myList.add(rs.getString("remarque"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myList;
	}

	/*
	 * returns a list of remarks not touched by claims per airport
	 * 
	 * @param airport
	 * 
	 * @return myList
	 */
	java.util.List<String> listOfPointsForts(String airport) {
		java.util.List<String> myList = new java.util.ArrayList<String>();

		// Array of available remarks
		String[] remarks = new String[] { "Liaison ville/aérogare",
				"La signalisation routière", "La signalisation des services",
				"L'accès à l'aéroport", "Circulation verticale",
				"Tableaux d'affichage", "Les chariots à bagages",
				"Les compagnies aériennes", "Comportement des agents",
				"Tapis livraison bagages",
				"Les facilitations aux Personnes à Mobilité Réduite",
				"Les facilitations MRE (marocains résidents à l'étranger)",
				"Les moyens de transport", "Passerelles", "Horloges", "Papier",
				"Apparence/Visiblité", "Eclairages", "Salles d'embarquement",
				"Salles de débarquement", "Espaces Verts" };

		for (String st : remarks) {
			if (!listOfPointsFaibles(airport).contains(st)) {
				myList.add(st);
			}
		}
		return myList;
	}

	/*
	 * returns a Panel for info markers
	 * 
	 * @param airport
	 * 
	 * @return panel
	 */
	@SuppressWarnings("deprecation")
	Panel createInfoWindow(String airport) {

		Panel mainPanel = new Panel();
		mainPanel.setSizeUndefined();
		mainPanel.setIcon(new ThemeResource("icons/actions/view_sidetree.png"));
		// to layout each panel
		HorizontalLayout content = new HorizontalLayout();

		// Points Faibles
		VerticalLayout vlayout = new VerticalLayout();
		vlayout.setSpacing(true);
		vlayout.setSizeUndefined();

		if (listOfPointsFaibles(airport).size() != 0) {
			for (String st : listOfPointsFaibles(airport)) {
				vlayout.addComponent(new Label(st));
			}
		} else {
			vlayout.addComponent(new Label("-/-"));
		}
		Panel fpanel = new Panel("Points Faibles");
		fpanel.setSizeUndefined();
		fpanel.setStyleName(Panel.STYLE_LIGHT);
		fpanel.addComponent(vlayout);
		content.addComponent(fpanel);

		// Points Forts
		VerticalLayout __vlayout = new VerticalLayout();
		__vlayout.setSpacing(true);
		__vlayout.setSizeUndefined();
		if (listOfPointsForts(airport).size() != 21) {
			for (String st : listOfPointsForts(airport)) {
				__vlayout.addComponent(new Label(st));
			}
		} else {
			__vlayout.addComponent(new Label("*.*"));
		}
		Panel _fpanel = new Panel("Points Forts");
		_fpanel.setSizeUndefined();
		_fpanel.setStyleName(Panel.STYLE_LIGHT);
		_fpanel.addComponent(__vlayout);
		content.addComponent(_fpanel);

		mainPanel.addComponent(content);
		return mainPanel;
	}

	/*
	 * creates a time series chart
	 * 
	 * @param dataset, title, dateFormat
	 * 
	 * @return chart
	 */
	JFreeChart createChart(XYDataset dataset, String title, String legend,
			String dateFormat) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Date",
				legend, dataset, true, true, false);

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
		// getting a Connection
		Connection con = DBConnexion.getConnection();

		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT nomAeroport, YEAR(r.`date`) `annee`, remarque, count(YEAR(r.`date`)) num FROM reclamation r WHERE r.`nomAeroport`=? AND remarque=? GROUP BY annee");
			ps.setString(1, airport);
			ps.setString(2, remarque);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s.add(new Year(rs.getInt("annee")), rs.getInt("num"));
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
	 * returns annual data set for each airport
	 * 
	 * @param year
	 * 
	 * @return a data set
	 */
	XYDataset createGlobalDataset(Integer year) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();

		// calling the server
		DQSPServer s = new DQSPServerI();
		// getting a connection with database
		Connection con = DBConnexion.getConnection();

		for (String st : s.listOfAirports()) {
			if (s.numberOfPassengers(st) != 0) {
				try {
					PreparedStatement ps = con
							.prepareStatement("SELECT count(*) num, MONTH(r.`date`) mois, YEAR(r.`date`) annee FROM reclamation r WHERE r.`nomAeroport`=? AND YEAR(r.`date`)=?");
					ps.setString(1, st);
					ps.setInt(2, year);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						TimeSeries serie = new TimeSeries(st);
						try {
							serie.add(
									new Month(rs.getInt("mois"), year
											.intValue()),
									rs.getInt("num") / s.numberOfPassengers(st));
						} catch (Exception ex) {
							__app.getMainWindow().showNotification(
									"Notification", "No Data",
									Window.Notification.TYPE_TRAY_NOTIFICATION);
						}
						dataset.addSeries(serie);
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				TimeSeries _serie = new TimeSeries(st);
				_serie.add(new Month(1, year.intValue()), 1);
				dataset.addSeries(_serie);
			}
		}

		return dataset;
	}

	/*
	 * returns annual data set for each airport per theme
	 * 
	 * @param year
	 * 
	 * @return a data set
	 */
	XYDataset createThemeDataset(Integer year, String theme) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();

		// calling the server
		DQSPServer s = new DQSPServerI();
		// getting a connection with database
		Connection con = DBConnexion.getConnection();

		for (String st : s.listOfAirports()) {
			if (s.numberOfPassengers(st) != 0) {
				try {
					PreparedStatement ps = con
							.prepareStatement("SELECT count(*) num, MONTH(r.`date`) mois, YEAR(r.`date`) annee FROM reclamation r WHERE r.`nomAeroport`=? AND YEAR(r.`date`)=? AND r.`remarque`=?");
					ps.setString(1, st);
					ps.setInt(2, year);
					ps.setString(3, theme);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						TimeSeries serie = new TimeSeries(st);
						try {
							serie.add(
									new Month(rs.getInt("mois"), year
											.intValue()),
									rs.getInt("num") / s.numberOfPassengers(st));
						} catch (Exception ex) {
							__app.getMainWindow().showNotification(
									"Notification", "No data",
									Window.Notification.TYPE_TRAY_NOTIFICATION);
						}
						dataset.addSeries(serie);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				TimeSeries _serie = new TimeSeries(st);
				_serie.add(new Month(1, year.intValue()), 1);
				dataset.addSeries(_serie);
			}
		}

		return dataset;
	}

}
