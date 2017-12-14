package gui;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import database.OpleidingDAO;
import database.PersoneelDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.Event;
import logic.Opleiding;
import logic.Personeel;

public class EventsController {

	@FXML
	ComboBox<Opleiding> opleidingen;
	@FXML
	TableView<Event> events;
	@FXML
	TableColumn<Event, String> colTrainer;
	@FXML
	TableColumn<Event, String> colAdres;
	@FXML
	TableColumn<Event, String> colVan;
	@FXML
	TableColumn<Event, String> colTot;
	@FXML
	TableColumn<Event, Integer> colAant;
	@FXML
	TableColumn<Event, Integer> colMax;
	@FXML
	Button bToevoegen;
	@FXML
	Label lSelectie;
	@FXML
	Button bDeelnemers;
	@FXML
	Button bLocatie;
	@FXML
	Button bBewerken;
	@FXML
	Button bAfgelasten;
	@FXML
	TableView<Personeel> deelnemers;
	@FXML
	TableColumn<Personeel, String> colDeelnemers;

	@FXML
	private void clearLabel() {
		lSelectie.setText("");
		deelnemers.getItems().clear();
		deelnemers.setPlaceholder(new Label("Selecteer een event."));
	}

	@FXML
	private void handleToevoegen() throws IOException {
		if (opleidingen.getValue() == null)
			lSelectie.setText("Gelieve eerst een opleiding te selecteren.");
		else {
			lSelectie.setText("");
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Event toevoegen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setEvents();
				}
			});
			AddEventController c = (AddEventController) f.getController();
			c.initiate(opleidingen.getValue(), false, null);
			popup.show();
		}
	}

	@FXML
	private void handleDeelnemers() {
		if (events.getSelectionModel().getSelectedItems().size() == 0) {

			lSelectie.setText("Geen event geselecteerd.");

		} else {
			ObservableList<Personeel> list = FXCollections.observableArrayList(
					PersoneelDAO.getDeelnemers(events.getSelectionModel().getSelectedItem().getEventId()));
			deelnemers.setItems(list);
			deelnemers.setPlaceholder(new Label("Geen deelnemers."));
		}
	}

	@FXML
	private void handleLocatie() throws IOException {
		if (events.getSelectionModel().getSelectedItems().size() == 0)
			lSelectie.setText("Geen event geselecteerd.");
		else {
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("EventLocatie.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			popup.setTitle("Locatie");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			EventLocatieController c = (EventLocatieController) f.getController();
			c.setAdres(events.getSelectionModel().getSelectedItem().getAdres());
			popup.show();
		}
	}

	@FXML
	private void handleBewerken() throws Exception {

		if (events.getSelectionModel().getSelectedItems().size() == 0)
			lSelectie.setText("Geen event geselecteerd.");
		else {
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Event bewerken");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setEvents();
				}
			});
			AddEventController c = (AddEventController) f.getController();
			c.initiate(opleidingen.getValue(), true, events.getSelectionModel().getSelectedItem());
			popup.show();
		}
	}

	@FXML
	private void handleAfgelasten() throws IOException {

		if (events.getSelectionModel().getSelectedItems().size() == 0)
			lSelectie.setText("Geen event geselecteerd.");
		else {
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("EventAfgelasten.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Event afgelasten");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setEvents();
				}
			});
			EventAfgelastenController c = (EventAfgelastenController) f.getController();
			c.e = events.getSelectionModel().getSelectedItem();
			popup.show();
		}
	}

	@FXML
	private void setEvents() {

		deelnemers.getItems().clear();
		deelnemers.setPlaceholder(new Label("Selecteer een event."));
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Event WHERE opleiding_id = " + opleidingen.getValue().getOpleidingId()
				+ " AND einddatum >= CURRENT_DATE() AND afgelast = FALSE ORDER BY startdatum");
		ObservableList<Event> list = FXCollections.observableArrayList(q.list());
		session.getTransaction().commit();

		events.setItems(list);
		events.setPlaceholder(new Label("Geen komende events voor deze opleiding."));

	}

	@FXML
	public void initialize() {

		events.setPlaceholder(new Label("Selecteer een opleiding in de lijst."));
		colTrainer.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> data) {
				return new SimpleStringProperty(data.getValue().getNaamTrainer());
			}
		});
		colTrainer.setCellFactory(column -> {
			return new TableCell<Event, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(350);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});
		colAdres.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> data) {
				return new SimpleStringProperty(data.getValue().getAdres().toString());
			}
		});
		colAdres.setCellFactory(column -> {
			return new TableCell<Event, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(350);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});
		colVan.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> data) {
				return new SimpleStringProperty(data.getValue().getStringStartdatum());
			}
		});
		colTot.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> data) {
				return new SimpleStringProperty(data.getValue().getStringEinddatum());
			}
		});
		colAant.setCellValueFactory(new Callback<CellDataFeatures<Event, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Event, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getAantalDeelnames()).asObject();
			}
		});
		colMax.setCellValueFactory(new Callback<CellDataFeatures<Event, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Event, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getMaxDeelnames()).asObject();
			}
		});

		ObservableList<Opleiding> list = FXCollections.observableArrayList(OpleidingDAO.getAll());
		opleidingen.setItems(list);

		Callback<ListView<Opleiding>, ListCell<Opleiding>> call = lv -> new ListCell<Opleiding>() {

			@Override
			protected void updateItem(Opleiding o, boolean empty) {
				super.updateItem(o, empty);
				setText(o == null ? "" : (o.getOpleidingId() + " - " + o.getNaam()));
			}

		};
		opleidingen.setCellFactory(call);
		opleidingen.setButtonCell(call.call(null));

		deelnemers.setPlaceholder(new Label("Selecteer een event."));
		colDeelnemers.setCellValueFactory(new Callback<CellDataFeatures<Personeel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personeel, String> data) {
				return new SimpleStringProperty(data.getValue().getVolleNaam());
			}
		});
	}

}
