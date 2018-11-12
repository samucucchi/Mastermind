package gui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneCtrl {

	static final String MAIN_MENU_PATH = "../views/MainMenu.fxml";
	static final String DIFFICULTY_MENU_PATH = "../views/DifficultyMenu.fxml";
	static final String EASY_GAME_PATH = "../views/EasyGame.fxml";
	static final String NORMAL_GAME_PATH = "../views/NormalGame.fxml";
	static final String HARD_GAME_PATH = "../views/HardGame.fxml";
	
	public static Stage primaryStage;
	
	public static void start(Stage primaryStage) throws Exception {
		SceneCtrl.primaryStage = primaryStage;
		showMainMenu();
		SceneCtrl.primaryStage.setTitle("MasterMind");
		SceneCtrl.primaryStage.show();
	}
	
	public static void showMainMenu() throws IOException {
		BorderPane mainMenu = (BorderPane)loadView(MAIN_MENU_PATH);
		SceneCtrl.primaryStage.setScene(new Scene(mainMenu));
	}
	
	public static void showDifficultyMenu() throws IOException {
		BorderPane difficultyMenu = (BorderPane)loadView(DIFFICULTY_MENU_PATH);
		SceneCtrl.primaryStage.setScene(new Scene(difficultyMenu));
	}

	public static void showGame(String difficulty) throws IOException{
		String gamePath;
		GameController controller;
		switch(difficulty) {
		case "easy":
			gamePath = EASY_GAME_PATH;
			controller = new EasyGameController();
			break;
		case "normal":
			gamePath = NORMAL_GAME_PATH;
			controller = new NormalGameController();
			break;
		case "hard":
			gamePath = HARD_GAME_PATH;
			controller = new HardGameController();
			break;
		default:
			throw new IOException("Board not found");
		}
		HBox game = (HBox)loadGameView(gamePath, controller);
		SceneCtrl.primaryStage.setScene(new Scene(game));
	}
	
	private static Parent loadView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneCtrl.class.getResource(path));
		return(loader.load());
	}
	
	private static Parent loadGameView(String path, GameController controller) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(SceneCtrl.class.getResource(path));
		return(loader.load());
	}
}
