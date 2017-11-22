package gui;

import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.*;

public class Main extends Application {

	public static Stage window;
	public static SessionFactory factory;
	public static User sessionUser;

	@Override
	public void start(Stage primaryStage) throws IOException {
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		window.setTitle("HR Applicatie");
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

	public static void setRoot(Parent root) {
		window.getScene().setRoot(root);
	}

	public static void main(String[] args) {
		factory = new Configuration().configure().addAnnotatedClass(Adres.class).addAnnotatedClass(Boek.class)
				.addAnnotatedClass(Event.class).addAnnotatedClass(Log.class).addAnnotatedClass(Opleiding.class)
				.addAnnotatedClass(Personeel.class).addAnnotatedClass(User.class).addAnnotatedClass(Vaardigheid.class)
				.addAnnotatedClass(Vraag.class).addAnnotatedClass(WebUser.class).buildSessionFactory();
		launch(args);
		factory.close();
	}
	
}
