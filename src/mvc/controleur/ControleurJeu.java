package mvc.controleur;

import java.io.IOException;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurJeu {
	private GestionJeu jeu;
	private GestionOption option;
	private String[] lettresTrouvees;

	@FXML
	private Label lblMot, lblLettresRestantes, lblMsgInteractif, lblMsgJoueur;

	@FXML
	private ImageView imgPendu;

	public ControleurJeu(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void initialize() {
		lettresTrouvees = new String[jeu.getMotMystere().length()];
		for (int i = 0; i < lettresTrouvees.length; i++) {
			lettresTrouvees[i] = "_";
		}
		lblMot.setText(String.join("", lettresTrouvees));

		lblMsgJoueur
				.setText("A toi de jouer " + jeu.getNomJoueur() + ", rentre une lettre pour voir si elle est valide");

		afficherNombreLettresRestantes();
		System.out.println(jeu.getMotMystere());

		lblMsgInteractif.setText("Nombre d'erreurs restantes : " + (jeu.getNbMaxErreurs() - jeu.getNbErreurs()));

//		Image img = new Image(getClass().getResource("images/aide.png").toExternalForm());
//		imgPendu.setImage(img);
	}

	@FXML
	void poserLettre(ActionEvent event) throws IOException {
		Button btn = ((Button) event.getSource());
		String lettre = btn.getText();
		char l = lettre.charAt(0);

		Vector<Integer> v = new Vector<Integer>();
		if (jeu.ChercherLettreDansMot(l, v) != 0) {
			afficherLettresMot(lettre);
			btn.setStyle("-fx-background-color: #00CC00");
			btn.setDisable(true);
			lblMsgInteractif.setText("Bravo " + jeu.getNomJoueur() + ", tu as trouvé une lettre !");
		} else {
			btn.setStyle("-fx-background-color: #CCCCCC");
			btn.setDisable(true);
			jeu.MAJNbErreurs();
			lblMsgInteractif.setText(
					"Dommage ! Tu as encore le droit à " + (jeu.getNbMaxErreurs() - jeu.getNbErreurs()) + " erreurs");
		}

		afficherNombreLettresRestantes();
		verifierVictoire();
	}

	private void verifierVictoire() throws IOException {
		if (jeu.ToutTrouve()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/fin-partie.fxml"));
			FXMLLoader toolbar = new FXMLLoader(getClass().getResource("/mvc/vue/toolbar.fxml"));
			ControleurFinPartie contFinPartie = new ControleurFinPartie();
			loader.setController(contFinPartie);

			GridPane root = loader.load();
			root.add(toolbar.load(), 0, 0, 3, 1);
			Stage stage = (Stage) lblMot.getScene().getWindow();
			stage.setScene(new Scene(root));
		}

		if (jeu.getNbErreurs() == jeu.getNbMaxErreurs()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/fin-partie.fxml"));
			FXMLLoader toolbar = new FXMLLoader(getClass().getResource("/mvc/vue/toolbar.fxml"));
			ControleurFinPartie contFinPartie = new ControleurFinPartie();
			loader.setController(contFinPartie);

			GridPane root = loader.load();
			root.add(toolbar.load(), 0, 0, 3, 1);
			Stage stage = (Stage) lblMot.getScene().getWindow();
			stage.setScene(new Scene(root));
		}
	}

	private int getNombreLettresRestantes() {
		return jeu.getMotMystere().length() - jeu.getNbLettresTrouvees();
	}

	private void afficherNombreLettresRestantes() {
		lblLettresRestantes.setText("Lettre(s) restante(s) : " + getNombreLettresRestantes());
	}

	private void afficherLettresMot(String lettre) {
		String[] mot = jeu.getMotMystere().split("");
		for (int i = 0; i < mot.length; i++) {
			if (mot[i].equals(lettre) && lettresTrouvees[i].equals("_"))
				lettresTrouvees[i] = lettre;
		}

		lblMot.setText(String.join("", lettresTrouvees));
	}
}
