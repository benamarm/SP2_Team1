package gui;

import database.LogDAO;
import database.VraagDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Vraag;

public class DelVraagController {
	
	private Vraag toDelete;
	private ObservableList<Vraag> vragen;
	
	public void initiate(Vraag toDelete, ObservableList<Vraag> vragen) {
		this.toDelete = toDelete;
		this.vragen = vragen;
	}

	@FXML
	Button bOK;
	@FXML
	Label lCheck;
	
	@FXML
	private void handleOK() {

		if(VraagDAO.delete(toDelete, vragen)) {
			bOK.setDisable(true);
			LogDAO.vraagVerwijderd(toDelete);
			lCheck.setStyle("-fx-text-fill: black");
			lCheck.setText("Vraag succesvol verwijderd!");
		}
		else {
			bOK.setDisable(true);
			lCheck.setText("Er is een technische fout opgelopen.");
		}
	}
}
