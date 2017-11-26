package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	public void initialize() {
		aanvragen.setPlaceholder(new Label("Er zijn momenteel geen aanvragen."));
	}
}
