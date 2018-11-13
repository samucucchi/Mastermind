package gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {

	// goes to the difficulty menu
	@FXML
	private void play(ActionEvent event) throws IOException {
		SceneController.showMenu("../views/DifficultyMenu.fxml");
	}
	
	// goes to the history menu
	@FXML
	private void showHistory(ActionEvent event) throws IOException{
		SceneController.showMenu("../views/HistoryMenu.fxml");
	}
	
}
