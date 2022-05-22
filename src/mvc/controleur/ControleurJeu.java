package mvc.controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControleurJeu {
	@FXML
	Label lblMot;
	
	@FXML
	void poserLettre(ActionEvent event) {
		String textBtn = ((Button)event.getSource()).getText();
		lblMot.setText(textBtn);
		
		if (textBtn.equals("B")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/fin-partie.fxml"));
	        ControleurFinPartie contFinPartie = new ControleurFinPartie();
	    	loader.setController(contFinPartie);
	    	
	    	try {
				VBox jeu = loader.load();
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(jeu));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
