package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	private static Stage primaryStage;
	private Scene currentScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		//carico il main menu
		BorderPane mainMenu = FXMLLoader.load(getClass().getResource("views/MainMenu.fxml"));
		this.currentScene = new Scene(mainMenu);
		primaryStage.setScene(this.currentScene);
		primaryStage.setTitle("MasterMind");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void showDifficultyMenu() throws IOException {
		BorderPane difficultyMenu = FXMLLoader.load(Main.class.getResource("views/DifficultyMenu.fxml"));
		primaryStage.setScene(new Scene(difficultyMenu));
	}
	
}
