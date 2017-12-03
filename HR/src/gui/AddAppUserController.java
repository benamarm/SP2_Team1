package gui;

import database.UserDAO;
import exceptions.UserBestaatReedsException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.User;

public class AddAppUserController {

	@FXML
	TextField tEmail;
	@FXML
	TextField tVoornaam;
	@FXML
	TextField tAchternaam;
	@FXML
	CheckBox cbAdmin;
	@FXML
	Button bToevoegen;
	@FXML
	Label lToevoegen;

	@FXML
	private void handleToevoegen() {
		if (tEmail.getText().equals("") || tVoornaam.getText().equals("") || tAchternaam.getText().equals("")) {
			lToevoegen.setText("Gelieve alle velden in te vullen.");
		} else {
			User u = new User();
			u.setLoginemail(tEmail.getText());
			u.setNaam(tVoornaam.getText());
			u.setAchternaam(tAchternaam.getText());
			u.setPositie(cbAdmin.isSelected() ? "ADMIN" : "HR");
			try {
				if (UserDAO.save(u)) {
					bToevoegen.setDisable(true);
					lToevoegen.setStyle("-fx-text-fill: black");
					lToevoegen.setText("User succesvol toegevoegd!");
				} else
					lToevoegen.setText("Er is een technische fout opgelopen.");
			} catch (UserBestaatReedsException e) {
				lToevoegen.setText("E-mailadres reeds in gebruik.");
			}

		}
	}
}
