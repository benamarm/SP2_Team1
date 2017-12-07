package gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import APIFiles.GoogleBooks;
import APIFiles.GoogleBooksExecutableQuery;
import APIFiles.GoogleBooksQueryPrefix;
import antlr.collections.List;
import database.OpleidingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import logic.Boek;
import logic.Opleiding;
import javafx.scene.input.MouseEvent;

public class BoekenController {
	
	private boolean selecteerAlles = false;

	@FXML
	TableView<Boek> boekenlijst;
	@FXML
	TableColumn<Boek, String> colTitel;
	@FXML
	TableColumn<Boek, String> colAuteur;
	@FXML
	TableColumn<Boek, String> colBeschrijving;
	@FXML
	TableColumn<Boek, String> colPrijs;
	@FXML
	CheckBox bSelecteerAlles;
	@FXML
	Button bAdd;
	@FXML
	Label bRem;
	@FXML
	TextField taSearch;
	@FXML
	MenuButton mbOpleiding;
	@FXML
	MenuItem bToevoegen;
	
	private ObservableList<Button> buttons = 
    FXCollections.observableArrayList();
	
	@FXML
	private void handleSelecteerAlles() {
		this.selecteerAlles = !this.selecteerAlles;
		if(this.selecteerAlles) 
			boekenlijst.getSelectionModel().selectAll();
		else
			boekenlijst.getSelectionModel().clearSelection();
	}
	
	@FXML
	private void handleRemove() {
		
	}
	
	 public static String invertCapitals(String other) {
	        return other.chars().mapToObj(BoekenController::flipCap)
	                            .map(c -> Character.toString(c))
	                            .reduce("", (s, c) -> s + c);
	    }
	 
	 public static Character flipCap(int c) {
	        if (c >= 'A' && c <= 'Z') {
	           return (char)(c - 'A' + 'a');
	        } else if (c >= 'a' && c <= 'z') {
	           return (char)(c - 'a' + 'A');
	        } else {
	           return (char)c;
	        }
	    }
	 
	 
//	@FXML
//	private void handleBoekenlijst() {
//		ObservableList<Opleiding> opleidingen = OpleidingDAO.getAll();
//		JsonFactory factory = JacksonFactory.getDefaultInstance();
//		GoogleBooksExecutableQuery query;
//		ArrayList<Boek> boeken;
//		query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, opleidingen.get(i).getNaam());
//		try {
//			boeken = GoogleBooks.executeQuery(factory, query);
//			Button b;
//			
//			b = new Button(boeken.get(i).getTitel());
//	        buttons.add(b);
//	        b.addEventHandler(MouseEvent.MOUSE_CLICKED, 
//	            (event -> b.setText(invertCapitals(b.getText()))));
//	        //button.setText("");
//	    			} catch (Exception e) {
//	    				// TODO Auto-generated catch block
//	    				e.printStackTrace();
//	    			}
//	    			
//	}
	
	
	@FXML
	private void handleOpleiding() {
		ObservableList<Opleiding> opleidingen;
		JsonFactory factory = JacksonFactory.getDefaultInstance();
		GoogleBooksExecutableQuery query;
		ArrayList<Button> buttons = new ArrayList<Button>();
		try {
			opleidingen = OpleidingDAO.getObservables();
			for(int i = 0; i < opleidingen.size(); i++) {
				Button b = new Button(opleidingen.get(i).getNaam());
		        buttons.add(b);
		        b.addEventHandler(MouseEvent.MOUSE_CLICKED, 
		            (event -> b.setText(invertCapitals(b.getText()))));
		        //button.setText("");
			}
		   //button.setText("");
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@FXML
	public void initialize() {
		
		taSearch.setPromptText("Doorzoek hier de hele bibliotheek...");
		boekenlijst.setPlaceholder(new Label("Er zijn momenteel geen boeken."));

		colTitel.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getTitel());
			}
		});
		colAuteur.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getAuteurs().get(0));
			}
		});
		colBeschrijving.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
						return new SimpleStringProperty(data.getValue().getBeschrijving());
					}
				});
		
		boekenlijst.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		 GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, "Banking");
		 
		ObservableList<Boek> list;
		try {
			list = FXCollections.observableArrayList(GoogleBooks.executeQuery(JacksonFactory.getDefaultInstance(), query));
			boekenlijst.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
