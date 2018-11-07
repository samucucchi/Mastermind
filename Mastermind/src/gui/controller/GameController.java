package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	private final int RADIUS = 25;
	private final String BUTTON_STYLE = "-fx-background-radius: 35; -fx-background-color: ";
	
	private final String WHITE_COLOR = "white";
	
	private final int SEQUENCE_NUMBER = 3;
	
	@FXML private GridPane pins;
	
	@FXML private GridPane sequence;
	
	@FXML private VBox previousSequences;
	
	@FXML
	private void selectColor(MouseEvent event) {
		Circle pinSelected = (Circle)event.getSource();
		//disablePin(pinSelected);
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle sequencePin = (Circle) sequence.getChildren().get(i);
			if (sequencePin.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequencePin.setFill(pinSelected.getFill());
				disablePin(pinSelected);
				if(i == SEQUENCE_NUMBER) {
					disableAllPins();
				}
				break;
			}
		}
	}
	
	@FXML
	private void checkSequence() {
		if(isSequenceCompleted(sequence)) {
			GridPane previousSequence = createPreviousSequence(sequence);
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			//enablePins();
		}
		else {
			System.out.println("Completare la sequenza");
		}
	}
	
	private void disablePin(Circle pin) {
		pin.setFill(Paint.valueOf(WHITE_COLOR));
	}
	
	private void disableAllPins() {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Circle pin = (Circle)pins.getChildren().get(i);
			disablePin(pin);
		}
	}
	
	private void enableButtons() {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Button pin = (Button)pins.getChildren().get(i);
			if(pin.isDisabled()) {
				//String color = getPinColor(pin);
				pin.setDisable(false);
				//pin.setStyle(BUTTON_STYLE + color);
			}
		}
	}
	
	private GridPane createPreviousSequence(GridPane sequence) {
		GridPane previousSequence = new GridPane();
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Paint pinColor = ((Circle)sequence.getChildren().get(i)).getFill();
			Circle previousPin = createSequenceCircle(pinColor);
			previousSequence.add(previousPin, i, 0);
		}
		return previousSequence;
	}
	
	private Circle createSequenceCircle(Paint color) {
		Circle circle = new Circle(RADIUS, color);
		return circle;
	}
	
	private void clearSequence(GridPane sequence) {
		Paint whiteColor = Paint.valueOf("white");
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = ((Circle)sequence.getChildren().get(i));
			pin.setFill(whiteColor);
		}
	}
	
	private boolean isSequenceCompleted(GridPane sequence) {
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = (Circle)sequence.getChildren().get(i);
			if(pin.getFill() == Paint.valueOf("white")) {
				return false;
			}
		}
		return true;
	}
	
	@FXML
	private void cerchio() {
		System.out.println("diocane");
	}
}
