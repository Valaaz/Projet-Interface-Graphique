package mvc.controleur;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurJeu {
	private GestionJeu jeu;
	private GestionOption option;

	@FXML
	Label lblMot, lblLettresRestantes, lblMsgInterfactif, lblMsgJoueur;

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

	@FXML
	public void rejouer() {
		System.exit(0);
	}

	@FXML
	public void ouvrirParametres() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Choix");
		dialog.setHeaderText("Paramètres");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/parametres.fxml"));

		ControleurParametres contParametres = new ControleurParametres(jeu, option);
		loader.setController(contParametres);

		GridPane grille = loader.load();
		dialog.getDialogPane().setContent(grille);

		ButtonType buttonTypeValider = new ButtonType("Valider", ButtonData.LEFT);
		ButtonType buttonTypeRes = new ButtonType("Restaurer valeurs\npar défaut", ButtonData.LEFT);
		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);

		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeValider, buttonTypeRes, buttonTypeAnnuler);

		Node btnOk = dialog.getDialogPane().lookupButton(buttonTypeValider);
		Node btnRes = dialog.getDialogPane().lookupButton(buttonTypeRes);
		Node btnAnnuler = dialog.getDialogPane().lookupButton(buttonTypeAnnuler);
		btnOk.setStyle("-fx-pref-height: 50px");
		btnRes.setStyle("-fx-pref-height: 50px");
		btnAnnuler.setStyle("-fx-pref-height: 50px");

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == buttonTypeValider) {
			contParametres.valider();
			System.out.println(option.toString());
			jeu.AffErreurs();
		} else if (result.get() == buttonTypeRes) {
			ouvrirParametres();
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

	@FXML
	public void ouvrirAide() {
		String info = "Règles :\n\n"
				+ "Pour gagner, le joueur doit trouver le mot caché en proposant différentes lettres.\n"
				+ "Si le joueur se trompe plus de 5 fois alors, la partie est perdue." + "\n\nAide au jeu :\n\n"
				+ "Pour sélectionner une lettre, cliquez avec le clique gauche de votre souris sur les lettres du clavier visuel.\n"
				+ "Vous pouvez également taper avec le clavier pour sélectionner les lettres du clavier virtuel"
				+ "\n\nSupport :\n\n" + "Tel : 06 42 86 57 38\n" + "e-mail : Antoine.scanu1@gmail.com";

		Alert aide = new Alert(AlertType.INFORMATION);
		aide.setHeaderText("Règles");
		aide.setContentText(info);
		aide.setHeight(600);
		aide.show();
	}

	@FXML
	public void quitter() {
		System.exit(0);
	}
}
