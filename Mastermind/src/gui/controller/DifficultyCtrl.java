package gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DifficultyCtrl {

	@FXML
	private void startGame(ActionEvent event) throws IOException {
		//prendo l'id del bottone
		String difficulty = ((Button)event.getSource()).getId();
		System.out.println(difficulty);
		SceneCtrl.showGame();
	}
}
