package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class MainAppController {

	@FXML
	private Text tUsername;
	@FXML
	private Text tLogout;
	
	@FXML
	private void logOut() throws Exception {
		Main.setRoot(FXMLLoader.load(getClass().getResource("Login.fxml")));
	}
	
	public void setUsername(String s) {
		tUsername.setText(s);
	}
	
}
