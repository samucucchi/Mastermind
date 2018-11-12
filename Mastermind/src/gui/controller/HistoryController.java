package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;

public class HistoryController {

	protected String[] difficulties= {"Easy","Medium","Hard"};
	@FXML
	private ComboBox<String> comboBox;
	
	public void initialize() {
		// comboBox elements resest and update
	    comboBox.getItems().clear();
	    comboBox.getItems().addAll("Easy", "Medium", "Hard");
	    // event listener for the comboBox
	    // TODO change println with actual code!!!
	    comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue<? extends String> selected, String oldDiff, String newDiff) {
	          if (newDiff != null) {
	        	  System.out.println("User selected: " + newDiff);
	          }
	        }
	    });
	}
	
	@FXML
	protected void goToMenu() throws IOException {
		SceneController.showMainMenu();
	}
	
}