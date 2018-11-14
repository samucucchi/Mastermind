package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.util.Optional;

import game.enumerators.Difficulty;
import game.stats.StatsModifier;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class HistoryController {

	private StatsModifier statsModifier;
	protected String[] difficulties= {"Easy","Medium","Hard"};
	
	@FXML private TextField wins;
	@FXML private TextField losses;
	@FXML private TextField average;
	@FXML private TextField best;
	@FXML private TextField worst;
	
	
	public HistoryController() {
		statsModifier = new StatsModifier();
	}
	
	@FXML
	private ComboBox<String> comboBox;
	
	public void initialize() {
		// comboBox elements reset and update
	    comboBox.getItems().clear();
	    comboBox.getItems().addAll("Easy", "Medium", "Hard");
	    // event listener for the comboBox
	    comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue<? extends String> selected, String oldDiff, String newDiff) {
	          if (newDiff != null) {
	        	  // whenever the comboBox selected item gets changed the TextFields get updated
	        	  Difficulty difficulty = Difficulty.valueOf(newDiff.toUpperCase());
	        	  updateValues(statsModifier.getStats(difficulty));
	          }
	        }
	    });
	}
	
	// goes back to main menu
	@FXML
	private void goToMenu() throws IOException {
		SceneController.showMenu("/gui/views/MainMenu.fxml");
	}
	
	//resets games history
	@FXML
	private void resetHistory() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("History reset");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete all your games history?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			statsModifier.resetStats();
			SceneController.showMenu("/gui/views/HistoryMenu.fxml");
		}
	}
	
	// updates TextFields values based on the difficulty comboBox selected item
	private void updateValues(int[] stats) {
		wins.setText(Integer.toString(stats[0]));
		losses.setText(Integer.toString(stats[1]));
		average.setText(Integer.toString(stats[2]));
		best.setText(Integer.toString(stats[3]));
		worst.setText(Integer.toString(stats[4]));
	}
}