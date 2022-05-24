package mvc.controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class ControleurPreferences {
	private GestionJeu jeu;
	private GestionOption option;
	private ToggleGroup groupeSkin;

	@FXML
	CheckBox cBoxSombre;

	@FXML
	Slider sliderPolice;

	@FXML
	TextField textPseudo;

	@FXML
	Label lblTaillePolice;

	@FXML
	private RadioButton radioBtnSkinSimple, radioBtnSkinAvance;

	public ControleurPreferences(GestionJeu jeu, GestionOption option) {
		super();
		this.jeu = jeu;
		this.option = option;
	}

	@FXML
	public void initialize() {
		groupeSkin = new ToggleGroup();
		radioBtnSkinSimple.setToggleGroup(groupeSkin);
		radioBtnSkinAvance.setToggleGroup(groupeSkin);

		if (option.getSkinPendu() == 0) {
			groupeSkin.selectToggle(radioBtnSkinSimple);
		} else {
			groupeSkin.selectToggle(radioBtnSkinAvance);
		}

		if (option.isModeSombre()) {
			cBoxSombre.setSelected(true);
		} else {
			cBoxSombre.setSelected(false);
		}

		switch (option.getTaillePolice()) {
		case 12:
			sliderPolice.setValue(12);
			break;
		case 14:
			sliderPolice.setValue(14);
			break;
		case 16:
			sliderPolice.setValue(16);
			break;
		case 18:
			sliderPolice.setValue(18);
			break;
		case 20:
			sliderPolice.setValue(20);
			break;
		case 22:
			sliderPolice.setValue(22);
			break;
		default:
			sliderPolice.setValue(12);
			break;
		}

		if (!jeu.getNomJoueur().isEmpty()) {
			textPseudo.setText(jeu.getNomJoueur());
		} else {
			textPseudo.setText("Joueur1");
		}

		sliderPolice.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				lblTaillePolice.setFont(new Font("System", (double) newValue));
			}
		});
	}

	/*
	 * public void setLabelPolice() { switch((int)sliderPolice.getValue()) { case
	 * 12: lblTaillePolice.setFont(new Font("System", 12)); case 14:
	 * lblTaillePolice.setFont(new Font("System", 14)); case 16:
	 * lblTaillePolice.setFont(new Font("System", 16)); case 18:
	 * lblTaillePolice.setFont(new Font("System", 18)); case 20:
	 * lblTaillePolice.setFont(new Font("System", 20)); case 22:
	 * lblTaillePolice.setFont(new Font("System", 22)); default:
	 * lblTaillePolice.setFont(new Font("System", 12)); } }
	 */

	public void valider() {
		option.setTaillePolice((int) sliderPolice.getValue());
		option.setModeSombre(getModeSombre());
		option.setSkinPendu(getSkin());
		jeu.setNomJoueur(textPseudo.getText());
	}

	public boolean getModeSombre() {
		if (cBoxSombre.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public int getSkin() {
		if (groupeSkin.getSelectedToggle().equals(radioBtnSkinSimple)) {
			return 0;
		} else {
			return 1;
		}
	}

}
