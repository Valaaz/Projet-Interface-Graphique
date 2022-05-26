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
	private CheckBox cBoxSombre;

	@FXML
	private Slider sliderPolice;

	@FXML
	private TextField textPseudo;

	@FXML
	private Label lblTaillePolice;

	@FXML
	private RadioButton radioBtnSkinSimple, radioBtnSkinAvance;

	private final static int LIMIT = 20;

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
		default:
			sliderPolice.setValue(12);
			break;
		}

		if (!jeu.getNomJoueur().isEmpty()) {
			textPseudo.setText(jeu.getNomJoueur().trim());
		} else {
			textPseudo.setPromptText("Entrez un pseudo");
		}
		sliderPolice.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				lblTaillePolice.setFont(new Font("System", (double) newValue));
			}
		});
		textPseudo.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					// verifie si le pseudo est superieur à la limite
					if (textPseudo.getText().length() >= LIMIT) {
						// si ça depasse on set le texte avec 20 caraceères
						textPseudo.setText(textPseudo.getText().substring(0, LIMIT));
					}
				}
			}
		});
	}

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

	public boolean nomJoueurEstVide() {
		if (textPseudo.getText().trim() == "") {
			return true;
		} else {
			return false;
		}
	}

}
