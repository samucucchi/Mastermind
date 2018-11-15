package gui.controller;

import java.io.IOException;
import java.util.Optional;

import game.Master;
import game.enumerators.Colors;
import game.stats.StatsModifier;
import gui.Drawer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class GameController {

	// input pins' radius
	protected final int INPUT_PIN_RADIUS = 25;
	// input sequence's circles' radius
	protected double sequence_circle_radius;
	// previous sequences circles' radius
	protected int previous_sequence_circle_radius;
	// hintPane columns
	protected int hintPane_column_number;
	// game's sequence's length
	protected int sequence_length;
	// hintPane rows
	protected final int HINTPANE_ROW_NUMBER = 2;
	// white color protected constant
	protected final String WHITE_COLOR = "white";
	// input pins' columns
	protected final int INPUT_PINS_COLUMNS = 2;
	// input pins' rows
	protected final int INPUT_PINS_ROWS = 4;
	// Drawer class is used to prepare the game GUI
	protected Drawer drawer = new Drawer();
	// StatsModifier class is used to manage games history
	protected StatsModifier statsModifier = new StatsModifier();
	// Master class manages all the data (it is the actual project's Model)
	protected Master master;
	// matrix representing colors to be displayed in input pins
	protected String[][] pinColors = { { "red", "blue" }, { "green", "yellow" }, { "orange", "violet" },
			{ "brown", "black" } };
	// clickable input pins, used to fill the input sequence
	@FXML
	protected GridPane inputPins;
	// input sequence, used to display the current player's attemp
	protected GridPane sequence;
	// pane that contains the input sequence grid
	@FXML
	protected Pane sequenceContainer;
	// the right half of the game's view, used to display player attemps
	@FXML
	protected VBox previousSequences;

	public GameController() {
		this.drawer = new Drawer();
		this.statsModifier = new StatsModifier();
	}

	@FXML // intitializes and draws input pins and sequence grid (with circles inside)
	protected void initialize() {
		drawInputPins();
		drawSequenceGrid();
	}

	@FXML // intitializes and draws input pins
	protected void drawInputPins() {

		for (int i = 0; i < INPUT_PINS_ROWS; i++) {
			for (int j = 0; j < INPUT_PINS_COLUMNS; j++) {
				// takes color from pinColors
				Circle pin = drawer.createCircle(INPUT_PIN_RADIUS, Paint.valueOf(pinColors[i][j]));
				// adds "defaultColor" property, so that color can be restored
				pin.getProperties().put("defaultColor", pinColors[i][j]);
				// associates the method when the user clicks a pin
				pin.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					selectColor(event);
				});
				// adds the circle into the grid
				inputPins.add(pin, j, i);
			}
		}

	}

	@FXML // intitializes and draws the sequence grid (with circles inside)
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
				disablePin(event);
			});
			sequence.add(sequenceCircle, i, 0);
		}
		sequenceContainer.getChildren().add(sequence);

	}

	// When the player clicks on a pin
	// the color chosen is inserted into the first empty circle of the sequence
	@FXML
	protected void selectColor(MouseEvent event) {

		// gets the clicked circle
		Circle pinSelected = (Circle) event.getSource();
		// inserts the selected color into the first white slot of the sequence
		for (int i = 0; i < sequence_length; i++) {
			Circle sequenceSlot = (Circle) sequence.getChildren().get(i);
			if (sequenceSlot.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequenceSlot.setFill(pinSelected.getFill());
				sequenceSlot.getProperties().put("currentColor", pinSelected.getProperties().get("defaultColor"));
				break;
			}
		}

	}

	// When the player hits the check button
	// prints the sequence and the "hint grid" in the previousSequences container
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
			GridPane previousSequence = drawer.createPreviousSequence(sequence, previous_sequence_circle_radius,
					HINTPANE_ROW_NUMBER, hintPane_column_number, result);
			
			// adds the sequence to the container
			// then clears the sequence, checks if the player won
			// and checks if the player reached the attemps limit (set by difficulty)
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			// checks game "win" property which only gets changed in master.checksequence()
			if (!checkWin()) {
				// checks if game attemps reached the difficulty limit
				checkAttemps();
			};
		}

	}

	// checks if "win" attribute of game is true
	// if it is true, alerts the player that he won
	// and leads him back to the main menu
	protected boolean checkWin() throws IOException {

		if (master.getGame().getWin()) {

			// Alert box layout and "showAndWait()" function
			Alert win = new Alert(AlertType.INFORMATION);
			win.setTitle("Victory");
			win.setHeaderText(null);
			win.setContentText("You win!!");
			win.showAndWait();

			// leads the player back to main menu
			SceneController.showMenu("/gui/views/MainMenu.fxml");

			// updates stats for the history menu
			statsModifier.setStats(statsModifier.getStats(master.getGame().getDifficulty()), true,
					master.getGame().getAttempts());
			return true;
		}
		return false;

	}

	// checks "attemps" game's attibute and compares it with the difficulty limit
	// if they correspond, this functions alerts the player that he lost
	// and leads him back to the main menu
	protected void checkAttemps() throws IOException {

		if (master.getGame().getAttempts() == master.getGame().getDifficulty().getAttempts()) {

			// Alert box layout and "showAndWait()" function
			Alert lose = new Alert(AlertType.INFORMATION);
			lose.setTitle("Defeat");
			lose.setHeaderText(null);
			lose.setContentText("You loose!");
			lose.showAndWait();

			// leads the player back to main menu
			SceneController.showMenu("/gui/views/MainMenu.fxml");

			// updates stats for the history menu
			statsModifier.setStats(statsModifier.getStats(master.getGame().getDifficulty()), false,
					master.getGame().getAttempts());
		}

	}

	// after alerting the player to make sure he really wants to go back
	// if the player clicks the "ok" button, game goes back to main menu
	// TODO and updates stats as a lost (attemps = difficulty deafult attemps limit)
	@FXML
	protected void giveUp() throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to give up?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// updates stats for the history menu setting game attemps equals to difficulty limit
			statsModifier.setStats(statsModifier.getStats(master.getGame().getDifficulty()), false,
								master.getGame().getDifficulty().getAttempts());
			SceneController.showMenu("/gui/views/MainMenu.fxml");
		}

	}

	// disables the clicked pin (used for both input and sequence pins)
	protected void disablePin(MouseEvent event) {

		Circle pin = (Circle) event.getSource();
		pin.setFill(Paint.valueOf(WHITE_COLOR));

	}

	// clears the input sequence
	// painting all the circles with "white" color
	// and updating the "currentColor" property
	protected void clearSequence(GridPane sequence) {

		Paint whiteColor = Paint.valueOf("white");
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = ((Circle) sequence.getChildren().get(i));
			// paints the sequence's Circle with white color
			pin.setFill(whiteColor);
			// updates the "currentColor" Circle's property
			pin.getProperties().put("currentColor", WHITE_COLOR);
		}

	}

	// checks if every circle of the input sequence has been painted
	// returns a boolean value to communicate the result
	protected boolean isSequenceCompleted(GridPane sequence) {
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = (Circle) sequence.getChildren().get(i);
			if (pin.getFill() == Paint.valueOf("white")) {
				return false;
			}
		}
		return true;
	}

	// converts the input sequence to a "Colors" array
	// so that the model can analyze it
	protected Colors[] convertSequenceToColors() {

		// temporary Colors array which is going to represent the translated input
		// sequence
		Colors[] sequenceToCheck = new Colors[sequence_length];

		for (int i = 0; i < sequence_length; i++) {
			Circle pin = (Circle) sequence.getChildren().get(i);
			// gets currently analyzed circle's "currentColor" property
			String color = (String) pin.getProperties().get("currentColor");
			// color translation in uppercase for the enum "Colors"
			color = color.toUpperCase();
			// gets color's value and adds it to the transalted sequence
			Colors colorToCheck = Colors.valueOf(color);
			sequenceToCheck[i] = colorToCheck;
		}
		return sequenceToCheck;
	}
}
