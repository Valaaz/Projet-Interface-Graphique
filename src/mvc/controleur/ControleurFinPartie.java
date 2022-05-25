package mvc.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurFinPartie {
	private GestionJeu jeu;
	private GestionOption option;
	private boolean gagnant;
	private String skin;

	@FXML
	private Label msgFin, msgFinPendu;

	@FXML
	private ImageView imagePenduFin;

	public ControleurFinPartie(GestionJeu jeu, GestionOption option, boolean gagne) {
		super();
		this.jeu = jeu;
		this.option = option;
		this.gagnant = gagne;
	}

	public void initialize() {

		if (option.getSkinPendu() == 0)
			skin = "A";
		else
			skin = "B";

		if (gagnant) {
			msgFin.setText("SUPER " + jeu.getNomJoueur() + " tu as réussi ! Tu as GAGNÉ et trouvé le mot "
					+ jeu.getMotMystere() + " ! ");

			msgFinPendu.setText("MERCI " + jeu.getNomJoueur() + " ! Tu m'as sorti d'affaire !");

			Image img = new Image(
					getClass().getResource("/images/pendu_image/" + skin + "Gagner.png").toExternalForm());
			imagePenduFin.setImage(img);

		} else {
			msgFin.setText("DOMMAGE " + jeu.getNomJoueur() + " ! Tu as PERDU.. Le mot était " + jeu.getMotMystere()
					+ " mais tu fera mieux la prochaine fois !");

			msgFinPendu
					.setText("NON " + jeu.getNomJoueur() + " ! Tu as échoué mais rien n'est perdu, tu peux réessayer.");

			Image img = new Image(getClass().getResource("/images/pendu_image/" + skin + "Perdu.png").toExternalForm());
			imagePenduFin.setImage(img);
		}

	}
}
