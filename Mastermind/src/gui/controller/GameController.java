package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class GameController {

	protected final int RADIUS = 25;
	
	protected final String WHITE_COLOR = "white";
	
	protected final int COLOR_NUMBER = 8;
	
	protected String[][] pinColors = {{"red", "blue"}, {"green", "yellow"}, {"orange", "purple"}, {"brown", "black"}};
	
	@FXML protected GridPane pins;
	
	@FXML protected GridPane sequence;
	
	@FXML protected VBox previousSequences;
	
	@FXML
	protected void initialize() {
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
	protected void selectColor(MouseEvent event) {
		Circle pinSelected = (Circle)event.getSource();
		//disablePin(pinSelected);
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle sequencePin = (Circle) sequence.getChildren().get(i);
			if (sequencePin.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequencePin.setFill(pinSelected.getFill());
				//disablePin(pinSelected);
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
			//enableAllPins();
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
	}
	
	protected void disablePin(Circle pin) {
		pin.setFill(Paint.valueOf(WHITE_COLOR));
	}
	
	protected GridPane createPreviousSequence(GridPane sequence) {
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
	
	protected void clearSequence(GridPane sequence) {
		Paint whiteColor = Paint.valueOf("white");
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = ((Circle)sequence.getChildren().get(i));
			pin.setFill(whiteColor);
		}
	}
	
	protected boolean isSequenceCompleted(GridPane sequence) {
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = (Circle)sequence.getChildren().get(i);
			if(pin.getFill() == Paint.valueOf("white")) {
				return false;
			}
		}
		return true;
	}
}
