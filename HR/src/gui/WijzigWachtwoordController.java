package gui;

import database.LogDAO;
import database.UserDAO;
import exceptions.UserOnbestaandException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class WijzigWachtwoordController {
	@FXML
	PasswordField password1;
	@FXML
	PasswordField password2;
	@FXML
	Button bOK;
	@FXML
	Label lCheck;

	@FXML
	private void handleOK() {
		if (password1.getText().equals("") || password2.getText().equals(""))
			lCheck.setText("Gelieve alle velden in te vullen.");
		else if (!password1.getText().equals(password2.getText()))
			lCheck.setText("Wachtwoord verkeerd herhaald.");
		else {

			try {
				if (UserDAO.updatePassword(Main.sessionUser.getLoginemail(), password1.getText())) {
					bOK.setDisable(true);
					LogDAO.eigenWachtwoord();
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Wachtwoord succesvol gewijzigd.");
				} else {
					bOK.setDisable(true);
					lCheck.setText("Er is een technische fout opgelopen.");
				}

			} catch (UserOnbestaandException e) {}
		}
	}

}