package mvc.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurParametres {
	private GestionJeu jeu;
	private GestionOption option;
	private ToggleGroup groupeErreurMax, groupeDifficulte;

	@FXML
	ChoiceBox<String> chBoxTheme;

	@FXML
	private RadioButton radioBtnCinq, radioBtnDix, radioBtnFacile, radioBtnMoyen, radioBtnDifficile;

	public ControleurParametres(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void initialize() {
		chBoxTheme.getItems().addAll("Tous les thèmes", "Animal", "Informatique", "Espace", "Nature", "Mobilier");
		chBoxTheme.getSelectionModel().select(option.getTheme());

		groupeErreurMax = new ToggleGroup();
		radioBtnCinq.setToggleGroup(groupeErreurMax);
		radioBtnDix.setToggleGroup(groupeErreurMax);

		if (jeu.getNbMaxErreurs() == 5)
			groupeErreurMax.selectToggle(radioBtnCinq);
		else
			groupeErreurMax.selectToggle(radioBtnDix);

		groupeDifficulte = new ToggleGroup();
		radioBtnFacile.setToggleGroup(groupeDifficulte);
		radioBtnMoyen.setToggleGroup(groupeDifficulte);
		radioBtnDifficile.setToggleGroup(groupeDifficulte);

		if (option.getDifficulte().equals("Facile"))
			groupeDifficulte.selectToggle(radioBtnFacile);
		else if (option.getDifficulte().equals("Moyen"))
			groupeDifficulte.selectToggle(radioBtnMoyen);
		else
			groupeDifficulte.selectToggle(radioBtnDifficile);
	}

	public void valider() {
		option.setTheme(chBoxTheme.getSelectionModel().getSelectedItem());
		option.setDifficulte(getDifficulte());
		jeu.setNbMaxErreurs(getNbMaxErreurs());
	}

	public int getNbMaxErreurs() {
		if (groupeErreurMax.getSelectedToggle().equals(radioBtnCinq))
			return 5;
		else
			return 10;
	}

	public String getDifficulte() {
		if (groupeDifficulte.getSelectedToggle().equals(radioBtnFacile))
			return "Facile";
		else if (groupeDifficulte.getSelectedToggle().equals(radioBtnMoyen))
			return "Moyen";
		else
			return "Difficile";
	}
}
