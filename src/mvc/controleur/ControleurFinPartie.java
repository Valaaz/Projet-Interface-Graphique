package mvc.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControleurFinPartie {
	@FXML
	Label msgFinPartie;
	
	@FXML
	public void initialize() {
		msgFinPartie.setText("Vous avez gagné !");
	}

}
