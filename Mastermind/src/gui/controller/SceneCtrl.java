package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
		HBox game = (HBox) loadView("../views/Game.fxml");
		String boardPath;
		switch(difficulty) {
		case "easy":
			boardPath = "../views/Easy.fxml";
			break;
		case "normal":
			boardPath = "../views/Normal.fxml";
			break;
		case "hard":
			boardPath = "../views/Normal.fxml";
			break;
		default:
			throw new IOException("Board not found");
		}
		ScrollPane board = (ScrollPane)loadView(boardPath);
		game.getChildren().add(board);
		SceneCtrl.primaryStage.setScene(new Scene(game));
	}
	
	private static Parent loadView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneCtrl.class.getResource(path));
		return(loader.load());
	}
}
