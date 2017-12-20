package gui;

import java.sql.Date;
import java.time.LocalDate;
import database.LogDAO;
import database.SurveyDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import logic.Publicatie;
import logic.Survey;

public class PubliceerSurveyController {

	Survey s;

	public void setSurvey(Survey s) {
		this.s = s;
		lSurvey.setText(s.getTitel());
	}

	@FXML
	Label lSurvey;
	@FXML
	DatePicker dTot;
	@FXML
	Button bOK;
	@FXML
	Label lCheck;

	@FXML
	private void handleOK() {
		if (dTot.getValue() == null || !dTot.getValue().isAfter(LocalDate.now()))
			lCheck.setText("Ongepaste datum.");
		else {
			Publicatie p = new Publicatie();
			p.setSurvey(s);
			p.setTot(Date.valueOf(dTot.getValue()));
			p.setActief(true);

			if (SurveyDAO.save(p)) {
				bOK.setDisable(true);
				LogDAO.surveyGepubliceerd(p);
				lCheck.setStyle("-fx-text-fill: black");
				lCheck.setText("Survey succesvol gepubliceerd!");
			} else {
				bOK.setDisable(true);
				lCheck.setText("Er is een technische fout opgelopen.");
			}
		}
	}
	
}
