package gui;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.api.client.json.jackson2.JacksonFactory;

import APIFiles.GoogleBooks;
import APIFiles.GoogleBooksExecutableQuery;
import APIFiles.GoogleBooksQueryPrefix;
import database.OpleidingDAO;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Boek;
import logic.Opleiding;

public class BoekenOpleidingSelectionController {
	
//	@FXML
//	static Pane pane;
//	
//	public static Pane getPane() {
//		return pane;
//	}

	@FXML
	private TableView<Opleiding> opleidingSelectionTab;
	@FXML
	private TableColumn<Opleiding, String> colNaam;
	@FXML
	private TableColumn<Opleiding, String> colBeschrijvingOpleiding;
	@FXML
	Button bDoorgaan;
	@FXML
	Label lSelectie;
	
	
	

	@FXML
	private void handleDoorgaan() throws Exception {
		if (opleidingSelectionTab.getSelectionModel().getSelectedItems().size() == 0) {

			lSelectie.setText("Geen opleiding geselecteerd.");

		} else {
			Main.setOpleiding(opleidingSelectionTab.getSelectionModel().getSelectedItem());
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("Boeken.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Boeken toevoegen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					initialize();
				}
			});
			BoekenController c = (BoekenController) f.getController();
			c.setEditMode(opleidingSelectionTab.getSelectionModel().getSelectedItem());
			popup.show();
		}

	}
	

	@FXML
	public void initialize() {
		opleidingSelectionTab.setPlaceholder(new Label("Er zijn geen opleidingen."));
		colNaam.setCellValueFactory(new Callback<CellDataFeatures<Opleiding, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Opleiding, String> data) {
				return new SimpleStringProperty(data.getValue().getNaam());
			}
		});
		colBeschrijvingOpleiding.setCellValueFactory(new Callback<CellDataFeatures<Opleiding, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Opleiding, String> data) {
						return new SimpleStringProperty(data.getValue().getBeschrijving());
					}
				});

		colNaam.setCellFactory(column -> {
			return new TableCell<Opleiding, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(300);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});

		colBeschrijvingOpleiding.setCellFactory(column -> {
			return new TableCell<Opleiding, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(300);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("FROM Opleiding");

		ObservableList<Opleiding> list = FXCollections.observableArrayList(q.list());

		session.getTransaction().commit();

		opleidingSelectionTab.setItems(list);

	}
	
	
	

}
