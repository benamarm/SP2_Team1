package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("HR Applicatie");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();	
	}
	
	public static void setRoot(Parent root) {
		stage.getScene().setRoot(root);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}