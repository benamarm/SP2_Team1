package gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.collections.ObservableList;

import database.EventDAO;
import database.OpleidingDAO;
import database.PersoneelDAO;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;

public class StatistiekController {
	@FXML
	TableView<Event> eventen;
	@FXML
	TableColumn<Event, Integer> colId;
	@FXML
	TableColumn<Event, String> colNaam;
	@FXML
	TableColumn<Event, Integer> colDeelnemers;
	@FXML
	TableColumn<Event,Integer> colAantal_deelnames;
	@FXML
	ComboBox<Opleiding> events;
	@FXML
	ComboBox<Event>opleidingen;
	@FXML
	ComboBox<Opleiding>totaal ;
	@FXML
	ComboBox<Opleiding>totaal1;
	@FXML
	ComboBox<Opleiding>totaal2;
	@FXML
	ComboBox<Opleiding>totaal3;
	@FXML
	ComboBox<Opleiding>totaal4;
	
	@FXML
	Label lSelectie;
	@FXML
	Button bToevoegen;
	@FXML
	Button bBewerken;
	@FXML
	Button bBoeken; 
	@FXML
     PieChart piechart;
	@FXML
	private void setEvents() {

		events.getItems().clear();
		events.setPlaceholder(new Label("Selecteer een event."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + events.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> list = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		events.setItems(list);
		events.setPlaceholder(new Label("Geen komende events voor deze opleiding."));

	}
	@FXML
	private void setOpleiding() {
		opleidingen.getItems().clear();
		opleidingen.setPlaceholder(new Label("Selecteer een opleiding."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + opleidingen.getValue().getEventId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Event> listO = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		opleidingen.setItems(listO);
		opleidingen.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	private void setTotaal() {
		totaal.getItems().clear();
		totaal.setPlaceholder(new Label("Selecteer een opleiding ."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + totaal.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> listt = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		totaal.setItems(listt);
		totaal.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	private void setTotaal1() {
		totaal1.getItems().clear();
		totaal1.setPlaceholder(new Label("Selecteer een opleiding ."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + totaal1.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> listt1 = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		totaal1.setItems(listt1);
		totaal1.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	private void setTotaal2() {
		totaal2.getItems().clear();
		totaal2.setPlaceholder(new Label("Selecteer een opleiding ."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + totaal2.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> listt2 = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		totaal2.setItems(listt2);
		totaal2.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	private void setTotaal3() {
		totaal3.getItems().clear();
		totaal3.setPlaceholder(new Label("Selecteer een opleiding ."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + totaal3.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> listt3 = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		totaal3.setItems(listt3);
		totaal3.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	private void setTotaal4() {
		totaal4.getItems().clear();
		totaal.setPlaceholder(new Label("Selecteer een opleiding ."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + totaal4.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Opleiding> listt4 = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		totaal4.setItems(listt4);
		totaal4.setPlaceholder(new Label("Geen komende events voor deze opleiding."));
		
	}
	@FXML
	public void initialize() {
		
		
				
		ObservableList<Opleiding> list = FXCollections.observableArrayList(OpleidingDAO.getAll());
		events.setItems(list);
		
		 Callback<ListView<Opleiding>, ListCell<Opleiding>> call = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding ex, boolean empty) {

	                super.updateItem(ex, empty);

	                setText(ex == null ? "" : ((ex.getNaam())) );
	                
	            }
	            
	            

		 };
		 ObservableList<Event> listO = FXCollections.observableArrayList(EventDAO.getAll());
         opleidingen.setItems(listO);
         Callback<ListView<Event>, ListCell<Event>> callo = lv -> new ListCell<Event>() {
	            @Override
	            protected void updateItem(Event o, boolean empty) {

	                super.updateItem(o, empty);

	                setText(o == null ? "" : (o.getEventId()+" "+o.getOpleiding().getNaam() + " gegeven door : " + o.getNaamTrainer())  );
	                
	            }
         
         };
         ObservableList<Opleiding> listt = FXCollections.observableArrayList(OpleidingDAO.getAll());
         totaal.setItems(listt);
         Callback<ListView<Opleiding>, ListCell<Opleiding>> callt = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding t, boolean empty) {

	                super.updateItem(t, empty);

	                setText(t == null ? "" : (t.getNaam())  );
	                
	            }
         
         };
         ObservableList<Opleiding> listt1 = FXCollections.observableArrayList(OpleidingDAO.getAll());
         totaal1.setItems(listt1);
         Callback<ListView<Opleiding>, ListCell<Opleiding>> callt1 = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding t1, boolean empty) {

	                super.updateItem(t1, empty);

	                setText(t1 == null ? "" : (t1.getNaam())  );
	                
	            }
         
         };
         ObservableList<Opleiding> listt2 = FXCollections.observableArrayList(OpleidingDAO.getAll());
         totaal2.setItems(listt2);
         Callback<ListView<Opleiding>, ListCell<Opleiding>> callt2 = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding t2, boolean empty) {

	                super.updateItem(t2, empty);

	                setText(t2 == null ? "" : (t2.getNaam())  );
	                
	            }
         
         };
         ObservableList<Opleiding> listt3 = FXCollections.observableArrayList(OpleidingDAO.getAll());
         totaal3.setItems(listt3);
         Callback<ListView<Opleiding>, ListCell<Opleiding>> callt3 = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding t3, boolean empty) {

	                super.updateItem(t3, empty);

	                setText(t3 == null ? "" : (t3.getNaam())  );
	                
	            }
         
         };
         ObservableList<Opleiding> listt4 = FXCollections.observableArrayList(OpleidingDAO.getAll());
         totaal4.setItems(listt4);
         Callback<ListView<Opleiding>, ListCell<Opleiding>> callt4 = lv -> new ListCell<Opleiding>() {
	            @Override
	            protected void updateItem(Opleiding t4, boolean empty) {

	                super.updateItem(t4, empty);

	                setText(t4 == null ? "" : (t4.getNaam())  );
	                
	            }
         
         };
         
	 events.setCellFactory(call);
	events.setButtonCell(call.call(null));
	//
	opleidingen.setCellFactory(callo);
	opleidingen.setButtonCell(callo.call(null));
	//
	totaal.setCellFactory(callt);
	totaal.setButtonCell(callt.call(null));
	//
	totaal1.setCellFactory(callt1);
	totaal1.setButtonCell(callt1.call(null));
	//
	totaal2.setCellFactory(callt2);
	totaal2.setButtonCell(callt2.call(null));
	//
	totaal3.setCellFactory(callt3);
	totaal3.setButtonCell(callt3.call(null));
	//
	totaal4.setCellFactory(callt4);
	totaal4.setButtonCell(callt4.call(null));
	}
	@FXML
	  public void start() {
		  Scene scene = new Scene(new Group());
		  Stage stage = new Stage();
		 
		  
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("bezete plaatsen", EventDAO.getAantalDeelnemers(events.getValue().getOpleidingId())),
	                new PieChart.Data("open plaatsen", EventDAO.getMaxAantalDeelnemers(events.getValue().getOpleidingId())-EventDAO.getAantalDeelnemers(events.getValue().getOpleidingId()) ));

	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle(events.getValue().getNaam() );
	        final Label caption = new Label("");
	        caption.setTextFill(Color.DARKORANGE);
	        caption.setStyle("-fx-font: 24 arial;");

	        for (final PieChart.Data data : chart.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
	                    new EventHandler<MouseEvent>() {
	                        @Override public void handle(MouseEvent e) {
	                            caption.setTranslateX(e.getSceneX());
	                            caption.setTranslateY(e.getSceneY());
	                            caption.setText(String.valueOf(data.getPieValue()) 
	                                );
	                        }
	                    });
	        }
	        ((Group) scene.getRoot()).getChildren().add(chart);
	        stage.setScene(scene);
	        stage.show();      
	       
	        
	    }
	@FXML
	  public void deelnemers() {
		  Scene scene = new Scene(new Group());
		  Stage stage = new Stage();
		  
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("bezete plaatsen",opleidingen.getValue().getAantalDeelnames()),
	                new PieChart.Data("open plaatsen",opleidingen.getValue().getMaxDeelnames() - opleidingen.getValue().getAantalDeelnames() ));

	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle(opleidingen.getValue().getOpleiding().getNaam()+" gegeven door "+opleidingen.getValue().getNaamTrainer()) ;
	        final Label caption = new Label("");
	        caption.setTextFill(Color.DARKORANGE);
	        caption.setStyle("-fx-font: 24 arial;");

	        for (final PieChart.Data data : chart.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
	                    new EventHandler<MouseEvent>() {
	                        @Override public void handle(MouseEvent e) {
	                            caption.setTranslateX(e.getSceneX());
	                            caption.setTranslateY(e.getSceneY());
	                            caption.setText(String.valueOf(data.getPieValue()) 
	                                );
	                        }
	                    });
	        }
	        ((Group) scene.getRoot()).getChildren().add(chart);
	        stage.setScene(scene);
	        stage.show();      
	       
	        
	    }
	@FXML
	public void bar( ) {
        
        Stage stage = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("aantal opleidingen voor");
        xAxis.setLabel("opleiding");       
        yAxis.setLabel("Value");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Aantal events voor "+totaal.getValue().getNaam());       
        series1.getData().add(new XYChart.Data(totaal.getValue().getNaam(), EventDAO.getOpl(totaal.getValue().getOpleidingId())));
             
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Aantal events voor "+totaal1.getValue().getNaam());
        series2.getData().add(new XYChart.Data(totaal1.getValue().getNaam(), EventDAO.getOpl(totaal1.getValue().getOpleidingId())));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Aantal events voor "+totaal2.getValue().getNaam());
        series3.getData().add(new XYChart.Data(totaal2.getValue().getNaam(), EventDAO.getOpl(totaal2.getValue().getOpleidingId())));
        
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Aantal events voor "+totaal3.getValue().getNaam());
        series4.getData().add(new XYChart.Data(totaal3.getValue().getNaam(), EventDAO.getOpl(totaal3.getValue().getOpleidingId())));
       
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Aantal events voor "+totaal4.getValue().getNaam());
        series5.getData().add(new XYChart.Data(totaal4.getValue().getNaam(), EventDAO.getOpl(totaal4.getValue().getOpleidingId())));
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1,series2,series3,series4,series5);
        stage.setScene(scene);
        stage.show();
    }

	
	}

