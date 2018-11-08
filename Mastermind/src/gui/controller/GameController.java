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
	
	private final String WHITE_COLOR = "white";
	
	private final int SEQUENCE_NUMBER = 3;
	
	private final int COLOR_NUMBER = 8;
	
	private String[][] pinColors = {{"red", "blue"}, {"green", "yellow"}, {"orange", "purple"}, {"brown", "black"}};
	
	@FXML private GridPane pins;
	
	@FXML private GridPane sequence;
	
	@FXML private VBox previousSequences;
	
	@FXML
	private void initialize() {
		System.out.println("ciao");
		for(int i = 0; i < COLOR_NUMBER / 2; i++) {
			for(int j = 0; j < COLOR_NUMBER / 4; j++) {
				//creazione cerchio
				Circle pin = new Circle(RADIUS, Paint.valueOf(pinColors[i][j]));
				pin.setStroke(Paint.valueOf("black"));
				pin.getProperties().put("defaultColor", pin.getFill());
				pin.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					selectColor(event);
				});
				pins.add(pin, j, i);
			}
		}
	}
	
	@FXML
	private void selectColor(MouseEvent event) {
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
	private void checkSequence() {
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
	private void removeColor(MouseEvent event) {
		Circle pinToRemove = (Circle)event.getSource();
		Paint color = pinToRemove.getFill();
		disablePin(pinToRemove);
		enablePin(color);
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
		circle.setStroke(Paint.valueOf("black"));
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
	
}
