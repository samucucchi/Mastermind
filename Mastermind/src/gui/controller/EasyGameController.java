package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class EasyGameController extends GameController{

	final double SEQUENCE_RADIUS = 31.25;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	final int SEQUENCE_LENGTH = 4;
	
	public EasyGameController() {
		super.sequenceCircleRadius = this.SEQUENCE_RADIUS;
		super.previousSequenceCircleRadius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
	@FXML
	protected void selectColor(MouseEvent event) {
		Circle pinSelected = (Circle)event.getSource();
		//disablePin(pinSelected);
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle sequencePin = (Circle) sequence.getChildren().get(i);
			if (sequencePin.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequencePin.setFill(pinSelected.getFill());
				disablePin(pinSelected);
				break;
			}
		}
	}
	
	@FXML
	protected void checkSequence() {
		if(isSequenceCompleted(sequence)) {
			GridPane previousSequence = createPreviousSequence(sequence);
			previousSequence.setHgap(5);
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			enableAllPins();
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
			Paint defaultColor = (Paint)currentCircle.getProperties().get("defaultColor");
			currentCircle.setFill(defaultColor);
		}
	}
	
}
