package gui;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import logic.Adres;

public class EventLocatieController {
	
	@FXML
	WebView wv;
	
	public void setAdres(Adres a) {
		wv.getEngine().load("https://maps.google.com/?q=" + a.toString());		
	}
	
}