package gui.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneController {

	public static Stage primaryStage;
	
	// starts the application showing the main menu
	// every window is unresizable and has title "Mastermind"
	public static void start(Stage pStage) throws Exception {
		primaryStage = pStage;
		showMenu("../views/MainMenu.fxml");
		primaryStage.setTitle("MasterMind");
		primaryStage.show();
		primaryStage.setResizable(false);
		// avoids first scene extra-margin due to setResizable(false) bug;
		primaryStage.sizeToScene();
	}
	
	// shows and loads a specific menu passed as function's argument
	public static void showMenu(String path) throws IOException {
		StackPane mainMenu = (StackPane)loadMenuView(path);
		primaryStage.setScene(new Scene(mainMenu));
	}
	
	// shows the game view, loads it and sets it's controller,
	// based on the "difficulty" function's arguments
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
		primaryStage.setScene(new Scene(game));
	}
	
	// loads a new menu view
	private static Parent loadMenuView(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneController.class.getResource(path));
		return(loader.load());
	}
	
	// loads a new game view and sets it's controller
	private static Parent loadGameView(String path, GameController controller) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(SceneController.class.getResource(path));
		return(loader.load());
	}
}
