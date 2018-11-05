package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneCtrl extends Application{

	private Stage primaryStage;
	private Scene currentScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		//carico il main menu
		BorderPane mainMenu = FXMLLoader.load(getClass().getResource("../views/MainMenu.fxml"));
		this.currentScene = new Scene(mainMenu);
		this.primaryStage.setScene(this.currentScene);
		this.primaryStage.setTitle("MasterMind");
		this.primaryStage.show();
	}
	
	public void showDifficultyMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/DifficultyMenu.fxml"));
		System.out.println("Eheh");
		BorderPane difficultyMenu = loader.load();
		//this.currentScene = new Scene(difficultyMenu);
		//this.primaryStage.setScene(new Scene(difficultyMenu));
		//this.primaryStage.show();
	}

	//metodo per cominciare la gui
	public void begin(String[] args) {
		launch(args);
	}
}
