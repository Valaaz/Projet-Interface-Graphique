package mvc.controleur;

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
	
	if(option.getSkinPendu() == 0) {
		groupeSkin.selectToggle(radioBtnSkinSimple);
	}
	else {
		groupeSkin.selectToggle(radioBtnSkinAvance);
	}
	
	if(option.isModeSombre()) {
		cBoxSombre.setSelected(true);
	}
	else {
		cBoxSombre.setSelected(false);
	}
	
	switch(option.getTaillePolice()) {
	case 0:
		sliderPolice.setValue(0);
		break;
	case 1:
		sliderPolice.setValue(1);
		break;
	case 2:
		sliderPolice.setValue(2);
		break;
	case 3:
		sliderPolice.setValue(3);
		break;
	case 4:
		sliderPolice.setValue(4);
		break;
	case 5:
		sliderPolice.setValue(5);
		break;
	default:
		sliderPolice.setValue(3);
		break;
	}
	
	if(!jeu.getNomJoueur().isEmpty()) {
		textPseudo.setText(jeu.getNomJoueur());
	}
	else {
		textPseudo.setText("Joueur1");
	}
}

public void setLabelPolice() {
	switch((int)sliderPolice.getValue()) {
	case 0:
		lblTaillePolice.setFont(new Font("System", 12));
	case 1:
		lblTaillePolice.setFont(new Font("System", 14));
	case 2:
		lblTaillePolice.setFont(new Font("System", 16));
	case 3:
		lblTaillePolice.setFont(new Font("System", 18));
	case 4:
		lblTaillePolice.setFont(new Font("System", 20));
	case 5:
		lblTaillePolice.setFont(new Font("System", 22));
	default:
		lblTaillePolice.setFont(new Font("System", 12));
	}
}

public void valider() {
	option.setTaillePolice((int) sliderPolice.getValue());
	option.setModeSombre(getModeSombre());
	option.setSkinPendu(getSkin());
	jeu.setNomJoueur(textPseudo.getText());
}

public boolean getModeSombre(){
	if(cBoxSombre.isSelected()) {
		return true;
	}
	else {
		return false;
	}
}

public int getSkin() {
	if(groupeSkin.getSelectedToggle().equals(radioBtnSkinSimple)) {
		return 0;
	}
	else {
		return 1;
	}
}


}
