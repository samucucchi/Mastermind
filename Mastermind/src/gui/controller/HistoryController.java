package gui.controller;


import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;

public class HistoryController {

	protected String[] difficulties= {"Easy","Normal","Hard"};
	@FXML
	private ComboBox<String> comboBox;
	
	public void initialize() {
	    comboBox.getItems().clear();
	    comboBox.getItems().addAll("Easy", "Normal", "Hard");
	}

	
	@FXML
	protected void chooseDifficulty(ActionEvent event) throws IOException {
		String difficulty = "";
		System.out.println("User selected: " + difficulty);
	}
	
	
	@FXML
	protected void goToMenu() throws IOException {
		SceneController.showMainMenu();
	}
	
}