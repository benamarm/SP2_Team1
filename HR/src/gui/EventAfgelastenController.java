package gui;

import org.hibernate.Session;
import database.EventDAO;
import database.LogDAO;
import email.Email;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.Event;

public class EventAfgelastenController {

	public Event e;

	@FXML
	TextArea tReden;
	@FXML
	Button bOK;
	@FXML
	Label lCheck;

	@FXML
	private void handleOK() {
		if (tReden.getText().equals(""))
			lCheck.setText("Gelieve een reden te geven.");
		else {

			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();

			try {
				e.setAfgelast(true);
				session.update(e);
				session.getTransaction().commit();

			} catch (Exception e) {
				bOK.setDisable(true);
				lCheck.setText("Er is een technische fout opgelopen.");
				return;
			}

			bOK.setDisable(true);
			lCheck.setStyle("-fx-text-fill: black");
			lCheck.setText("Event succesvol afgelast");
			LogDAO.eventAfgelast(e, tReden.getText());
			if (EventDAO.initialize(e) > 0)
				Email.eventAfgelast(e, tReden.getText());
		}

	}

}