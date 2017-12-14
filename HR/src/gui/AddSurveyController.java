package gui;

import org.hibernate.Session;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Opleiding;
import logic.Survey;
import logic.Vraag;

public class AddSurveyController {

	private Opleiding o;
	
	public void setOpleiding(Opleiding o) {
		this.o = o;
		lOpleiding.setText(o.getNaam());
	}

	@FXML
	Label lOpleiding;
	@FXML
	TextField tTitel;
	@FXML
	TextArea tVraag1;
	@FXML
	TextArea tVraag2;
	@FXML
	TextArea tVraag3;
	@FXML
	TextArea tVraag4;
	@FXML
	TextArea tVraag5;
	@FXML
	Label lCheck;
	@FXML
	Button bToevoegen;

	@FXML
	private void handleToevoegen() {
		if (tTitel.getText().equals("") || tVraag1.getText().equals("") || tVraag2.getText().equals("")
				|| tVraag3.getText().equals("") || tVraag4.getText().equals("") || tVraag5.getText().equals(""))
			lCheck.setText("Gelieve alle velden in te vullen.");
		else {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Survey s = new Survey();
			s.setOpleiding(o);
			s.setTitel(tTitel.getText());
			
			Vraag v1 = new Vraag();
			v1.setVraag(tVraag1.getText());
			v1.setInx(1);
			Vraag v2 = new Vraag();
			v2.setVraag(tVraag2.getText());
			v2.setInx(2);
			Vraag v3 = new Vraag();
			v3.setVraag(tVraag3.getText());
			v3.setInx(3);
			Vraag v4 = new Vraag();
			v4.setVraag(tVraag4.getText());
			v4.setInx(4);
			Vraag v5 = new Vraag();
			v5.setVraag(tVraag5.getText());
			v5.setInx(5);
			
			s.getVragen().add(v1);
			s.getVragen().add(v2);
			s.getVragen().add(v3);
			s.getVragen().add(v4);
			s.getVragen().add(v5);
			v1.setSurvey(s);
			v2.setSurvey(s);
			v3.setSurvey(s);
			v4.setSurvey(s);
			v5.setSurvey(s);
			
			try {
				session.save(s);
				session.getTransaction().commit();
				
				bToevoegen.setDisable(true);
				//log
				lCheck.setStyle("-fx-text-fill: black");
				lCheck.setText("Survey succesvol toegevoegd!");
			}						
			catch(Exception e) {
				bToevoegen.setDisable(true);
				e.printStackTrace();
				lCheck.setText("Er is een technische fout opgelopen.");
			}
		}
	}
}
