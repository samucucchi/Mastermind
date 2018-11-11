package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class EasyGameController extends GameController{

	final int RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	public EasyGameController() {
		super.RADIUS = this.RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
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
			System.out.println("Completare la sequenza");
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
