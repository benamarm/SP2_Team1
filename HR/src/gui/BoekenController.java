package gui;

import java.io.IOException;
import database.OpleidingDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Boek;
import logic.Opleiding;
import javafx.scene.control.Button;

public class BoekenController {
	
	@FXML
	ComboBox<Opleiding> opleidingen;
	@FXML
	TableView<Boek> boeken;
	@FXML
	TableColumn<Boek, String> colTitel;
	@FXML
	TableColumn<Boek, String> colAuteur;
	@FXML
	CheckBox cbSelecteerAlles;
	@FXML
	Button bVerwijderen;
	@FXML
	Button bToevoegen;
	@FXML
	Label lWarning;
	
	@FXML
	private void handleSelecteerAlles() {
		if (cbSelecteerAlles.isSelected())
			boeken.getSelectionModel().selectAll();
		else
			boeken.getSelectionModel().clearSelection();
	}
	
	
	@FXML
	private void handleToevoegen() throws IOException {
		if(opleidingen.getValue() != null) {
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddBoeken.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Boeken toevoegen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setBoeken();
				}
			});
			AddBoekenController c = (AddBoekenController) f.getController();
			c.setSelectedOpleiding(opleidingen.getValue());
			popup.show();
		} else {
			lWarning.setTextFill(Color.web("ff0000"));
			lWarning.setText("Geen opleiding geselecteerd.");
		}
			
	}
	
	@FXML
	private void setBoeken() {
		lWarning.setText("");
		boeken.setItems(OpleidingDAO.getBoeken(opleidingen.getValue()));
		boeken.setPlaceholder(new Label("Deze opleiding heeft momenteel geen boeken."));
	}

	@FXML
	public void initialize() {

		opleidingen.setPlaceholder(new Label("Er zijn geen opleidingen."));
		boeken.setPlaceholder(new Label("Kies hierboven eerst een opleiding."));

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

		colTitel.setCellFactory(column -> {
			return new TableCell<Boek, String>() {
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

		colAuteur.setCellFactory(column -> {
			return new TableCell<Boek, String>() {
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
		
		opleidingen.setItems(OpleidingDAO.getObservables());

		Callback<ListView<Opleiding>, ListCell<Opleiding>> call = lv -> new ListCell<Opleiding>() {

			@Override
			protected void updateItem(Opleiding o, boolean empty) {
				super.updateItem(o, empty);
				setText(o == null ? "" : (o.getOpleidingId() + "  " + o.getNaam()));
			}

		};
		opleidingen.setCellFactory(call);
		opleidingen.setButtonCell(call.call(null));
		
		boeken.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
}
