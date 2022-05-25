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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurIntro {
	private GestionJeu jeu;
	private GestionOption option;

	@FXML
	private TextField textPseudoIntro;
	
	@FXML
	private ImageView imagePendu;

	@FXML
	private Label lblTitre;

	@FXML
	private Label lblNom;

	@FXML
	private Label lblMessage;

	@FXML
	private GridPane gridPane;

	@FXML
	private Button btnJouer;

	@FXML
	private Button btnParametres;

	@FXML
	private Button btnPreferences;

	@FXML
	private Button btnAide;

	@FXML
	private Button btnQuitter;

	public ControleurIntro(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void initialize() {
		if (textPseudoIntro.getText().trim() == "") {
			btnJouer.setDisable(true);
		} else {
			textPseudoIntro.setText(jeu.getNomJoueur().trim());
			btnJouer.setDisable(false);
		}

		textPseudoIntro.textProperty().addListener((observable) -> {
			jeu.setNomJoueur(textPseudoIntro.getText().trim());
			if (textPseudoIntro.getText().trim() == "") {
				btnJouer.setDisable(true);
			} else {
				btnJouer.setDisable(false);
			}
		});
	}

	@FXML
	public void jouer(ActionEvent event) throws IOException {
		jeu.InitialiserPartie();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/jeu.fxml"));
		FXMLLoader toolbar = new FXMLLoader(getClass().getResource("/mvc/vue/toolbar.fxml"));
		ControleurJeu contJeu = new ControleurJeu(jeu, option);
		loader.setController(contJeu);
		ControleurToolBar contToolBar = new ControleurToolBar(jeu, option);
		toolbar.setController(contToolBar);

		VBox jeu = loader.load();
		jeu.getChildren().add(0, toolbar.load());
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(jeu);
		stage.setScene(scene);
		if (option.isModeSombre()) {
			if (option.getTaillePolice() == 12) {
				scene.getStylesheets().add(getClass().getResource("/css/applicationDark12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				scene.getStylesheets().add(getClass().getResource("/css/applicationDark14.css").toExternalForm());
			} else {
				scene.getStylesheets().add(getClass().getResource("/css/applicationDark16.css").toExternalForm());
			}
		} else {
			if (option.getTaillePolice() == 12) {
				scene.getStylesheets().add(getClass().getResource("/css/applicationLight12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				scene.getStylesheets().add(getClass().getResource("/css/applicationLight14.css").toExternalForm());
			} else {
				scene.getStylesheets().add(getClass().getResource("/css/applicationLight16.css").toExternalForm());
			}
		}
	}

	@FXML
	public void ouvrirParametres() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Paramètres");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/parametres.fxml"));

		ControleurParametres contParametres = new ControleurParametres(jeu, option);
		loader.setController(contParametres);

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
			contParametres.valider();
		} else if (result.get() == buttonTypeRes) {
			ouvrirParametres();
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

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
			System.out.println("Bonjour");
			if (option.getTaillePolice() == 12) {
				aide.getDialogPane().getStylesheets()
						.add(getClass().getResource("/css/applicationDark12.css").toExternalForm());
			} else if (option.getTaillePolice() == 14) {
				System.out.println("Bonjour");
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

	public void quitter() {
		System.exit(0);
	}

	@FXML
	public void ouvrirPreferences() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Préférences");
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
				dialog.getDialogPane().getStylesheets().add(getClass().getResource("/css/applicationDark14.css").toExternalForm());
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
			textPseudoIntro.setText(jeu.getNomJoueur().trim());
			if (option.isModeSombre()) {
				btnJouer.setStyle("-fx-background-color : #856396");
				btnParametres.setStyle("-fx-background-color : #856396");
				btnPreferences.setStyle("-fx-background-color : #856396");
				btnAide.setStyle("-fx-background-color : #856396");
				btnQuitter.setStyle("-fx-background-color : #856396");
				gridPane.setStyle("-fx-background-color : #c9aac6");
				if (option.getTaillePolice() == 12) {
					lblTitre.setStyle("-fx-font-size : 12px");
					lblNom.setStyle("-fx-font-size : 12px");
					lblMessage.setStyle("-fx-font-size : 12px;");
				} else if (option.getTaillePolice() == 14) {
					lblTitre.setStyle("-fx-font-size : 14px");
					lblNom.setStyle("-fx-font-size : 14px");
					lblMessage.setStyle("-fx-font-size : 14px;");
				} else {
					lblTitre.setStyle("-fx-font-size : 16px");
					lblNom.setStyle("-fx-font-size : 16px");
					lblMessage.setStyle("-fx-font-size : 16px;");
				}
			} else {
				btnJouer.setStyle("-fx-background-color : #99CCFF");
				btnParametres.setStyle("-fx-background-color : #99CCFF");
				btnPreferences.setStyle("-fx-background-color : #99CCFF");
				btnAide.setStyle("-fx-background-color : #99CCFF");
				btnQuitter.setStyle("-fx-background-color : #99CCFF");
				gridPane.setStyle("-fx-background-color : #E8F9FD");
				if (option.getTaillePolice() == 12) {
					lblTitre.setStyle("-fx-font-size : 12px");
					lblNom.setStyle("-fx-font-size : 12px");
					lblMessage.setStyle("-fx-font-size : 12px");
				} else if (option.getTaillePolice() == 14) {
					lblTitre.setStyle("-fx-font-size : 14px");
					lblNom.setStyle("-fx-font-size : 14px");
					lblMessage.setStyle("-fx-font-size : 14px");
				} else {
					lblTitre.setStyle("-fx-font-size : 16px");
					lblNom.setStyle("-fx-font-size : 16px");
					lblMessage.setStyle("-fx-font-size : 16px");
				}
			}
			if(option.getSkinPendu() == 0) {
				Image img = new Image(getClass().getResource("/images/pendu_image/AGagner.png").toExternalForm());
				imagePendu.setImage(img);
			}
			else {
				Image img = new Image(getClass().getResource("/images/pendu_image/BGagner.png").toExternalForm());
				imagePendu.setImage(img);
			}
		} else if (result.get() == buttonTypeRes) {
			ouvrirPreferences();
			textPseudoIntro.setText(jeu.getNomJoueur().trim());
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

}
