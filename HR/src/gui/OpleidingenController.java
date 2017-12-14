package gui;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Opleiding;

public class OpleidingenController {

	@FXML
	TableView<Opleiding> opleidingen;
	@FXML
	TableColumn<Opleiding, Integer> colId;
	@FXML
	TableColumn<Opleiding, String> colNaam;
	@FXML
	TableColumn<Opleiding, String> colBeschrijving;
	@FXML
	Label lSelectie;
	@FXML
	Button bToevoegen;
	@FXML
	Button bBewerken;
	@FXML
	Button bBoeken;

	@FXML
	private void clearLabel() {
		lSelectie.setText("");
	}

	@FXML
	private void handleToevoegen() throws Exception {
		lSelectie.setText("");
		Stage popup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("AddOpleiding.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setTitle("Opleiding toevoegen");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initialize();
			}
		});
		popup.show();
	}

	@FXML
	private void handleBewerken() throws Exception {

		if (opleidingen.getSelectionModel().getSelectedItems().size() == 0) {

			lSelectie.setText("Geen opleiding geselecteerd.");

		} else {
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddOpleiding.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Opleiding bewerken");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					initialize();
				}
			});
			AddOpleidingController c = (AddOpleidingController) f.getController();
			c.setEditMode(opleidingen.getSelectionModel().getSelectedItem());
			popup.show();
		}

	}

	@FXML
	public void initialize() {

		opleidingen.setPlaceholder(new Label("Er zijn geen opleidingen."));

		colId.setCellValueFactory(new Callback<CellDataFeatures<Opleiding, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Opleiding, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getOpleidingId()).asObject();
			}
		});
		colNaam.setCellValueFactory(new Callback<CellDataFeatures<Opleiding, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Opleiding, String> data) {
				return new SimpleStringProperty(data.getValue().getNaam());
			}
		});
		colBeschrijving
				.setCellValueFactory(new Callback<CellDataFeatures<Opleiding, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Opleiding, String> data) {
						return new SimpleStringProperty(data.getValue().getBeschrijving());
					}
				});

		colNaam.setCellFactory(column -> {
			return new TableCell<Opleiding, String>() {
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

		colBeschrijving.setCellFactory(column -> {
			return new TableCell<Opleiding, String>() {
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

		ObservableList<Opleiding> list = FXCollections.observableArrayList(OpleidingDAO.getAll());
		opleidingen.setItems(list);

	}

}
