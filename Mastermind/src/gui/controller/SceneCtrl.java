package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneCtrl {

	public static Stage primaryStage;
	public static Scene currentScene;
	
	public static void start(Stage primaryStage) throws Exception {
		SceneCtrl.primaryStage = primaryStage;
		//carico il main menu
		BorderPane mainMenu = FXMLLoader.load(SceneCtrl.class.getResource("../views/MainMenu.fxml"));
		SceneCtrl.currentScene = new Scene(mainMenu);
		SceneCtrl.primaryStage.setScene(currentScene);
		SceneCtrl.primaryStage.setTitle("MasterMind");
		SceneCtrl.primaryStage.show();
	}
	
	public static void showDifficultyMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneCtrl.class.getResource("../views/DifficultyMenu.fxml"));
		System.out.println("Eheh");
		BorderPane difficultyMenu = loader.load();
		SceneCtrl.primaryStage.setScene(new Scene(difficultyMenu));
	}

}
