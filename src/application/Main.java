package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mvc.controleur.ControleurIntro;
import mvc.modele.GestionJeu;
import mvc.modele.GestionOption;

public class Main extends Application {
	private GestionJeu jeu;
	private GestionOption option;

	@Override
	public void init() {
		try {
			jeu = new GestionJeu("./dictionnaires/Tous les themes.txt");
			option = new GestionOption();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/intro.fxml"));
			ControleurIntro contIntro = new ControleurIntro(jeu, option);
			loader.setController(contIntro);
			GridPane root = loader.load();
			Scene scene = new Scene(root);

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

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
