package gui;

import org.hibernate.Session;
import org.hibernate.query.Query;
import database.LogDAO;
import database.UserDAO;
import email.Email;
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
				LogDAO.authenticate(true);
				Main.setRoot(FXMLLoader.load(getClass().getResource("MainApp.fxml")));
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
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setTitle("Wachtwoord vergeten");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
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

		if (!tForgotEmail.getText().contains("@")) {
			lForgotPrompt.setText("Gelieve een e-mailadres in te geven.");
		} else {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();

			User u = session.get(User.class, tForgotEmail.getText());

			if (u == null) {
				lForgotPrompt.setText("De gegeven adres heeft geen account.");
			} else {

				// Genereer nieuw wachtwoord
				String password = User.generatePassword();

				// Aanpassen in database
				Query q = session.createNativeQuery("UPDATE applogin SET password = :p WHERE loginemail = :l");
				q.setParameter("p", password).setParameter("l", u.getLoginemail());

				if (q.executeUpdate() == 1) {
					// Email sturen
					Email.sendPassword(u.getLoginemail(), password);
					bForgotOK.setDisable(true);
					lForgotPrompt.setStyle("-fx-text-fill: black");
					lForgotPrompt.setText("U ontvangt binnenkort een e-mail met een nieuw wachtwoord.");

				} else
					lForgotPrompt.setText("Er is een technische fout opgelopen.");

			}

			session.getTransaction().commit();
		}

	}

}
