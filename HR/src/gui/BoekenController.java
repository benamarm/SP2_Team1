package gui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import APIFiles.GoogleBooks;
import APIFiles.GoogleBooksExecutableQuery;
import APIFiles.GoogleBooksQueryPrefix;
import antlr.collections.List;
import database.OpleidingDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import logic.Boek;
import logic.Opleiding;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BoekenController {
	/**
	 *  Ik ga de boekentab splitsen in 3 tabs: 1 voor het selecteren van een opleiding
	 *  1 voor overzicht van alle boeken in de gekozen opleiding waarbij je er kan verwijderen eventueel,
	 *  en tenslotte 1 voor een overzicht van boeken 
	 *  in de Google API die gerelateerd zijn aan de titel van de opleiding.
	 *  op die laatste tab kan men dan boeken kiezen die hij wil toevoegen aan de gekozen opleiding.
	 * 
	 * */
	private Opleiding geselecteerd = null;
	ObservableList<Boek> list = null;
	
	//@FXML
	//Pane pane = BoekenOpleidingSelectionController.getPane();
	// Tenslotte 1 voor een overzicht van beschikbare boeken voor die opleiding: De elementen declareren
	private boolean selecteerAlles = false;

	@FXML
	TableView<Boek> boekenlijst;
	@FXML
	TableColumn<Boek, String> colTitel;
	@FXML
	TableColumn<Boek, String> colAuteur;
	@FXML
	TableColumn<Boek, String> colBeschrijvingBoek;
	@FXML
	TableColumn<Boek, String> colPrijs;
	@FXML
	Button bSelecteerAlles;
	@FXML
	Label lText;
	@FXML
	Button bToevoegen;
	
	
	
	//------------------------------------------------------Handlers voor de 1e tab------------------------------------------------------
	
	//------------------------------------------------------Handlers voor de 2e tab----------------------------------------------
	@FXML	
	private void handleSelecteerAlles() {
		this.selecteerAlles = !this.selecteerAlles;
		if(this.selecteerAlles) 
			boekenlijst.getSelectionModel().selectAll();
		else
			boekenlijst.getSelectionModel().clearSelection();
	}
	
	@FXML
	private void handleToevoegen() throws Exception {;
		for(int i = 0; i < boekenlijst.getSelectionModel().getSelectedItems().size(); i++) {
			Boek b = boekenlijst.getSelectionModel().getSelectedItems().get(i);
			OpleidingDAO.addBoekToOpleiding(b, geselecteerd);
		}
	}

	@FXML
	public void initialize() {
		geselecteerd = Main.getOpleiding();
		
		lText.setText("Kies hieronder de boeken die u aan "
				+geselecteerd.getNaam()
				+" wil toevoegen. Druk daarna op toevoegen.");
		boekenlijst.setPlaceholder(new Label("Geen boeken gevonden voor deze opleiding."));

		colTitel.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getTitel());
			}
		});
		colBeschrijvingBoek.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
						return new SimpleStringProperty(data.getValue().getBeschrijving());
					}
				});
		
		colPrijs.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getPrijs());
			}
		});
		
		boekenlijst.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		GoogleBooksExecutableQuery query = new GoogleBooksExecutableQuery(GoogleBooksQueryPrefix.TITEL, geselecteerd.getNaam());
		
		try {
			list = FXCollections.observableArrayList(GoogleBooks.executeQuery(JacksonFactory.getDefaultInstance(), query));
			boekenlijst.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setEditMode(Opleiding o) {
		//edit = true;
		geselecteerd = new Opleiding(o);
	}
	
	
}
