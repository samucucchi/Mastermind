package gui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneController {

	public static Stage primaryStage;
	public static Scene currentScene;
	 
	public static void start(Stage primaryStage) throws Exception {
		SceneController.primaryStage = primaryStage;
		showMainMenu();
		SceneController.primaryStage.setTitle("MasterMind");
		SceneController.primaryStage.show();
		SceneController.primaryStage.setResizable(false);
	}
	
	public static void showMainMenu() throws IOException {
		StackPane mainMenu = (StackPane)loadView("../views/MainMenu.fxml");
		SceneController.primaryStage.setScene(new Scene(mainMenu));
		SceneController.primaryStage.setResizable(false);
	}
	
	public static void showHistoryMenu() throws IOException {
		StackPane historyMenu = (StackPane)loadView("../views/HistoryMenu.fxml");
		SceneController.primaryStage.setScene(new Scene(historyMenu));
		SceneController.primaryStage.setResizable(false);
	}
	
	public static void showDifficultyMenu() throws IOException {
		StackPane difficultyMenu = (StackPane)loadView("../views/DifficultyMenu.fxml");
		SceneController.primaryStage.setScene(new Scene(difficultyMenu));
		SceneController.primaryStage.setResizable(false);
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
			gamePath = "../views/HardGame.fxml";
			break;
		default:
			throw new IOException("Board not found");
		}
		StackPane game = (StackPane)loadView(gamePath);
		SceneController.primaryStage.setScene(new Scene(game));
		SceneController.primaryStage.setResizable(false);
	}
	
	private static Parent loadView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneController.class.getResource(path));
		System.out.println("scene loaded");
		return(loader.load());
	}
}
