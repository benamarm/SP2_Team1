package gui;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
import logic.Opleiding;
import logic.Publicatie;
import logic.Survey;
import logic.Vraag;

public class SurveysController {
	@FXML
	ComboBox<Opleiding> opleidingen;
	@FXML
	Button bAddSurvey;
	@FXML
	ComboBox<Survey> inactieveSurveys;
	@FXML
	Button bPubliceer;
	@FXML
	Label lCheckSurveys;
	@FXML
	ComboBox<Publicatie> gepubliceerdeSurveys;
	@FXML
	Button bDeactiveer;
	@FXML
	TableView<Vraag> vragenInactief;
	@FXML
	TableColumn<Vraag, Integer> colIndexInactief;
	@FXML
	TableColumn<Vraag, String> colVraagInactief;
	@FXML
	Button bAddVraag;
	@FXML
	Button bDelVraag;
	@FXML
	Button bEditVraag;
	@FXML
	TableView<Vraag> vragenActief;
	@FXML
	TableColumn<Vraag, Integer> colIndexActief;
	@FXML
	TableColumn<Vraag, String> colVraagActief;
	@FXML
	Label lCheckVragen;

	@FXML
	private void handleAddSurvey() throws IOException {
		if (opleidingen.getValue() == null)
			lCheckSurveys.setText("Geen opleiding geselecteerd.");
		else {
			clearLabels();
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddSurvey.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Survey toevoegen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setSurveys();
				}
			});
			AddSurveyController c = (AddSurveyController) f.getController();
			c.setOpleiding(opleidingen.getValue());
			popup.show();
		}
	}

	@FXML
	private void handlePubliceer() throws IOException {
		if (inactieveSurveys.getValue() == null)
			lCheckSurveys.setText("Geen survey geselecteerd.");
		else {
			clearLabels();
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("PubliceerSurvey.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Survey publiceren");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setSurveys();
				}
			});
			PubliceerSurveyController c = (PubliceerSurveyController) f.getController();
			c.setSurvey(inactieveSurveys.getValue());
			popup.show();
		}
	}

	@FXML
	private void handleDeactiveer() {
		if (gepubliceerdeSurveys.getValue() == null)
			lCheckSurveys.setText("Geen survey geselecteerd.");
		else {
			clearLabels();
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();

			try {

				Publicatie p = gepubliceerdeSurveys.getValue();
				p.setActief(false);
				session.update(p);
				session.getTransaction().commit();
				
				//log
				setSurveys();
				lCheckSurveys.setStyle("-fx-text-fill: black");
				lCheckSurveys.setText("Survey succesvol gedeactiveerd.");

			} catch (Exception e) {
				lCheckSurveys.setText("Er is een technische fout opgelopen.");
			}
		}
	}

	@FXML
	private void handleAddVraag() throws IOException {
		if (inactieveSurveys.getValue() == null)
			lCheckVragen.setText("Geen survey geselecteerd.");
		else {
			clearLabels();
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddVraag.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Vraag toevoegen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setVragenInactief();
				}
			});
			AddVraagController c = (AddVraagController) f.getController();
			c.initiate(inactieveSurveys.getValue(), false, vragenInactief.getItems().size() + 1, null);
			popup.show();
		}
	}

	@FXML
	private void handleDelVraag() throws IOException {
		if (vragenInactief.getSelectionModel().getSelectedItems().size() == 0)
			lCheckVragen.setText("Geen vraag geselecteerd.");
		else if (vragenInactief.getItems().size() == 5)
			lCheckVragen.setText("Een survey moet uit minstens 5 vragen bestaan.");
		else {
			clearLabels();
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("DelVraag.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Vraag verwijderen");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setVragenInactief();
				}
			});
			DelVraagController c = (DelVraagController) f.getController();
			c.initiate(vragenInactief.getSelectionModel().getSelectedItem(), vragenInactief.getItems());
			popup.show();
		}
	}

	@FXML
	private void handleEditVraag() throws IOException {
		if (vragenInactief.getSelectionModel().getSelectedItems().size() == 0)
			lCheckVragen.setText("Geen vraag geselecteerd.");
		else {
			clearLabels();
			Stage popup = new Stage();
			FXMLLoader f = new FXMLLoader(getClass().getResource("AddVraag.fxml"));
			Parent root = (Parent) f.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			popup.setTitle("Vraag bewerken");
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setResizable(false);
			popup.centerOnScreen();
			popup.setScene(scene);
			popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					setVragenInactief();
				}
			});
			AddVraagController c = (AddVraagController) f.getController();
			c.initiate(inactieveSurveys.getValue(), true, null, vragenInactief.getSelectionModel().getSelectedItem());
			popup.show();
		}
	}

	@FXML
	private void clearLabels() {
		lCheckSurveys.setText("");
		lCheckSurveys.setStyle("-fx-text-fill: red");
		lCheckVragen.setText("");
		lCheckVragen.setStyle("-fx-text-fill: red");
	}

	@FXML
	private void setSurveys() {
		vragenInactief.getItems().clear();
		vragenActief.getItems().clear();

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		// Alle surveys die niet gepubliceerd zijn
		Query q = session.createNativeQuery("SELECT * FROM surveys s WHERE opleiding_id = "
				+ opleidingen.getValue().getOpleidingId()
				+ " AND NOT EXISTS (SELECT 1 FROM surveyPublicaties p WHERE p.survey_id = s.survey_id AND tot > SYSDATE() AND actief = 1)",
				Survey.class);
		ObservableList<Survey> inactief = FXCollections.observableArrayList(q.getResultList());

		// Alle surveys die gepubliceerd zijn
		q = session.createQuery("FROM Publicatie p WHERE p.publicatieId = (SELECT MAX(p2.publicatieId) from Publicatie p2 WHERE p2.survey.surveyId = p.survey.surveyId) AND p.actief = TRUE ORDER BY tot");
		ObservableList<Publicatie> actief = FXCollections.observableArrayList(q.getResultList());
		session.getTransaction().commit();

		inactieveSurveys.setItems(inactief);
		gepubliceerdeSurveys.setItems(actief);

		Callback<ListView<Survey>, ListCell<Survey>> callInactief = lv -> new ListCell<Survey>() {
			@Override
			protected void updateItem(Survey s, boolean empty) {
				super.updateItem(s, empty);
				setText(s == null ? "" : (s.getTitel()));
			}
		};

		Callback<ListView<Publicatie>, ListCell<Publicatie>> callActief = lv -> new ListCell<Publicatie>() {
			@Override
			protected void updateItem(Publicatie p, boolean empty) {
				super.updateItem(p, empty);
				setText(p == null ? "" : ("Tot " + p.getStringTot() + ": " + p.getSurvey().getTitel()));
			}
		};
		inactieveSurveys.setCellFactory(callInactief);
		inactieveSurveys.setButtonCell(callInactief.call(null));
		gepubliceerdeSurveys.setCellFactory(callActief);
		gepubliceerdeSurveys.setButtonCell(callActief.call(null));
	}

	@FXML
	private void setVragenInactief() {
		clearLabels();
		if (inactieveSurveys.getValue() != null) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("FROM Vraag WHERE survey_id = " + inactieveSurveys.getValue().getSurveyId()
					+ " AND inx > 0 ORDER BY inx");
			ObservableList<Vraag> list = FXCollections.observableArrayList(q.list());
			session.getTransaction().commit();
			vragenInactief.setItems(list);
		}
	}

	@FXML
	private void setVragenActief() {
		clearLabels();
		if (gepubliceerdeSurveys.getValue() != null) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("FROM Vraag WHERE survey_id = "
					+ gepubliceerdeSurveys.getValue().getSurvey().getSurveyId() + " AND inx > 0 ORDER BY inx");
			ObservableList<Vraag> list = FXCollections.observableArrayList(q.list());
			session.getTransaction().commit();
			vragenActief.setItems(list);
		}
	}

	@FXML
	public void initialize() {
		vragenInactief.setPlaceholder(new Label("Selecteer eerst een opleiding, dan een survey in de lijsten."));
		vragenActief.setPlaceholder(new Label("Selecteer eerst een opleiding, dan een survey in de lijsten."));

		colIndexInactief
				.setCellValueFactory(new Callback<CellDataFeatures<Vraag, Integer>, ObservableValue<Integer>>() {
					@Override
					public ObservableValue<Integer> call(CellDataFeatures<Vraag, Integer> data) {
						return new SimpleIntegerProperty(data.getValue().getInx()).asObject();
					}
				});
		colVraagInactief.setCellValueFactory(new Callback<CellDataFeatures<Vraag, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vraag, String> data) {
				return new SimpleStringProperty(data.getValue().getVraag());
			}
		});
		colVraagInactief.setCellFactory(column -> {
			return new TableCell<Vraag, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(400);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
		});

		colIndexActief.setCellValueFactory(new Callback<CellDataFeatures<Vraag, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Vraag, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getInx()).asObject();
			}
		});
		colVraagActief.setCellValueFactory(new Callback<CellDataFeatures<Vraag, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Vraag, String> data) {
				return new SimpleStringProperty(data.getValue().getVraag());
			}
		});
		colVraagActief.setCellFactory(column -> {
			return new TableCell<Vraag, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(item);
					if (item != null && !item.equals("")) {
						Tooltip t = new Tooltip(item);
						t.setMaxWidth(400);
						t.setWrapText(true);
						setTooltip(t);
					}
				}
			};
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
	}

}
