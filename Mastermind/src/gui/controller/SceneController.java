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
		String gamePath = "../views/Game.fxml";
		GameController controller;
		switch(difficulty) {
		case "easy":
			controller = new EasyGameController();
			break;
		case "normal":
			controller = new MediumGameController();
			break;
		case "hard":
			controller = new HardGameController();
			break;
		default:
			throw new IOException("Board not found");
		}
		StackPane game = (StackPane)loadGameView(gamePath, controller);
		SceneController.primaryStage.setScene(new Scene(game));
		SceneController.primaryStage.setResizable(false);
	}
	
	
	private static Parent loadView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneController.class.getResource(path));
		return(loader.load());
	}
	
	private static Parent loadGameView(String path, GameController controller) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(SceneController.class.getResource(path));
		return(loader.load());
	}
}
