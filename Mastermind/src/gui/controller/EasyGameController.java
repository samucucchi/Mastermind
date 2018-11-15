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

	// subClass final attributes
	final double SEQUENCE_CIRCLE_RADIUS = 31.25;
	final int PREVIOUS_SEQUENCE_CIRCLE_RADIUS = 25;
	final int HINTPANE_COLUMN_NUMBER = 2;
	final int SEQUENCE_LENGTH = 4;

	public EasyGameController() {
		// EasyGameController's superclass (i.e. GameController) attributes override;
		super();
		super.master = new Master(Difficulty.EASY);
		super.sequence_circle_radius = this.SEQUENCE_CIRCLE_RADIUS;
		super.previous_sequence_circle_radius = this.PREVIOUS_SEQUENCE_CIRCLE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}

	@Override // now uses "removeColor" instead of "disablePin"
	@FXML
	public void drawSequenceGrid() {
		
		// sequence's circles dimension adaptation to the length of the game's sequence
		double dimension = 250 / sequence_length;
		
		// creates the grid which visually represents the input sequence
		sequence = drawer.createGrid(1, sequence_length, dimension);
		 
		// draws circles, based on the length of the games' sequence
		// and adds the to the sequence, visually represented by the grid
		// each circle has white as default color and can be clicked
		for (int i = 0; i < sequence_length; i++) {
			Circle sequenceCircle = drawer.createCircle(sequence_circle_radius, Paint.valueOf(WHITE_COLOR));
			sequenceCircle.getProperties().put("currentColor", "white");
			sequenceCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				// disables the clicked sequence circle
				// and re-enables the input pin representing that color
				removeColor(event);
			});
			sequence.add(sequenceCircle, i, 0);
		}
		sequenceContainer.getChildren().add(sequence);
	}

	
	// disables the clicked sequence circle
	// and re-enables the input pin representing that color
	@FXML
	protected void removeColor(MouseEvent event) {
		Circle pinToRemove = (Circle) event.getSource();
		Paint color = pinToRemove.getFill();
		disablePin(event);
		enablePin(color);
	}
	
	// When the player clicks on a pin, if the sequence is not already completed
	// the color chosen is inserted into the first empty circle of the sequence
	@Override // now also disables the input pin
	@FXML
	protected void selectColor(MouseEvent event) {
		if(!isSequenceCompleted(sequence)) {
			super.selectColor(event);
			disablePin(event);
		}
	}
	
	// When the player hits the check button
	// prints the sequence and the "hint grid" in the previousSequences container
	@Override // now uses "enableAllPins()" if sequence is completed
	@FXML
	protected void submitSequence() throws IOException {
		if (!(isSequenceCompleted(sequence))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sequence error");
			alert.setHeaderText(null);
			alert.setContentText("Please, complete the sequence THEN press Check button.");
			alert.showAndWait();
		} else {
			// gets a copy of the sequence and converts sequence to enums(Colors)
			int[] result = master.checkSequence(convertSequenceToColors());
			GridPane previousSequence = drawer.createPreviousSequence(
					sequence, previous_sequence_circle_radius,
					HINTPANE_ROW_NUMBER, hintPane_column_number, result);
			// adds the sequence to the container
			// then clears the sequence and re-enables all the input pins
			// then checks if the player won
			// and checks if the player reached the attemps limit (set by difficulty)
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			enableAllPins();
			// checks game "win" property which only gets changed in master.checksequence()
			if (!checkWin()) {
				// checks if game attemps reached the difficulty limit
				checkAttemps();
			};
		}
	}

	// enables single input pin
	protected void enablePin(Paint color) {
		for (int i = 0; i < inputPins.getChildren().size(); i++) {
			// gets currently analized input pin
			Circle currentCircle = (Circle) inputPins.getChildren().get(i);
			// gets currently analized input pin's "defaultColor" property (see Circle's
			// property)
			String defaultColor = (String) currentCircle.getProperties().get("defaultColor");
			if (color == Paint.valueOf(defaultColor)) {
				currentCircle.setFill(color);
			}
		}
	}

	// enables all input pins
	private void enableAllPins() {
		for (int i = 0; i < inputPins.getChildren().size(); i++) {
			Circle currentCircle = (Circle) inputPins.getChildren().get(i);
			String defaultColor = (String) currentCircle.getProperties().get("defaultColor");
			Paint color = Paint.valueOf(defaultColor);
			currentCircle.setFill(color);
		}
	}

}
