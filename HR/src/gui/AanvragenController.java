package gui;

import java.text.SimpleDateFormat;
import database.VaardigheidDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import logic.Vaardigheid;

public class AanvragenController {

	@FXML
	TableView<Vaardigheid> aanvragen;
	@FXML
	TableColumn<Vaardigheid, String> colId;
	@FXML
	TableColumn<Vaardigheid, String> colNaam;
	@FXML
	TableColumn<Vaardigheid, String> colOpleiding;
	@FXML
	TableColumn<Vaardigheid, String> colVan;
	@FXML
	TableColumn<Vaardigheid, String> colTot;
	@FXML
	CheckBox cbSelecteerAlles;
	@FXML
	Button bCheck;
	@FXML
	Label lCheck;
	
	@FXML
	private void handleSelecteerAlles() {
		if(cbSelecteerAlles.isSelected()) 
			aanvragen.getSelectionModel().selectAll();
		else
			aanvragen.getSelectionModel().clearSelection();
	}
	
	@FXML
	private void handleCheck() {
		if(aanvragen.getSelectionModel().getSelectedItems().size() == 0) {
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Geen aanvragen geselecteerd.");
		}
		else {
			
			for(Vaardigheid v : aanvragen.getSelectionModel().getSelectedItems()) {
				v.setChecked(true);
			}
			
			if(VaardigheidDAO.updateObservables(aanvragen.getSelectionModel().getSelectedItems())) {
				initialize();
				lCheck.setStyle("-fx-text-fill: black");
				lCheck.setText("De aanvragen werden bevestigd.");
			}
			else {
				lCheck.setStyle("-fx-text-fill: red");
				lCheck.setText("Er is een technische fout opgelopen.");
			}
			
		}
			
	}

	@FXML
	public void initialize() {

		aanvragen.setPlaceholder(new Label("Er zijn momenteel geen aanvragen."));

		colId.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getPersoneel().getPersId());
			}
		});
		colNaam.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				return new SimpleStringProperty(data.getValue().getPersoneel().getVolleNaam());
			}
		});
		colOpleiding
				.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
						return new SimpleStringProperty(data.getValue().getEvent().getOpleiding().getNaam());
					}
				});
		colVan.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				return new SimpleStringProperty(sdf.format(data.getValue().getEvent().getStartdatum()));
			}
		});
		colTot.setCellValueFactory(new Callback<CellDataFeatures<Vaardigheid, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vaardigheid, String> data) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				return new SimpleStringProperty(sdf.format(data.getValue().getEvent().getEinddatum()));
			}
		});
		
		aanvragen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ObservableList<Vaardigheid> list = FXCollections.observableArrayList(VaardigheidDAO.getUnchecked());
		aanvragen.setItems(list);
		
	}
}
