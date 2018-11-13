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

public class EasyGameController extends GameController {

	final double SEQUENCE_CIRCLE_RADIUS = 31.25;

	final int PREVIOUS_SEQUENCE_CIRCLE_RADIUS = 25;

	final int HINTPANE_COLUMN_NUMBER = 2;

	final int SEQUENCE_LENGTH = 4;

	public EasyGameController() {
		// EasyGameController's superclass (i.e. GameController) attributes override;
		super.master = new Master(Difficulty.EASY);
		super.sequence_circle_radius = this.SEQUENCE_CIRCLE_RADIUS;
		super.previous_sequence_circle_radius = this.PREVIOUS_SEQUENCE_CIRCLE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}

	@FXML
	public void drawSequenceGrid() {
		double dimension = 250/sequence_length;
		sequence = drawer.createGrid(1, sequence_length, dimension);
		for(int i = 0; i < sequence_length; i++) {
			Circle sequenceCircle = drawer.createCircle(sequence_circle_radius, Paint.valueOf(WHITE_COLOR));
			sequenceCircle.getProperties().put("currentColor", "white");
			sequenceCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				removeColor(event);
			});
			sequence.add(sequenceCircle, i, 0);
		}
		sequenceContainer.getChildren().add(sequence);
	}
	
	@FXML
	protected void removeColor(MouseEvent event) {
		Circle pinToRemove = (Circle) event.getSource();
		Paint color = pinToRemove.getFill();
		disablePin(event);
		enablePin(color);
	}
	
	@FXML
	protected void selectColor(MouseEvent event) {
		super.selectColor(event);		
		disablePin(event);
	}

	@FXML
	protected void checkSequence() throws IOException {
		if(!(isSequenceCompleted(sequence))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sequence error");
			alert.setHeaderText(null);
			alert.setContentText("Please, complete the sequence THEN press Check button.");
			alert.showAndWait();
			}
		else {
			/*gets a copy of the sequence*/
			//convert sequence to enums
			int[] result = master.checkSequence(convertToColors());
			GridPane previousSequence = drawer.createPreviousSequence(sequence, previous_sequence_circle_radius, HINTPANE_ROW_NUMBER, hintPane_column_number, result);
			/*adds the sequence into the container*/
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			checkWin();
			checkAttemps();
		}
	}
	
	protected void enablePin(Paint color) {
		for(int i = 0; i < inputPins.getChildren().size(); i++) {
			Circle currentCircle = (Circle)inputPins.getChildren().get(i);
			String defaultColor = (String)currentCircle.getProperties().get("defaultColor");
			if(color == Paint.valueOf(defaultColor)) {
				currentCircle.setFill(color);
			}
		}
	}
	
	private void enableAllPins() {
		for (int i = 0; i < inputPins.getChildren().size(); i++) {
			Circle currentCircle = (Circle) inputPins.getChildren().get(i);
			String defaultColor = (String) currentCircle.getProperties().get("defaultColor");
			Paint color = Paint.valueOf(defaultColor);
			currentCircle.setFill(color);
		}
	}

}
