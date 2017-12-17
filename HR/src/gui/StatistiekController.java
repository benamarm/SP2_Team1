package gui;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javafx.collections.ObservableList;

import database.EventDAO;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
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
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;

public class StatistiekController extends Application{
	@FXML
	TableView<Event> events;
	@FXML
	TableColumn<Event, Integer> colId;
	@FXML
	TableColumn<Event, String> colNaam;
	@FXML
	TableColumn<Event, Integer> colDeelnemers;
	@FXML
	TableColumn<Event,Integer> colAantal_deelnames;
	@FXML
	Label lSelectie;
	@FXML
	Button bToevoegen;
	@FXML
	Button bBewerken;
	@FXML
	Button bBoeken; 
	@FXML
    private PieChart piechart;
	@FXML
	public void initialize() {

		events.setPlaceholder(new Label("Er zijn geen events."));

		colNaam.setCellFactory(column -> {
			return new TableCell<Event, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(300);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});

		colAantal_deelnames.setCellFactory(column -> {
			return new TableCell<Event, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					setText(Integer.toString(item));
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip();
						t.setMaxWidth(300);
						t.setWrapText(true);
						setTooltip(t);
					}
				}

				
			};
		});

		

		ObservableList<Event> list = FXCollections.observableArrayList(EventDAO.getAll());
		events.setItems(list);
		 Callback<ListView<Event>, ListCell<Event>> call = lv -> new ListCell<Event>() {
	            @Override
	            protected void updateItem(Event ex, boolean empty) {

	                super.updateItem(ex, empty);

	                setText(ex == null ? "" : (ex.getEventId() + " - " + ex.getNaamTrainer()));

	            }



	        };
	}
    
	  public void start(Stage stage) {
	        Scene scene = new Scene(new Group());
	        stage.setTitle("c++");
	        stage.setWidth(500);
	        stage.setHeight(500);
	        
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("bezet", Event.getAantalDeelnames()),
	                new PieChart.Data("open", Event.getMaxDeelnames()-Event.getAantalDeelnames())
	                );

	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle(Opleiding.getNaam());
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
	                
	        ((Group) scene.getRoot()).getChildren().addAll(chart, caption);
	        stage.setScene(scene);
	        stage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	       
	    }
	}
	

	
