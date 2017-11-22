package gui;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.User;

public class LoginController {

	@FXML
	private Label lForgotPassword;
	@FXML
	private Label lPrompt;
	@FXML
	private Button bLogin;
	@FXML
	private TextField tEmail;
	@FXML
	private PasswordField pPassword;

	@FXML
	private void verifyCredentials() throws Exception {
		if (tEmail.getText().equals("") && pPassword.getText().equals(""))
			lPrompt.setText("Gelieve uw e-mailadres en wachtwoord in te vullen.");
		else if (tEmail.getText().equals(""))
			lPrompt.setText("Gelieve uw e-mailadres in te vullen.");
		else if (pPassword.getText().equals(""))
			lPrompt.setText("Gelieve uw wachtwoord in te vullen.");
		else {
			User u = UserDAO.connect(tEmail.getText(), pPassword.getText());
			if (u != null) {
				Main.sessionUser = u;
				FXMLLoader f = new FXMLLoader(getClass().getResource("MainApp.fxml"));
				Main.setRoot(f.load());
				MainAppController c = f.<MainAppController>getController();
				c.setName(u.getNaam() + " " + u.getAchternaam());
			} else {
				lPrompt.setText("De ingevoerde gegevens zijn incorrect.");
			}
		}
	}

	@FXML
	private void handleButtonForgotPassword() throws Exception {
		Stage popup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
		Scene scene = new Scene(root);
		popup.setTitle("Wachtwoord vergeten");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.setScene(scene);
		popup.show();
	}

	@FXML
	private TextField tForgotEmail;
	@FXML
	private Button bForgotOK;
	@FXML
	private Label lForgotPrompt;

	@FXML
	private void verifyForgotEmail() {
		if (tForgotEmail.getText().contains("@")) {
			lForgotPrompt.setStyle("-fx-text-fill: black");
			lForgotPrompt.setText("U zal een bericht ontvangen met een nieuw wachtwoord.");
		} else {
			lForgotPrompt.setStyle("-fx-text-fill: red");
			lForgotPrompt.setText("Gelieve een e-mailadres in te vullen.");
		}
	}

}
