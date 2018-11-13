package gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {

	//annotazione per il file fxml
	@FXML
	private void play(ActionEvent event) throws IOException {
		SceneController.showMenu("../views/DifficultyMenu.fxml");
	}
	
	@FXML
	private void showHistory(ActionEvent event) throws IOException{
		SceneController.showMenu("../views/HistoryMenu.fxml");
	}
}
