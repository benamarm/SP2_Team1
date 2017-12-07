package gui;

import org.hibernate.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Event;

public class EventAfgelastenController {

	public Event e;

	@FXML
	Button bJa;
	@FXML
	Label lCheck;

	@FXML
	private void handleJa() {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		try {
			e.setAfgelast(true);
			session.update(e);
			session.getTransaction().commit();

		} catch (Exception e) {
			bJa.setDisable(true);
			lCheck.setStyle("-fx-text-fill: red");
			lCheck.setText("Er is een technische fout opgelopen.");
		}

		bJa.setDisable(true);
		lCheck.setText("Event succesvol afgelast");
		
		//Email sturen

	}

}