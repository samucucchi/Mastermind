package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	final int RADIUS = 25;
	final String BUTTON_STYLE = "-fx-background-radius: 35; -fx-background-color: ";
	
	@FXML private GridPane pins;
	
	@FXML private GridPane sequence;
	
	@FXML private VBox previousSequences;
	
	@FXML
	private void selectColor(ActionEvent event) {
		Button selected = (Button)event.getSource();
		String color = getPinColor(selected);
		disableButton(selected);
		System.out.println(selected.getId());
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = (Circle)sequence.getChildren().get(i);
			if(pin.getFill() == Paint.valueOf("white")) {
				pin.setFill(Paint.valueOf(color));
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
			enableButtons();
		}
		else {
			System.out.println("Completare la sequenza");
		}
	}
	
	private void disableButton(Button button) {
		button.setStyle(BUTTON_STYLE + "white");
		button.setDisable(true);
	}
	
	private void enableButtons() {
		for(int i = 0; i < pins.getChildren().size(); i++) {
			Button pin = (Button)pins.getChildren().get(i);
			if(pin.isDisabled()) {
				String color = getPinColor(pin);
				pin.setDisable(false);
				pin.setStyle(BUTTON_STYLE + color);
			}
		}
	}
	
	private String getPinColor(Button button) {
		switch(button.getId()) {
		case "redPin":
			return "red";
		case "bluePin":
			return "blue";
		case "greenPin":
			return "green";
		case "yellowPin":
			return "yellow";
		case "orangePin":
			return "orange";
		case "purplePin":
			return "purple";
		case "brownPin":
			return "brown";
		case "blackPin":
			return "black";
		default:
			return null;
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
}
