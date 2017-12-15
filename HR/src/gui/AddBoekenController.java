package gui;

import java.text.DecimalFormat;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import database.OpleidingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Boek;
import logic.GoogleBooks;
import logic.GoogleBooksExecutableQuery;
import logic.GoogleBooksQueryPrefix;
import logic.Opleiding;
import javafx.scene.control.Button;

public class AddBoekenController {
	
	@FXML
	TextField lSearch;
	
	@FXML
	TableView<Boek> boeken;
	
	@FXML
	TableColumn<Boek, String> colTitel;
	
	@FXML
	TableColumn<Boek, String> colPrijs;
	
	@FXML
	CheckBox select;
	
	@FXML
	Button doorgaan;
	
	@FXML
	Button annuleren;
	
	@FXML
	Label lWarning;
	
	@FXML
	Label lUitleg;
	
	@FXML
	Label lTotaalPrijs;
	
	private Opleiding selectedOpleiding;
	
	
	public Opleiding getSelectedOpleiding() {
		return selectedOpleiding;
	}
	
	@FXML
	private void handleTotaalPrijs(){
		Double som = 0.0;
		lTotaalPrijs.setTextFill(Color.web("#1bc72f"));
		Boek b;
			for(int i=0; i< boeken.getSelectionModel().getSelectedItems().size(); i++) {
				b = boeken.getSelectionModel().getSelectedItems().get(i);
				if(b.getPrijs() != null && b.getPrijs() != "" && b.getPrijs() != "null") {
					som += Double.parseDouble(boeken.getSelectionModel().getSelectedItems().get(i).getPrijs());
				} else {
					som += 0.00;
				}
				
			}
			DecimalFormat df = new DecimalFormat("0.00");      
			lTotaalPrijs.setText("Totaalprijs: " + df.format(som) + " EURO");
			
	}
	
	
	@FXML
	private void handleDoorgaan() {
		ObservableList<Boek> selectedBooks = boeken.getSelectionModel().getSelectedItems();
		if(selectedBooks.size() > 0 & selectedOpleiding != null) {
			for(Boek b: selectedBooks) {
				OpleidingDAO.addBoekToOpleiding(b, selectedOpleiding);
			}
			lWarning.setTextFill(Color.web("#1bc72f"));
			if(boeken.getSelectionModel().getSelectedItems().size() > 1) {
				lWarning.setText(boeken.getSelectionModel().getSelectedItems().size() + " boeken werden toegevoegd aan -"+selectedOpleiding.getNaam()+"-");
				
			} else {
				lWarning.setText("\""+boeken.getSelectionModel().getSelectedItem().getIsbn() + "\" werd toegevoegd aan -"+selectedOpleiding.getNaam()+"-");
			}
			annuleren.setText("Afsluiten");
			
		} else {
			lWarning.setTextFill(Color.web("#ff0000"));
			lWarning.setText("Selecteer minstens één boek om door te gaan...");
		}
	}
	
	@FXML
	private void handleAnnuleren() {
		Stage stage = (Stage) annuleren.getScene().getWindow();
	    //stage.close();
	    
	    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void setSelectedOpleiding(Opleiding selectedOpleiding) {
		this.selectedOpleiding = selectedOpleiding;
		lUitleg.setText("Boeken toevoegen aan: \n"+selectedOpleiding.getNaam());
		setBoeken();
	}
	
	@FXML
	private void handleSelecteerAlles() {
		if (select.isSelected()) {
			boeken.getSelectionModel().selectAll();
			select.setText("Selecteer niets");
			}
		else {
			boeken.getSelectionModel().clearSelection();
			select.setText("Selecteer alles");
		}
		handleTotaalPrijs();
	}
	
	@FXML
	private void handleSearch() {
		if(lSearch.getText() != null) {
			setSearchResults();
		}
	}


	@SuppressWarnings("unchecked")
	@FXML
	private void setSearchResults() {
		boeken.setPlaceholder(new Label("Google Books heeft niks kunnen vinden."));
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		ObservableList<Boek> boekenSearch= FXCollections.observableArrayList();
		GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, lSearch.getText());
		try {
			boekenSearch = FXCollections.observableArrayList(GoogleBooks.executeQuery(jsonFactory, query));
			boeken.setItems(boekenSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void setBoeken() {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		//de GoogleBooks zoekresultaten
		ObservableList<Boek> boekenSearch = null;
		//De boeken die we effectief gaan tonen
		ObservableList<Boek> boekenTeTonen = FXCollections.observableArrayList();
		// De Boeken die al in de opleiding zitten mogen niet getoond worden
		ObservableList<Boek> boekenInOpleiding = FXCollections.observableArrayList(OpleidingDAO.getBoeken(selectedOpleiding));
		GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, selectedOpleiding.getNaam());
		//bool om bij te houden of er match gevonden ward
		boolean isMatch;
		try {
			boekenSearch = FXCollections.observableArrayList(GoogleBooks.executeQuery(jsonFactory, query));
			for(int i=0; i < boekenSearch.size(); i++) {
				//bool om bij te houden of er match gevonden ward
				isMatch = false;
				//We vergelijken elk boek in opleiding met elk van Google
				for(int j=0; i< boekenInOpleiding.size(); i++) {
					//is het een match ???
					if(boekenSearch.get(i).getIsbn() == boekenInOpleiding.get(j).getIsbn()) {
						isMatch = true; break;
					}
				}
				if(!isMatch) {
					boekenTeTonen.add(boekenSearch.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boeken.setItems(boekenTeTonen);
		boeken.setPlaceholder(new Label("Deze opleiding heeft momenteel geen boeken."));
	}
	
	
	@FXML
	public void initialize() {
		boeken.setPlaceholder(new Label("Kies hierboven eerst een opleiding..."));

		colTitel.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getTitel());
			}
		});
		colPrijs.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getPrijs());
			}
		});
		
		boeken.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	
}
