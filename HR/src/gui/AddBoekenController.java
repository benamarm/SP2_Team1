package gui;

import java.io.IOException;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import database.OpleidingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Adres;
import logic.Boek;
import logic.Event;
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
	
	public Opleiding selectedOpleiding;
	
	
	public Opleiding getSelectedOpleiding() {
		return selectedOpleiding;
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
			
		} else {
			lWarning.setTextFill(Color.web("#ff0000"));
			lWarning.setText("Selecteer minstens één boek om door te gaan...");
		}
	}
	
	@FXML
	private void handleAnnuleren() {
		Stage stage = (Stage) annuleren.getScene().getWindow();
	    stage.close();
	    setBoeken();
	}

	public void setSelectedOpleiding(Opleiding selectedOpleiding) {
		this.selectedOpleiding = selectedOpleiding;
		lUitleg.setText("Boeken toevoegen aan: \n"+selectedOpleiding.getNaam());
		setBoeken();
	}
	
	@FXML
	private void handleSelecteerAlles() {
		if (select.isSelected())
			boeken.getSelectionModel().selectAll();
		else
			boeken.getSelectionModel().clearSelection();
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
			boekenSearch = (ObservableList<Boek>) GoogleBooks.executeQuery(jsonFactory, query);
			boeken.setItems(boekenSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void setBoeken() {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		ObservableList<Boek> boekenSearch = null;
		GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, selectedOpleiding.getNaam());
		try {
			boekenSearch = FXCollections.observableArrayList(GoogleBooks.executeQuery(jsonFactory, query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boeken.setItems(boekenSearch);
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
