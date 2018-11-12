package gui.controller;

import java.io.IOException;

import game.Master;
import game.enumerators.Difficulty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import player.Player;

public class EasyGameController extends GameController{

	final double SEQUENCE_RADIUS = 31.25;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	final int SEQUENCE_LENGTH = 4;
	
	public EasyGameController() {
		super.master = new Master(Difficulty.EASY, new Player("ciao"));
		super.sequenceCircleRadius = this.SEQUENCE_RADIUS;
		super.previousSequenceCircleRadius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
	@FXML
	protected void selectColor(MouseEvent event) {
		Circle pinSelected = (Circle)event.getSource();
		for (int i = 0; i < SEQUENCE_LENGTH; i++) {
			Circle sequenceSlot = (Circle) sequence.getChildren().get(i);
			if (sequenceSlot.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequenceSlot.setFill(pinSelected.getFill());
				sequenceSlot.getProperties().put("currentColor", pinSelected.getProperties().get("defaultColor"));
				disablePin(pinSelected);
				break;
			}
		}
	}
	
	@FXML
	protected void checkSequence() throws IOException {
		if(isSequenceCompleted(sequence)) {
			int[] result = master.checkSequence(convertToColors());
			GridPane previousSequence = drawer.createPreviousSequence(sequence, PREVIOUS_SEQUENCE_RADIUS, HINTPANE_ROW_NUMBER, HINTPANE_COLUMN_NUMBER, result);
			previousSequence.setHgap(5);
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			enableAllPins();
			if(master.getGame().getWin()) {
				Alert win = new Alert(AlertType.INFORMATION);
				win.setTitle("Il coglione ha vinto!");
				win.setHeaderText(null);
				win.setContentText("Congratz! You won the mongolino d'oro!");

				win.showAndWait();
				SceneController.showMainMenu();
			}
			else if(!(master.getGame().getWin()) && master.getGame().getAttempts() == master.getGame().getDifficulty().getAttempts()){
				Alert lose = new Alert(AlertType.INFORMATION);
				lose.setTitle("Il coglione ha perso, surclassato, morto!");
				lose.setHeaderText(null);
				lose.setContentText("Congratz! YOU DIED!");

				lose.showAndWait();
				SceneController.showMainMenu();
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sequence error");
			alert.setHeaderText(null);
			alert.setContentText("Please, complete the sequence THEN press Check button.");

			alert.showAndWait();
		}
	}
	
	@FXML
	protected void removeColor(MouseEvent event) {
		Circle pinToRemove = (Circle)event.getSource();
		Paint color = pinToRemove.getFill();
		disablePin(pinToRemove);
		enablePin(color);
	}
	
	private void enableAllPins() {
		for(int i = 0; i < inputPins.getChildren().size(); i++) {
			Circle currentCircle = (Circle)inputPins.getChildren().get(i);
			String defaultColor = (String)currentCircle.getProperties().get("defaultColor");
			Paint color = Paint.valueOf(defaultColor);
			currentCircle.setFill(color);
		}
	}
	
}
