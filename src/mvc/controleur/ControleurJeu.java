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
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurJeu {
	private GestionJeu jeu;
	private GestionOption option;

	@FXML
	Label lblMot;

	public ControleurJeu(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void initialize() {
		System.out.println(option.toString());
		jeu.AffErreurs();
	}

	@FXML
	void poserLettre(ActionEvent event) {
		String textBtn = ((Button) event.getSource()).getText();
		lblMot.setText(textBtn);

		if (textBtn.equals("B")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/fin-partie.fxml"));
			ControleurFinPartie contFinPartie = new ControleurFinPartie();
			loader.setController(contFinPartie);

			try {
				VBox jeu = loader.load();
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(jeu));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
