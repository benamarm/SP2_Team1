package gui;

import java.io.IOException;
import database.LogDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainAppController {

	@FXML
	private Text tName;
	@FXML
	private Text tLogout;
	@FXML
	private Label lWijzigWachtwoord;
	@FXML
	private Button bUsers;
	@FXML
	private Button bAanvragen;
	@FXML
	private Button bBoeken;
	@FXML
	private Button bOpleidingen;
	@FXML
	private Button bEvents;
	@FXML
	private Button bSurveys;
	@FXML
	private Button bPersoneel;
	@FXML
	private Button bStatistieken;
	@FXML
	private Pane mainPane;

	@FXML
	private void logOut() {
		try {
			LogDAO.authenticate(false);
			Main.sessionUser = null;
			Main.setRoot(FXMLLoader.load(getClass().getResource("Login.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void wijzigWachtwoord() {

	}

	@FXML
	private void handleButtonUsers() {
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("Users.fxml")));
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
	private void handleButtonOpleidingen() {
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("Opleidingen.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleButtonBoeken() {
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("BoekenOpleidingSelection.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleButtonEvents() {
		try {
			mainPane.getChildren().clear();
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("Events.fxml")));
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
