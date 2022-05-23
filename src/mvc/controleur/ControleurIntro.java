package mvc.controleur;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurIntro {
	private GestionJeu jeu;
	private GestionOption option;

	public ControleurIntro(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void jouer(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/jeu.fxml"));
		ControleurJeu contJeu = new ControleurJeu(jeu, option);
		loader.setController(contJeu);

		try {
			VBox jeu = loader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(jeu));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
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
	public void ouvrirPreferences() throws IOException {
		Dialog<ButtonType> dialog = new Dialog<>();

		dialog.setTitle("Choix");
		dialog.setHeaderText("Préférences");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/preferences.fxml"));

		ControleurPreferences contPreferences = new ControleurPreferences(jeu, option);
		loader.setController(contPreferences);

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
			contPreferences.valider();
			System.out.println(option.toString());
			jeu.AffErreurs();
		} else if (result.get() == buttonTypeRes) {
			ouvrirPreferences();
		} else if (result.get() == buttonTypeAnnuler) {
			dialog.close();
		} else {
			System.out.println("Erreur");
		}
	}

}
