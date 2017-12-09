package gui;

import org.hibernate.Session;
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
		if(tReden.getText().equals("")) 
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
			}

			bOK.setDisable(true);
			lCheck.setStyle("-fx-text-fill: black");
			lCheck.setText("Event succesvol afgelast");
			//log met reden
			//Email sturen met reden
		}	

	}

}