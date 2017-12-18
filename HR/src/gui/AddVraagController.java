package gui;

import database.LogDAO;
import database.VraagDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.Survey;
import logic.Vraag;

public class AddVraagController {

	private Survey s;
	private Boolean edit;
	private Vraag oud;
	private Integer nextInx;

	public void initiate(Survey s, boolean edit, Integer nextInx, Vraag v) {
		this.s = s;
		lSurvey.setText(s.getOpleiding().getNaam() + "\n" + s.getTitel());
		this.edit = edit;
		this.nextInx = nextInx;
		if (edit) {
			oud = v;
			lVraag.setText("Vraag " + v.getInx() + ":");
			tVraag.setText(v.getVraag());
		}
	}

	@FXML
	Label lSurvey;
	@FXML
	Label lVraag;
	@FXML
	TextArea tVraag;
	@FXML
	Label lCheck;
	@FXML
	Button bOK;

	@FXML
	private void handleOK() {
		if (tVraag.getText().equals(""))
			lCheck.setText("Vraag is leeg.");
		else if (edit) {

			if (oud.getVraag().equals(tVraag.getText()))
				lCheck.setText("U heeft niets aangepast.");
			else {

				Vraag nieuw = new Vraag();
				nieuw.setVraag(tVraag.getText());
				nieuw.setSurvey(s);
				nieuw.setInx(oud.getInx());
				oud.setInx(0);

				if (VraagDAO.update(nieuw, oud)) {
					bOK.setDisable(true);
					LogDAO.vraagBewerkt(nieuw, oud);
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Vraag succesvol bewerkt!");
				} else {
					bOK.setDisable(true);
					lCheck.setText("Er is een technische fout opgelopen.");
				}
			}
		} else {
			Vraag nieuw = new Vraag();
			nieuw.setVraag(tVraag.getText());
			nieuw.setSurvey(s);
			nieuw.setInx(nextInx);

			if (VraagDAO.save(nieuw)) {
				bOK.setDisable(true);
				LogDAO.vraagToegevoegd(nieuw);
				lCheck.setStyle("-fx-text-fill: black");
				lCheck.setText("Vraag succesvol toegevoegd!");
			} else {
				bOK.setDisable(true);
				lCheck.setText("Er is een technische fout opgelopen.");
			}

		}
	}
}
