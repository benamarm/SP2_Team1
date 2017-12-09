package gui;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import email.Email;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.User;

public class UsersController {

	// Applicatie
	@FXML
	Tab applicatie;
	@FXML
	TableView<User> aUsers;
	@FXML
	TableColumn<User, String> acEmail;
	@FXML
	TableColumn<User, String> acNaam;
	@FXML
	TableColumn<User, String> acAdmin;
	@FXML
	Button bAddUser;
	@FXML
	Button bRemAdmin;
	@FXML
	Button bSetAdmin;
	@FXML
	Button abPassword;
	@FXML
	Label alSelectie;

	@FXML
	private void handleAppUser() throws IOException {
		alSelectie.setText("");
		Stage popup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("AddAppUser.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		popup.setTitle("User toevoegen");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initAppUsers();
			}
		});
		popup.show();
	}

	@FXML
	private void handleRemAdmin() {
		if (aUsers.getSelectionModel().getSelectedItems().size() == 0) {
			alSelectie.setStyle("-fx-text-fill: red");
			alSelectie.setText("Geen user geselecteerd.");
		} else {
			if (!aUsers.getSelectionModel().getSelectedItem().getPositie().equals("ADMIN")) {
				alSelectie.setStyle("-fx-text-fill: red");
				alSelectie.setText("De user is geen admin.");
			} else {
				User u = aUsers.getSelectionModel().getSelectedItem();
				u.setPositie("HR");

				Session session = Main.factory.getCurrentSession();
				session.beginTransaction();
				try {
					session.update(u);
					session.getTransaction().commit();
				} catch (Exception e) {
					alSelectie.setStyle("-fx-text-fill: red");
					alSelectie.setText("Er is een technische fout opgelopen.");
				}

				initAppUsers();
				alSelectie.setStyle("-fx-text-fill: black");
				alSelectie.setText("Adminrechten succesvol afgeschaft!");

			}
		}
	}

	@FXML
	private void handleSetAdmin() {
		if (aUsers.getSelectionModel().getSelectedItems().size() == 0) {
			alSelectie.setStyle("-fx-text-fill: red");
			alSelectie.setText("Geen user geselecteerd.");
		} else {
			if (aUsers.getSelectionModel().getSelectedItem().getPositie().equals("ADMIN")) {
				alSelectie.setStyle("-fx-text-fill: red");
				alSelectie.setText("De user is reeds admin.");
			} else {
				User u = aUsers.getSelectionModel().getSelectedItem();
				u.setPositie("ADMIN");

				Session session = Main.factory.getCurrentSession();
				session.beginTransaction();
				try {
					session.update(u);
					session.getTransaction().commit();
					
					initAppUsers();
					alSelectie.setStyle("-fx-text-fill: black");
					alSelectie.setText("User succesvol gepromoveerd!");
					
				} catch (Exception e) {
					alSelectie.setStyle("-fx-text-fill: red");
					alSelectie.setText("Er is een technische fout opgelopen.");
				}			

			}
		}
	}

	@FXML
	private void handleAppPassword() {
		if (aUsers.getSelectionModel().getSelectedItems().size() == 0) {
			alSelectie.setStyle("-fx-text-fill: red");
			alSelectie.setText("Geen user geselecteerd.");
		} else {

			User u = aUsers.getSelectionModel().getSelectedItem();

			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();

			String password = User.generatePassword();

			Query q = session.createNativeQuery("UPDATE applogin SET password = :p WHERE loginemail = :l");
			q.setParameter("p", password).setParameter("l", u.getLoginemail());

			if (q.executeUpdate() == 1) {
				Email.sendPassword(u.getLoginemail(), password);
				alSelectie.setStyle("-fx-text-fill: black");
				alSelectie.setText("Nieuw wachtwoord succesvol gegenereerd!");
			} else {
				alSelectie.setStyle("-fx-text-fill: red");
				alSelectie.setText("Er is een technische fout opgelopen.");
			}
			session.getTransaction().commit();

		}
	}

	@FXML
	private void initAppUsers() {

		aUsers.setPlaceholder(new Label("Er zijn geen users."));

		acEmail.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<User, String> data) {
				return new SimpleStringProperty(data.getValue().getLoginemail());
			}
		});
		acNaam.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<User, String> data) {
				return new SimpleStringProperty(data.getValue().getVolleNaam());
			}
		});
		acAdmin.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<User, String> data) {
				return new SimpleStringProperty(data.getValue().getPositie().equals("ADMIN") ? "JA" : "NEE");
			}
		});

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("FROM User WHERE loginemail != :l");
		q.setParameter("l", Main.sessionUser.getLoginemail());

		ObservableList<User> list = FXCollections.observableArrayList(q.getResultList());

		session.getTransaction().commit();

		aUsers.setItems(list);
	}

	@FXML
	private void clearLabel() {
		alSelectie.setText("");
		// setText "" op label website
	}

	@FXML
	public void initialize() {
		initAppUsers();
		initWebsite();
	}

	// Website
	@FXML
	Tab website;

	@FXML
	private void initWebsite() {

	}

}
