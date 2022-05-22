package mvc.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControleurParametres {
	ToggleGroup groupeErreurMax, groupeDifficulte;
	
	@FXML
	ChoiceBox<String> chBoxTheme;
	
	@FXML
    private RadioButton radioBtnCinq, radioBtnDix, radioBtnFacile, radioBtnMoyen, radioBtnDifficile;
	
	@FXML
	public void initialize() {
		chBoxTheme.getItems().addAll("Tous les thèmes", "Animal", "Informatique", "Espace", "Nature", "Mobilier");
		chBoxTheme.getSelectionModel().select(0);
		
		groupeErreurMax = new ToggleGroup();
		radioBtnCinq.setToggleGroup(groupeErreurMax);
		radioBtnDix.setToggleGroup(groupeErreurMax);
		radioBtnCinq.setSelected(true);
		
		groupeDifficulte = new ToggleGroup();
		radioBtnFacile.setToggleGroup(groupeDifficulte);
		radioBtnMoyen.setToggleGroup(groupeDifficulte);
		radioBtnDifficile.setToggleGroup(groupeDifficulte);
		radioBtnMoyen.setSelected(true);
	}
	
	public void valider() {
		System.out.println("Thème : " + chBoxTheme.getSelectionModel().getSelectedItem() + ", Nb max d'erreurs : " + groupeErreurMax.getSelectedToggle().toString() + ", difficulté : " + groupeDifficulte.getSelectedToggle().toString());
	}
}
