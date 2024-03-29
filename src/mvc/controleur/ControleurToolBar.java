package mvc.controleur;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurToolBar {

	private GestionJeu jeu;
	private GestionOption option;
	boolean enPartie;

	public ControleurToolBar(GestionJeu jeu, GestionOption option, boolean enPartie) {
		super();
		this.jeu = jeu;
		this.option = option;
		this.enPartie = enPartie;
	}

	@FXML
	private Button btnJouer, btnParametres, btnPreferences, btnAide, btnQuitter;

	@FXML
	private VBox vbox;

	@FXML
	private Label lblMot, lblMsgJoueur, lblLettresRestantes, lblMsgInteractif;

	@FXML
	private GridPane clavier;

	@FXML
	public void initialize() {
		btnJouer.setTooltip(new Tooltip("Rejouer"));
		btnParametres.setTooltip(new Tooltip("Parametres"));
		btnPreferences.setTooltip(new Tooltip("Preferences"));
		btnAide.setTooltip(new Tooltip("Aide"));
		btnQuitter.setTooltip(new Tooltip("Quitter"));
		btnParametres.setDisable(enPartie);
		btnPreferences.setDisable(enPartie);
	}

	@FXML
	public void rejouer(ActionEvent event) throws IOException {
		ControleurIntro contIntro = new ControleurIntro(jeu, option);
		contIntro.jouer(event);
	}

	@FXML
	public void ouvrirParametres() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Parametres");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/parametres.fxml"));

		ControleurParametres contParametres = new ControleurParametres(jeu, option);
		loader.setController(contParametres);

		GridPane grille = loader.load();
		dialog.getDialogPane().setContent(grille);

		ButtonType buttonTypeValider = new ButtonType("Valider", ButtonData.LEFT);
		ButtonType buttonTypeRes = new ButtonType("Restaurer valeurs\npar defaut", ButtonData.LEFT);
		ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);

		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeValider, buttonTypeRes, buttonTypeAnnuler);

		if (option.isModeSombre()) {
			if (option.getTaillePolice() == 12) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark14.css").toExternalForm());
			} else {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark16.css").toExternalForm());
			}
		} else {
			if (option.getTaillePolice() == 12) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight14.css").toExternalForm());
			} else {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
			}
		}

		Node btnOk = dialog.getDialogPane().lookupButton(buttonTypeValider);
		Node btnRes = dialog.getDialogPane().lookupButton(buttonTypeRes);
		Node btnAnnuler = dialog.getDialogPane().lookupButton(buttonTypeAnnuler);
		btnOk.setStyle("-fx-pref-height: 50px");
		btnRes.setStyle("-fx-pref-height: 50px");
		btnAnnuler.setStyle("-fx-pref-height: 50px");

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == buttonTypeValider) {
			contParametres.valider();
		} else if (result.get() == buttonTypeRes) {
			option.setTheme("Tous les themes");
			jeu.setNbMaxErreurs(5);
			option.setDifficulte("Moyen");
			msgReinitialisationValeurs();
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

	@FXML
	public void ouvrirPreferences() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Preferences");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/preferences.fxml"));

		ControleurPreferences contPreferences = new ControleurPreferences(jeu, option);
		loader.setController(contPreferences);

		GridPane grille = loader.load();
		dialog.getDialogPane().setContent(grille);

		if (option.isModeSombre()) {
			if (option.getTaillePolice() == 12) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark14.css").toExternalForm());
			} else {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark16.css").toExternalForm());
			}
		} else {
			if (option.getTaillePolice() == 12) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight14.css").toExternalForm());
			} else {
				dialog.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
			}
		}

		ButtonType buttonTypeValider = new ButtonType("Valider", ButtonData.LEFT);
		ButtonType buttonTypeRes = new ButtonType("Restaurer valeurs\npar defaut", ButtonData.LEFT);
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
			contPreferences.valider();
		} else if (result.get() == buttonTypeRes) {
			option.setModeSombre(false);
			option.setTaillePolice(12);
			option.setSkinPendu(0);
			msgReinitialisationValeurs();
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

	@FXML
	public void ouvrirAide() {
		String info = "Regles :\n\n"
				+ "Pour gagner, le joueur doit trouver le mot cache en proposant differentes lettres.\n"
				+ "Si le joueur se trompe plus de 5 fois alors, la partie est perdue." + "\n\nAide au jeu :\n\n"
				+ "Pour selectionner une lettre, cliquez avec le clique gauche de votre souris sur les lettres du clavier visuel.\n"
				+ "Vous pouvez egalement taper avec le clavier pour selectionner les lettres du clavier virtuel"
				+ "\n\nSupport :\n\n" + "Tel : 06 42 86 57 38\n" + "e-mail : Antoine.scanu1@gmail.com";

		Alert aide = new Alert(AlertType.INFORMATION);
		aide.setHeaderText("Regles");
		aide.setContentText(info);
		if (option.isModeSombre()) {
			if (option.getTaillePolice() == 12) {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark14.css").toExternalForm());
			} else {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark16.css").toExternalForm());
			}
		} else {
			if (option.getTaillePolice() == 12) {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight14.css").toExternalForm());
			} else {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
			}
		}
		aide.setHeight(900);
		aide.show();
	}

	@FXML
	public void quitter() {
		System.exit(0);
	}

	private void msgReinitialisationValeurs() {
		Alert info = new Alert(AlertType.INFORMATION, "Vous avez reinitialise les valeurs par defaut", ButtonType.OK);
		info.setHeaderText("Valeurs reinitialisees");
		info.showAndWait();
	}
}
