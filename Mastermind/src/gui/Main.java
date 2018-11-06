package gui;

import java.io.IOException;

import gui.controller.SceneCtrl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneCtrl.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
