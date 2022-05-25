package mvc.controleur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ControleurToolBar {
	@FXML
	private Button btnJouer, btnParametres, btnPreferences, btnAide, btnQuitter;

	@FXML
	public void initialize() {
		btnJouer.setTooltip(new Tooltip("Rejouer"));
		btnParametres.setTooltip(new Tooltip("Param�tres"));
		btnPreferences.setTooltip(new Tooltip("Pr�f�rences"));
		btnAide.setTooltip(new Tooltip("Aide"));
		btnQuitter.setTooltip(new Tooltip("Quitter"));
	}

	@FXML
	public void rejouer() {
		System.exit(0);
	}

	@FXML
	public void ouvrirParametres() throws IOException {
//		Dialog<ButtonType> dialog = new Dialog<>();
//
//		dialog.setTitle("Choix");
//		dialog.setHeaderText("Param�tres");
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/parametres.fxml"));
//
//		ControleurParametres contParametres = new ControleurParametres(jeu, option);
//		loader.setController(contParametres);
//
//		GridPane grille = loader.load();
//		dialog.getDialogPane().setContent(grille);
//
//		ButtonType buttonTypeValider = new ButtonType("Valider", ButtonData.LEFT);
//		ButtonType buttonTypeRes = new ButtonType("Restaurer valeurs\npar d�faut", ButtonData.LEFT);
//		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
//
//		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeValider, buttonTypeRes, buttonTypeAnnuler);
//		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
//
//		Node btnOk = dialog.getDialogPane().lookupButton(buttonTypeValider);
//		Node btnRes = dialog.getDialogPane().lookupButton(buttonTypeRes);
//		Node btnAnnuler = dialog.getDialogPane().lookupButton(buttonTypeAnnuler);
//		btnOk.setStyle("-fx-pref-height: 50px");
//		btnRes.setStyle("-fx-pref-height: 50px");
//		btnAnnuler.setStyle("-fx-pref-height: 50px");
//
//		Optional<ButtonType> result = dialog.showAndWait();
//		if (result.get() == buttonTypeValider) {
//			contParametres.valider();
//			System.out.println(option.toString());
//			jeu.AffErreurs();
//		} else if (result.get() == buttonTypeRes) {
//			ouvrirParametres();
//		} else if (result.get() == buttonTypeAnnuler) {
//			dialog.close();
//		} else {
//			System.out.println("Erreur");
//		}
	}

	@FXML
	public void ouvrirPreferences() throws IOException {
//		Dialog<ButtonType> dialog = new Dialog<>();
//
//		dialog.setTitle("Choix");
//		dialog.setHeaderText("Pr�f�rences");
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/preferences.fxml"));
//
//		ControleurPreferences contPreferences = new ControleurPreferences(jeu, option);
//		loader.setController(contPreferences);
//
//		GridPane grille = loader.load();
//		dialog.getDialogPane().setContent(grille);
//		dialog.getDialogPane().getStylesheets().add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
//
//		ButtonType buttonTypeValider = new ButtonType("Valider", ButtonData.LEFT);
//		ButtonType buttonTypeRes = new ButtonType("Restaurer valeurs\npar d�faut", ButtonData.LEFT);
//		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
//
//		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeValider, buttonTypeRes, buttonTypeAnnuler);
//
//		Node btnOk = dialog.getDialogPane().lookupButton(buttonTypeValider);
//		Node btnRes = dialog.getDialogPane().lookupButton(buttonTypeRes);
//		Node btnAnnuler = dialog.getDialogPane().lookupButton(buttonTypeAnnuler);
//		btnOk.setStyle("-fx-pref-height: 50px");
//		btnRes.setStyle("-fx-pref-height: 50px");
//		btnAnnuler.setStyle("-fx-pref-height: 50px");
//
//		Optional<ButtonType> result = dialog.showAndWait();
//		if (result.get() == buttonTypeValider) {
//			contPreferences.valider();
//			System.out.println(option.toString());
//			jeu.AffErreurs();
//		} else if (result.get() == buttonTypeRes) {
//			ouvrirPreferences();
//		} else if (result.get() == buttonTypeAnnuler) {
//			dialog.close();
//		} else {
//			System.out.println("Erreur");
//		}
	}

	@FXML
	public void ouvrirAide() {
		String info = "R�gles :\n\n"
				+ "Pour gagner, le joueur doit trouver le mot cach� en proposant diff�rentes lettres.\n"
				+ "Si le joueur se trompe plus de 5 fois alors, la partie est perdue." + "\n\nAide au jeu :\n\n"
				+ "Pour s�lectionner une lettre, cliquez avec le clique gauche de votre souris sur les lettres du clavier visuel.\n"
				+ "Vous pouvez �galement taper avec le clavier pour s�lectionner les lettres du clavier virtuel"
				+ "\n\nSupport :\n\n" + "Tel : 06 42 86 57 38\n" + "e-mail : Antoine.scanu1@gmail.com";

		Alert aide = new Alert(AlertType.INFORMATION);
		aide.setHeaderText("R�gles");
		aide.setContentText(info);
		aide.getDialogPane().getStylesheets().add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
		aide.setHeight(900);
		aide.show();
	}

	@FXML
	public void quitter() {
		System.exit(0);
	}
}
