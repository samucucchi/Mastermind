package gui.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.scene.control.ComboBox;

public abstract class HistoryController {
	
	protected String[] difficulty= {"Easy","Normal","Hard"};
	
	public static void initialize() {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(
				"Easy",
				"Normal",
				"Hard");
	}
	
	private void chooseDifficulty(ActionEvent event) throws IOException {
		ComboBox<String> comboBox = (ComboBox)event.getSource();
		System.out.println("User selected: " + comboBox.getValue());
	}
}