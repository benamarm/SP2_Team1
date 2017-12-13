package gui;

import database.LogDAO;
import database.OpleidingDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Opleiding;

public class AddOpleidingController {

	private Boolean edit = false;
	private Opleiding teBewerken = null;

	@FXML
	TextField tOplNaam;
	@FXML
	TextArea tOplBesch;
	@FXML
	Button bToevoegen;
	@FXML
	Label lOpl;

	@FXML
	private void handleToevoegen() {
		if (tOplNaam.getText().equals("") || tOplBesch.getText().equals("")) {
			lOpl.setText("Gelieve alle velden in te vullen.");
		} else if (edit) {

			if (tOplNaam.getText().equals(teBewerken.getNaam())
					&& tOplBesch.getText().equals(teBewerken.getBeschrijving())) {
				lOpl.setText("U heeft niets aangepast.");
			} else {
				teBewerken.setNaam(tOplNaam.getText());
				teBewerken.setBeschrijving(tOplBesch.getText());

				if(OpleidingDAO.update(teBewerken)) {
					bToevoegen.setDisable(true);
					LogDAO.opleidingBewerkt(teBewerken);
					lOpl.setStyle("-fx-text-fill: black");
					lOpl.setText("Opleiding succesvol bewerkt!");
				}
				else {
					bToevoegen.setDisable(true);
					lOpl.setText("Er is een technische fout opgelopen.");	
				}						

			}

		} else {

			Opleiding o = new Opleiding();
			o.setNaam(tOplNaam.getText());
			o.setBeschrijving(tOplBesch.getText());

			if(OpleidingDAO.save(o)) {
				bToevoegen.setDisable(true);
				LogDAO.opleidingToegevoegd(o);
				lOpl.setStyle("-fx-text-fill: black");
				lOpl.setText("Opleiding succesvol toegevoegd!");
			}
			else
				lOpl.setText("Er is een technische fout opgelopen.");			

		}
	}

	public void setEditMode(Opleiding o) {
		edit = true;
		teBewerken = new Opleiding(o);
		tOplNaam.setText(teBewerken.getNaam());
		tOplBesch.setText(teBewerken.getBeschrijving());
		bToevoegen.setText("Bewerken");
	}

}
