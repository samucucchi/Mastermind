package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;

import game.enumerators.Difficulty;
import game.stats.StatsModifier;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
	        	  System.out.println(difficulty);
	        	  updateValues(statsModifier.getStats(difficulty));
	          }
	        }
	    });
	}
	
	// goes back to main menu
	@FXML
	protected void goToMenu() throws IOException {
		SceneController.showMenu("../views/MainMenu.fxml");
	}
	
	// updates TextFields values based on the difficulty comboBox selected item
	public void updateValues(double[] stats) {
		wins.setText(Double.toString(stats[0]));
		losses.setText(Double.toString(stats[1]));
		average.setText(Double.toString(stats[2]));
		best.setText(Double.toString(stats[3]));
		worst.setText(Double.toString(stats[4]));
	}
}