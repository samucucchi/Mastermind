package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	final int RADIUS = 30;
	@FXML private GridPane pins;
	
	@FXML private GridPane sequence;
	
	@FXML private VBox previousSequences;
	
	@FXML
	private void selectColor(ActionEvent event) {
		String id = ((Button)event.getSource()).getId();
		String color = null;
		
		System.out.println(id);
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
		}
		else {
			System.out.println("Completare la sequenza");
		}
	}
	
	private void disablePin(Button pin) {
		pin.setStyle("-fx-background-color: white");
		pin.setDisable(true);
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
