package gui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneCtrl {

	public static Stage primaryStage;
	public static Scene currentScene;
	
	public static void start(Stage primaryStage) throws Exception {
		SceneCtrl.primaryStage = primaryStage;
		//carico il main menu
		BorderPane mainMenu = (BorderPane)loadView("../views/MainMenu.fxml");
		SceneCtrl.currentScene = new Scene(mainMenu);
		SceneCtrl.primaryStage.setScene(currentScene);
		SceneCtrl.primaryStage.setTitle("MasterMind");
		SceneCtrl.primaryStage.show();
	}
	
	public static void showDifficultyMenu() throws IOException {
		BorderPane difficultyMenu = (BorderPane)loadView("../views/DifficultyMenu.fxml");
		SceneCtrl.primaryStage.setScene(new Scene(difficultyMenu));
	}

	public static void showGame(String difficulty) throws IOException{
		String gamePath;
		switch(difficulty) {
		case "easy":
			gamePath = "../views/EasyGame.fxml";
			break;
		case "normal":
			gamePath = "../views/NormalGame.fxml";
			break;
		case "hard":
			gamePath = "../views/Normal.fxml";
			break;
		default:
			throw new IOException("Board not found");
		}
		HBox game = (HBox)loadView(gamePath);
		SceneCtrl.primaryStage.setScene(new Scene(game));
	}
	
	private static Parent loadView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneCtrl.class.getResource(path));
		System.out.println("caricato");
		return(loader.load());
	}
}
