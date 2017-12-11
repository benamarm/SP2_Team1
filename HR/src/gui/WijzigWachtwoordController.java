package gui;

import org.hibernate.Session;
import org.hibernate.query.Query;
import database.LogDAO;
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

			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();

			try {
				Query q = session.createNativeQuery("UPDATE applogin SET password = :p WHERE loginemail = '"
						+ Main.sessionUser.getLoginemail() + "'");
				q.setParameter("p", password1.getText());
				if (q.executeUpdate() == 1) {
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Wachtwoord succesvol gewijzigd.");
					bOK.setDisable(true);
				}

				session.getTransaction().commit();

				LogDAO.eigenWachtwoord();

			} catch (Exception e) {
				bOK.setDisable(true);
				lCheck.setText("Er is een technische fout opgelopen.");
			}
		}
	}

}