package gui.controller;

import java.io.IOException;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuCtrl {

	//annotazione per il file fxml
	@FXML
	private void play(ActionEvent event) throws IOException {
		System.out.println("ciao");
		Main.showDifficultyMenu();
	}
	
	@FXML
	private void showHistory(ActionEvent event) {
		
	}
}
