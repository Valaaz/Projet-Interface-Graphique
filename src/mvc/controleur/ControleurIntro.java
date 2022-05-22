package mvc.controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControleurIntro {
	@FXML
	void jouer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/jeu.fxml"));
        ControleurJeu contJeu = new ControleurJeu();
    	loader.setController(contJeu);
    	
    	try {
			VBox jeu = loader.load();
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(jeu));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
