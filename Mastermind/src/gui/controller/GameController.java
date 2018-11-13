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

	/*input pin radius is fixed*/
	protected final int INPUT_PIN_RADIUS = 25;
	
	protected double sequence_circle_radius;
	
	protected int previous_sequence_circle_radius;
	
	protected int hintPane_column_number;
	
	protected int sequence_length;
	
	protected final int HINTPANE_ROW_NUMBER = 2;
	
	protected final String WHITE_COLOR = "white";
			
	protected final int INPUT_PINS_COLUMNS = 2;
	
	protected final int INPUT_PINS_ROWS = 4;
	
	protected Drawer drawer = new Drawer();
	
	protected StatsModifier statsModifier = new StatsModifier();
	
	protected Master master;
	
	/*matrix representing colors to be displayed in input pins*/
	protected String[][] pinColors = {{"red", "blue"}, {"green", "yellow"}, {"orange", "violet"}, {"brown", "black"}};
	
	/*clickable pins*/
	@FXML protected GridPane inputPins;
	
	/*sequence under pins*/
	protected GridPane sequence;
	@FXML protected Pane sequenceContainer;
	
	/*right half of the screen*/
	@FXML protected VBox previousSequences;
	
	public GameController() {
		this.drawer = new Drawer();
		this.statsModifier = new StatsModifier();
	}
	
	/*intitializes and draws input pins*/
	@FXML
	protected void initialize() {
		drawInputPins();
		drawSequenceGrid();
	}
	
	@FXML
	protected void drawInputPins() {
		for(int i = 0; i < INPUT_PINS_ROWS; i++) {
			for(int j = 0; j < INPUT_PINS_COLUMNS; j++) {
				/*takes color from pinColors*/
				Circle pin = drawer.createCircle(INPUT_PIN_RADIUS, Paint.valueOf(pinColors[i][j]));
				/*adds "defaultColor" property, so color can be restored*/
				pin.getProperties().put("defaultColor", pinColors[i][j]);
				/*associates the method when the user clicks a pin*/
				pin.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					selectColor(event);
				});
				/*adds the circle in the grid*/
				inputPins.add(pin, j, i);
			}
		}
	}

	@FXML
	public void drawSequenceGrid() {
		double dimension = 250/sequence_length;
		sequence = drawer.createGrid(1, sequence_length, dimension);
		for(int i = 0; i < sequence_length; i++) {
			Circle sequenceCircle = drawer.createCircle(sequence_circle_radius, Paint.valueOf(WHITE_COLOR));
			sequenceCircle.getProperties().put("currentColor", "white");
			sequenceCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				disablePin(event);
			});
			sequence.add(sequenceCircle, i, 0);
		}
		sequenceContainer.getChildren().add(sequence);
	}
	
	/* When user clicks on a pin
	 * the color chosen is inserted into the first 
	 * empty circle of the sequence*/
	@FXML
	protected void selectColor(MouseEvent event) {
		/*gets the origin circle*/
		Circle pinSelected = (Circle)event.getSource();
		/*inserts the selected color in the first white sequence slot*/
		for (int i = 0; i < sequence_length; i++) {
			Circle sequenceSlot = (Circle) sequence.getChildren().get(i);
			if (sequenceSlot.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequenceSlot.setFill(pinSelected.getFill());
				sequenceSlot.getProperties().put("currentColor", pinSelected.getProperties().get("defaultColor"));
				break;
			}
		}
	}
	
	/* When user hits the check button
	 *prints the sequence and the hint grid in the previousSequences container*/
	@FXML
	protected void checkSequence() throws IOException {
		if(!(isSequenceCompleted(sequence))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sequence error");
			alert.setHeaderText(null);
			alert.setContentText("Please, complete the sequence THEN press Check button.");
			alert.showAndWait();
			}
		else {
			/*gets a copy of the sequence*/
			//convert sequence to enums
			int[] result = master.checkSequence(convertToColors());
			GridPane previousSequence = drawer.createPreviousSequence(sequence, previous_sequence_circle_radius, HINTPANE_ROW_NUMBER, hintPane_column_number, result);
			previousSequence.setHgap(5);
			/*adds the sequence into the container*/
			previousSequences.getChildren().add(previousSequence);
			clearSequence(sequence);
			checkWin();
			checkAttemps();
		}
	}
	
	protected void checkWin() throws IOException{
		if(master.getGame().getWin()) {
			Alert win = new Alert(AlertType.INFORMATION);
			win.setTitle("Victory");
			win.setHeaderText(null);
			win.setContentText("You win!!");
			win.showAndWait();
			SceneController.showMenu("../views/MainMenu.fxml");
			statsModifier.setStats(statsModifier.getStats(master.getGame().getDifficulty()), true, master.getGame().getAttempts());
		}
	}
	
	protected void checkAttemps() throws IOException {
		if(master.getGame().getAttempts() == master.getGame().getDifficulty().getAttempts()){
			Alert lose = new Alert(AlertType.INFORMATION);
			lose.setTitle("Defeat");
			lose.setHeaderText(null);
			lose.setContentText("You loose!");
			lose.showAndWait();
			SceneController.showMenu("../views/MainMenu.fxml");
			statsModifier.setStats(statsModifier.getStats(master.getGame().getDifficulty()), false, master.getGame().getAttempts());
		}
	}

	@FXML
	protected void giveUp() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to give up?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			SceneController.showMenu("../views/MainMenu.fxml");
		}
	}
	
	
	protected void disablePin(MouseEvent event) {
		Circle pin = (Circle)event.getSource();
		pin.setFill(Paint.valueOf(WHITE_COLOR));
	}
	
	protected void clearSequence(GridPane sequence) {
		Paint whiteColor = Paint.valueOf("white");
		for(int i = 0; i < sequence.getChildren().size(); i++) {
			Circle pin = ((Circle)sequence.getChildren().get(i));
			pin.setFill(whiteColor);
			pin.getProperties().put("currentColor", WHITE_COLOR);
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
	
	protected Colors[] convertToColors() {
		Colors[] sequenceToCheck = new Colors[sequence_length];
		for(int i = 0; i < sequence_length; i++) {
			Circle pin = (Circle)sequence.getChildren().get(i);
			String color = (String)pin.getProperties().get("currentColor");
			color = color.toUpperCase();
			//System.out.println(color.toString());
			Colors colorToCheck = Colors.valueOf(color);
			sequenceToCheck[i] = colorToCheck;
		}
		return sequenceToCheck;
	}
}
