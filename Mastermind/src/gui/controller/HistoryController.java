package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
	    comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue<? extends String> selected, String oldDiff, String newDiff) {
	          if (newDiff != null) {
	        	  System.out.println("User selected: " + newDiff);
	          }
	        }
	    });
	}

	/*
	@FXML
	protected void chooseDifficulty(ActionEvent event) throws IOException {
		ComboBox<String> comboBox= ((ComboBox<String>)event.getSource());
		String difficulty = comboBox.getValue();
		System.out.println("User selected: " + difficulty);
	}
	
	*/
	
	@FXML
	protected void goToMenu() throws IOException {
		SceneController.showMainMenu();
	}
	
}