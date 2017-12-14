package gui;

import org.hibernate.Session;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Vraag;

public class DelVraagController {
	
	private Vraag toDelete;
	private ObservableList<Vraag> vragen;
	
	public void initiate(Vraag toDelete, ObservableList<Vraag> vragen) {
		this.toDelete = toDelete;
		this.vragen = vragen;
	}

	@FXML
	Button bOK;
	@FXML
	Label lCheck;
	
	@FXML
	private void handleOK() {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		try {
			
			for(Vraag v : vragen) {
				if(v.getInx() == toDelete.getInx()) {
					
					int index = v.getInx();
					v.setInx(0);
					session.update(v);
					
					for(int i = index; i < vragen.size(); i++) {
						vragen.get(i).setInx(vragen.get(i).getInx() - 1);
						session.update(vragen.get(i));
					}
					break;
				}
			}
			
			session.getTransaction().commit();
			
			bOK.setDisable(true);
			//log
			lCheck.setStyle("-fx-text-fill: black");
			lCheck.setText("Vraag succesvol verwijderd!");
			
			
		}catch(Exception e) {
			bOK.setDisable(true);
			lCheck.setText("Er is een technische fout opgelopen.");
		}
	}
}
