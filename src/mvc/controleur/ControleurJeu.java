package mvc.controleur;

import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurJeu {
	private GestionJeu jeu;
	private GestionOption option;
	private String[] lettresTrouvees;

	@FXML
	Label lblMot, lblLettresRestantes, lblMsgInteractif, lblMsgJoueur;

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
//		System.out.println(option.toString());
//		jeu.AffErreurs();
		System.out.println(jeu.getMotMystere());
	}

	@FXML
	void poserLettre(ActionEvent event) {
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
			lblMsgInteractif.setText("Dommage " + jeu.getNomJoueur() + ", c'était une mauvaise lettre !");
		}

		afficherNombreLettresRestantes();
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
