package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainAppController {

	@FXML
	private Text tName;
	@FXML
	private Text tLogout;
	@FXML
	private Button bUsers;
	@FXML
	private Button bAanvragen;
	@FXML
	private Button bBoeken;
	@FXML
	private Button bOpleidingen;
	@FXML
	private Button bPersoneel;
	@FXML
	private Button bEvents;
	@FXML
	private Button bSurveys;
	@FXML
	private Pane mainPane;
	

	@FXML
	private void logOut() {
		try {
			Main.setRoot(FXMLLoader.load(getClass().getResource("Login.fxml")));
			Main.sessionUser = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleButtonAanvragen() {
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("Aanvragen.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void initialize() {
		tName.setText(Main.sessionUser.getVolleNaam());
		if (Main.sessionUser.getPositie().equals("ADMIN"))
			bUsers.setVisible(true);
		handleButtonAanvragen();
	}

}
