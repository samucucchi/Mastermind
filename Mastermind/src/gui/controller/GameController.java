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
	@FXML private Button redPin;
	@FXML private Button bluePin;
	@FXML private Button greenPin;
	@FXML private Button yellowPin;
	@FXML private Button orangePin;
	@FXML private Button purplePin;
	@FXML private Button brownPin;
	@FXML private Button blackPin;
	
	@FXML private GridPane sequence;
	
	@FXML private VBox previousSequences;
	
	@FXML
	private void selectColor(ActionEvent event) {
		String id = ((Button)event.getSource()).getId();
		String color = null;
		switch(id) {
		case "redPin":
			color = "red";
			disablePin(redPin);
			break;
		case "bluePin":
			color = "blue";
			disablePin(bluePin);
			break;
		case "greenPin":
			color = "green";
			disablePin(greenPin);
			break;
		case "yellowPin":
			color = "yellow";
			disablePin(yellowPin);
			break;
		case "orangePin":
			color = "orange";
			disablePin(orangePin);
			break;
		case "purplePin":
			color = "purple";
			disablePin(purplePin);
			break;
		case "brownPin":
			color = "brown";
			disablePin(brownPin);
			break;
		case "blackPin":
			color = "black";
			disablePin(blackPin);
			break;
		default:
			System.out.println("Color not recognized");
			break;
		}
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
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = (Circle)sequence.getChildren().get(i);
			if(pin.getFill() == Paint.valueOf("white")) {
				System.out.println("Completare la sequenza");
				return;
			}
		}
		GridPane previousSequence = createPreviousSequence(sequence);
		previousSequences.getChildren().add(previousSequence);
		clearSequence(sequence);
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
}
