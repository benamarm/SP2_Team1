package gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javafx.collections.ObservableList;

import database.EventDAO;
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
import javafx.scene.chart.PieChart;
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
	ComboBox<Event> events;
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
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + events.getValue().getEventId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Event> list = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		events.setItems(list);
		events.setPlaceholder(new Label("Geen komende events voor deze opleiding."));

	}
	@FXML
	public void initialize() {
		
		

		ObservableList<Event> list = FXCollections.observableArrayList(EventDAO.getAll());
		events.setItems(list);
	
		 Callback<ListView<Event>, ListCell<Event>> call = lv -> new ListCell<Event>() {
	            @Override
	            protected void updateItem(Event ex, boolean empty) {

	                super.updateItem(ex, empty);

	                setText(ex == null ? "" : (ex.getEventId() + " - " + ex.getOpleiding().getNaam()) + " Door " + ex.getNaamTrainer());
	                
	            }


		 };
		 
	 events.setCellFactory(call);
	events.setButtonCell(call.call(null));
	
	
		 
	}
	@FXML
	  public void start() {
		  Scene scene = new Scene(new Group());
		  Stage stage = new Stage();
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("bezete plaatsen",events.getValue().getAantalDeelnames()),
	                new PieChart.Data("open plaatsen", (events.getValue().getMaxDeelnames()) - (events.getValue().getAantalDeelnames()))
	                );

	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle(events.getValue().getOpleiding().getNaam() );
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

	
	}

