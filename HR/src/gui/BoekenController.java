package gui;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Boek;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;
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
	private void handleSelecteerAlles() {
		if (cbSelecteerAlles.isSelected())
			boeken.getSelectionModel().selectAll();
		else
			boeken.getSelectionModel().clearSelection();
	}
	
	
//	@FXML
//	private void handleToevoegen() throws IOException {
//		if (opleidingen.getValue() == null)
//			lSelectie.setText("Gelieve eerst een opleiding te selecteren.");
//		else {
//			lSelectie.setText("");
//			Stage popup = new Stage();
//			FXMLLoader f = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
//			Parent root = (Parent) f.load();
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			popup.setTitle("Event toevoegen");
//			popup.initModality(Modality.APPLICATION_MODAL);
//			popup.setResizable(false);
//			popup.centerOnScreen();
//			popup.setScene(scene);
//			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
//				public void handle(WindowEvent we) {
//					setBoeken();
//				}
//			});
//			AddEventController c = (AddEventController) f.getController();
//			c.initiate(opleidingen.getValue(), false, null);
//			popup.show();
//		}
//	}
	
	

	@FXML
	public void initialize() {

		opleidingen.setPlaceholder(new Label("Er zijn geen opleidingen."));

		colTitel.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getTitel());
			}
		});
		colAuteur.setCellValueFactory(new Callback<CellDataFeatures<Boek, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Boek, String> data) {
				return new SimpleStringProperty(data.getValue().getAuteurs().get(0)
						//Maar er kunnen meerdere auteurs zijn, we zetten ze allemaal in 1 tabbelcel
			+ data.getValue().getAuteurs().get(1) == null?"":(", "+data.getValue().getAuteurs().get(1))
			+ data.getValue().getAuteurs().get(2) == null?"":(", "+data.getValue().getAuteurs().get(2))
			+ data.getValue().getAuteurs().get(3) == null?"":" etc.");
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

	}
	
}
