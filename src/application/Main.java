package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
			jeu = new GestionJeu("./Dictionnaires/Dico.txt");
			option = new GestionOption();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		System.out.println(option.toString());
		jeu.AffErreurs();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc/vue/intro.fxml"));
			ControleurIntro contIntro = new ControleurIntro(jeu, option);
			loader.setController(contIntro);
			HBox root = loader.load();
			Scene scene = new Scene(root, 400, 400);

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
