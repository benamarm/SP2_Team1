package gui;

import java.io.File;
import database.OpleidingDAO;
import database.PersoneelDAO;
import database.VaardigheidDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import logic.Opleiding;
import logic.Personeel;
import logic.Vaardigheid;

public class PersoneelController {

	@FXML
	ComboBox<Personeel> personeel;
	@FXML
	ComboBox<Opleiding> opleidingen;
	@FXML
	Label lCheck;
	@FXML
	Button bDownload;
	@FXML
	Button bUpload;
	@FXML
	TableView<Vaardigheid> komendeEvents;
	@FXML
	TableColumn<Vaardigheid, String> komendeVan;
	@FXML
	TableColumn<Vaardigheid, String> komendeTot;
	@FXML
	TableColumn<Vaardigheid, String> komendeKeuring;
	@FXML
	TableView<Vaardigheid> voorbijeEvents;
	@FXML
	TableColumn<Vaardigheid, String> voorbijeVan;
	@FXML
	TableColumn<Vaardigheid, String> voorbijeTot;
	@FXML
	TableColumn<Vaardigheid, String> voorbijeCertificaat;

	@FXML
	private void handleDownload() {
		if (voorbijeEvents.getSelectionModel().getSelectedItems().size() == 0) {
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Geen voorbije event geselecteerd.");
		} else if (voorbijeEvents.getSelectionModel().getSelectedItem().getCertificaat() == null) {
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Geen certificaat behaald.");
		}
		else {
			DirectoryChooser dc = new DirectoryChooser();
			File path = dc.showDialog(Main.window);
			if (path != null) {
				if(VaardigheidDAO.downloadCertificaat(voorbijeEvents.getSelectionModel().getSelectedItem(), path)) {
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Certificaat succesvol gedownload!");
				}
				else {
					lCheck.setStyle("-fx-text-fill: red");
					lCheck.setText("Er is een technische fout opgelopen.");
				}
			}
		}
	}

	@FXML
	private void handleUpload() {
		if (voorbijeEvents.getSelectionModel().getSelectedItems().size() == 0) {
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Geen voorbije event geselecteerd.");
		} else if (voorbijeEvents.getSelectionModel().getSelectedItem().getCertificaat() != null) {
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Certificaat reeds behaald.");
		}
		else {
			FileChooser fc = new FileChooser();
			File path = fc.showOpenDialog(Main.window);
			if (path != null) {
				if(VaardigheidDAO.uploadCertificaat(voorbijeEvents.getSelectionModel().getSelectedItem(), path)) {
					setVoorbije();
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Certificaat succesvol geüpload!");
				}
				else {
					lCheck.setStyle("-fx-text-fill: red");
					lCheck.setText("Er is een technische fout opgelopen.");
				}
			}
		}
	}

	@FXML
	private void clearLabel() {
		lCheck.setText("");
	}

	@FXML
	private void setOpleidingen() {
		clearLabel();
		komendeEvents.setPlaceholder(new Label("Selecteer eerst een personeelslid, dan een opleiding."));
		voorbijeEvents.setPlaceholder(new Label("Selecteer eerst een personeelslid, dan een opleiding."));
		komendeEvents.getItems().clear();
		voorbijeEvents.getItems().clear();

		opleidingen.valueProperty().set(null);
		ObservableList<Opleiding> list = FXCollections.observableArrayList(OpleidingDAO.getAll());
		opleidingen.setItems(list);

		Callback<ListView<Opleiding>, ListCell<Opleiding>> call = lv -> new ListCell<Opleiding>() {
			@Override
			protected void updateItem(Opleiding o, boolean empty) {
				super.updateItem(o, empty);
				setText(o == null ? "" : (o.getOpleidingId() + " - " + o.getNaam()));
			}
		};
		opleidingen.setCellFactory(call);
		opleidingen.setButtonCell(call.call(null));
	}

	@FXML
	private void setEvents() {
		clearLabel();
		if (opleidingen.getValue() != null) {
			ObservableList<Vaardigheid> list = FXCollections
					.observableArrayList(VaardigheidDAO.getKomende(personeel.getValue(), opleidingen.getValue()));
			komendeEvents.setItems(list);
			if (list.size() == 0)
				komendeEvents.setPlaceholder(new Label("Geen inschrijving voor komende events binnen deze opleiding."));
			setVoorbije();
		}
	}

	@FXML
	private void setVoorbije() {
		clearLabel();
		ObservableList<Vaardigheid> list = FXCollections
				.observableArrayList(VaardigheidDAO.getVoorbije(personeel.getValue(), opleidingen.getValue()));
		voorbijeEvents.setItems(list);
		if (list.size() == 0)
			voorbijeEvents.setPlaceholder(new Label("Geen voltooide events binnen deze opleiding."));
	}

	@FXML
	public void initialize() {
		komendeEvents.setPlaceholder(new Label("Selecteer eerst een personeelslid, dan een opleiding."));
		voorbijeEvents.setPlaceholder(new Label("Selecteer eerst een personeelslid, dan een opleiding."));

		// TableView komende events
		komendeVan.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getEvent().getStringStartdatum());
			}
		});
		komendeTot.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getEvent().getStringEinddatum());
			}
		});
		komendeKeuring
				.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
						String s;
						if (data.getValue().isChecked() == null)
							s = "In afwachting";
						else if (data.getValue().isChecked() == true)
							s = "Goedgekeurd";
						else
							s = "Afgekeurd";
						return new SimpleStringProperty(s);
					}
				});

		// TableView voorbije events
		voorbijeVan.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getEvent().getStringStartdatum());
			}
		});
		voorbijeTot.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getEvent().getStringEinddatum());
			}
		});
		voorbijeCertificaat
				.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
						return new SimpleStringProperty(data.getValue().getCertificaat() == null ? "Neen" : "Ja");
					}
				});

		// ComboBox personeel
		ObservableList<Personeel> list = FXCollections.observableArrayList(PersoneelDAO.getAllAccount());
		personeel.setItems(list);
		Callback<ListView<Personeel>, ListCell<Personeel>> call = lv -> new ListCell<Personeel>() {
			@Override
			protected void updateItem(Personeel p, boolean empty) {
				super.updateItem(p, empty);
				setText(p == null ? "" : (p.getPersId() + " - " + p.getVolleNaam()));
			}
		};
		personeel.setCellFactory(call);
		personeel.setButtonCell(call.call(null));

	}

}
