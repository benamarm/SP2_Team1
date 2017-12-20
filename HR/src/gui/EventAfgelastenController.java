package gui;

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
			e.setAfgelast(true);
			if(EventDAO.update(e)) {
				bOK.setDisable(true);
				LogDAO.eventAfgelast(e, tReden.getText());
				if (EventDAO.initialize(e) > 0)
					Email.eventAfgelast(e, tReden.getText());
				lCheck.setStyle("-fx-text-fill: black");
				lCheck.setText("Event succesvol afgelast");
			}
			else {
				bOK.setDisable(true);
				lCheck.setText("Er is een technische fout opgelopen.");
			}			
		}
	}

}