package testing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DynamicControlsJavaFX {
	 @FXML
	    private Button add;
	    @FXML 
	    private TextField button;
	    @FXML 
	    private ListView<Button> visibleList;
	 
	    private ObservableList<Button> buttons = 
	        FXCollections.observableArrayList();
	 
	    @FXML
	    protected void initialize() {
	        visibleList.setItems(buttons);
	    } 
	 
	    @FXML
	    void addName() {
	        Button b = new Button(button.getText());
	        buttons.add(b);
	        b.addEventHandler(MouseEvent.MOUSE_CLICKED, 
	            (event -> b.setText(invertCapitals(b.getText()))));
	        button.setText("");
	    }
	 
	    public static String invertCapitals(String other) {
	        return other.chars().mapToObj(DynamicControlsJavaFX::flipCap)
	                            .map(c -> Character.toString(c))
	                            .reduce("", (s, c) -> s + c);
	    }
	 
	    public static Character flipCap(int c) {
	        if (c >= 'A' && c <= 'Z') {
	           return (char)(c - 'A' + 'a');
	        } else if (c >= 'a' && c <= 'z') {
	           return (char)(c - 'a' + 'A');
	        } else {
	           return (char)c;
	        }
	    }
}
