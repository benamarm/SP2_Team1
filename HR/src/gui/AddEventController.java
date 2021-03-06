package gui;

import java.sql.Date;
import java.time.LocalDate;
import database.AdresDAO;
import database.EventDAO;
import database.LogDAO;
import email.Email;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import logic.Adres;
import logic.Event;
import logic.Opleiding;

public class AddEventController {

	private Opleiding opleiding;
	private Boolean edit;
	private Event teBewerken;

	@FXML
	Label lOpleiding;
	@FXML
	TextField tTrainer;
	@FXML
	TextField tMax;
	@FXML
	DatePicker dStart;
	@FXML
	DatePicker dEind;
	@FXML
	ComboBox<Adres> adressen;
	@FXML
	TextField adresStraat;
	@FXML
	TextField adresNum;
	@FXML
	TextField adresPostcode;
	@FXML
	TextField adresLand;
	@FXML
	Button bToevoegen;
	@FXML
	Label lCheck;

	@FXML
	private void clearBestaandeAdres() {
		adressen.getSelectionModel().clearSelection();
	}

	@FXML
	private void clearNieuweAdres() {
		adresStraat.setText("");
		adresNum.setText("");
		adresPostcode.setText("");
		adresLand.setText("");
	}

	@FXML
	private void handleToevoegen() {
		// Check of alle velden ingevuld zijn
		if (tTrainer.getText().equals("") || tMax.getText().equals("") || dStart.getValue() == null
				|| dEind.getValue() == null
				|| (adressen.getValue() == null && (adresStraat.getText().equals("") || adresNum.getText().equals("")
						|| adresPostcode.getText().equals("") || adresLand.getText().equals(""))))
			lCheck.setText("Gelieve alle velden in te vullen.");
		// Max aantal deelnames < 5 ?
		else if (Integer.parseInt(tMax.getText()) < 5)
			lCheck.setText("Maximum aantal deelnames moet minstens 5 zijn.");
		// Startdatum moet na vandaag komen
		else if (!dStart.getValue().isAfter(LocalDate.now()))
			lCheck.setText("Ongepaste startdatum");
		// Einddatum moet na startdatum komen
		else if (!dEind.getValue().isAfter(dStart.getValue()))
			lCheck.setText("Ongepaste einddatum");
		else if (edit && Integer.parseInt(tMax.getText()) < teBewerken.getMaxDeelnames())
			lCheck.setText("Maximum aantal deelnemers mag enkel verhoogd worden.");
		else {

			Adres a;
			if (adresStraat.getText().equals(""))
				a = adressen.getValue();
			else {
				a = new Adres();
				a.setStraat(adresStraat.getText());
				a.setNummer(Integer.parseInt(adresNum.getText()));
				a.setPostcode(Integer.parseInt(adresPostcode.getText()));
				a.setLand(adresLand.getText());
				AdresDAO.save(a);
			}

			Event nieuw = new Event();
			nieuw.setAantalDeelnames(0);
			nieuw.setStartdatum(Date.valueOf(dStart.getValue()));
			nieuw.setEinddatum(Date.valueOf(dEind.getValue()));
			nieuw.setMaxDeelnames(Integer.parseInt(tMax.getText()));
			nieuw.setNaamTrainer(tTrainer.getText());
			nieuw.setOpleiding(opleiding);
			nieuw.setAdres(a);
			nieuw.setAfgelast(false);
			if (edit) {

				nieuw.setEventId(teBewerken.getEventId());
				nieuw.setAantalDeelnames(teBewerken.getAantalDeelnames());

				if (nieuw.equals(teBewerken)) {
					lCheck.setText("U heeft niets aangepast.");
					return;
				}

				if (EventDAO.update(nieuw)) {
					bToevoegen.setDisable(true);
					if (EventDAO.initialize(nieuw) > 0)
						Email.eventGewijzigd(nieuw, teBewerken);
					LogDAO.eventBewerkt(nieuw);
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Event succesvol bewerkt.");
				} else {
					bToevoegen.setDisable(true);
					lCheck.setText("Er is een technische fout opgelopen.");
				}

			} else {

				if (EventDAO.save(nieuw)) {
					bToevoegen.setDisable(true);
					LogDAO.eventToegevoegd(nieuw);
					lCheck.setStyle("-fx-text-fill: black");
					lCheck.setText("Event succesvol toegevoegd.");
				} else {
					bToevoegen.setDisable(true);
					lCheck.setText("Er is een technische fout opgelopen.");
				}
			}

		}
	}

	public void initiate(Opleiding o, boolean edit, Event e) {
		opleiding = o;
		lOpleiding.setText(o.getNaam());
		this.edit = edit;
		if (edit) {
			teBewerken = e;
			bToevoegen.setText("Bewerken");
			tTrainer.setText(e.getNaamTrainer());
			tMax.setText(new Integer(e.getMaxDeelnames()).toString());
			dStart.setValue(e.getStartdatum().toLocalDate());
			dEind.setValue(e.getEinddatum().toLocalDate());
			for (Adres a : adressen.getItems()) {
				if (a.equals(e.getAdres())) {
					adressen.getSelectionModel().select(a);
					break;
				}
			}
		}
	}

	@FXML
	public void initialize() {

		// Adressen in ComboBox zetten
		ObservableList<Adres> list = FXCollections.observableArrayList(AdresDAO.getAllOrdered());
		adressen.setItems(list);

		Callback<ListView<Adres>, ListCell<Adres>> call = lv -> new ListCell<Adres>() {

			@Override
			protected void updateItem(Adres a, boolean empty) {
				super.updateItem(a, empty);
				setText(a == null ? "" : (a.toString()));
			}

		};
		adressen.setCellFactory(call);
		adressen.setButtonCell(call.call(null));

		// Numerieke TextField
		tMax.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tMax.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		adresNum.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					adresNum.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		adresPostcode.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					adresPostcode.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

}
