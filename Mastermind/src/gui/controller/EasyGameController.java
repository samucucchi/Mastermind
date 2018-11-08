package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class EasyGameController extends GameController{

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
			System.out.println("Completare la sequenza");
		}
	}
	
	
	
	private void disableAllPins() {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Circle pin = (Circle)pins.getChildren().get(i);
			disablePin(pin);
		}
	}
	
	private void enablePin(Paint color) {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Circle currentCircle = (Circle)pins.getChildren().get(i);
			Paint defaultColor = (Paint)currentCircle.getProperties().get("defaultColor");
			if(color == defaultColor) {
				currentCircle.setFill(color);
			}
		}
	}
	
	private void enableAllPins() {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Circle currentCircle = (Circle)pins.getChildren().get(i);
			Paint defaultColor = (Paint)currentCircle.getProperties().get("defaultColor");
			currentCircle.setFill(defaultColor);
		}
	}
	
}
