package gui.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class GameController {

	/*input pin radius is fixed*/
	protected final int INPUT_PIN_RADIUS = 25;
	
	/*a type of game could have a different radius for its circles*/
	protected int RADIUS;
	
	protected int hintPane_column_number;
	
	protected final int HINTPANE_ROW_NUMBER = 2;
	
	protected final String WHITE_COLOR = "white";
	
	protected final int COLOR_NUMBER = 8;
	
	protected final int INPUT_PINS_COLUMNS = 2;
	
	protected final int INPUT_PINS_ROWS = 4;
	
	/*matrix representing colors to be displayed in input pins*/
	protected String[][] pinColors = {{"red", "blue"}, {"green", "yellow"}, {"orange", "purple"}, {"brown", "black"}};
	
	/*clickable pins*/
	@FXML protected GridPane inputPins;
	
	/*sequence under pins*/
	@FXML protected GridPane sequence;
	
	/*right half of the screen*/
	@FXML protected VBox previousSequences;
	
	/*intitializes and draws input pins*/
	@FXML
	protected void initialize() {
		for(int i = 0; i < INPUT_PINS_ROWS; i++) {
			for(int j = 0; j < INPUT_PINS_COLUMNS; j++) {
				/*takes color from pinColors*/
				Circle pin = new Circle(INPUT_PIN_RADIUS, Paint.valueOf(pinColors[i][j]));
				pin.setStroke(Paint.valueOf("black"));
				/*adds "defaultColor" property, so color can be restored*/
				pin.getProperties().put("defaultColor", pin.getFill());
				/*associates the method when the user clicks a pin*/
				pin.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					selectColor(event);
				});
				/*adds the circle in the grid*/
				inputPins.add(pin, j, i);
			}
		}
	}
	
	/*user clicks a pin:
	 * the color chosen is inserted into the sequence*/
	@FXML
	protected void selectColor(MouseEvent event) {
		/*gets the origin circle*/
		Circle pinSelected = (Circle)event.getSource();
		/*inserts the selected color in the first white sequence slot*/
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Circle sequenceSlot = (Circle) sequence.getChildren().get(i);
			if (sequenceSlot.getFill() == Paint.valueOf(WHITE_COLOR)) {
				sequenceSlot.setFill(pinSelected.getFill());
				break;
			}
		}
	}
	
	/*user hits the check button:
	 *prints the sequence and the int grid in the previousSequences container*/
	@FXML
	protected void checkSequence() {
		if(isSequenceCompleted(sequence)) {
			/*gets a copy of the sequence*/
			GridPane previousSequence = createPreviousSequence(sequence);
			previousSequence.setHgap(5);
			/*adds the sequence into the container*/
			previousSequences.getChildren().add(previousSequence);
			/*clears the current sequence*/
			clearSequence(sequence);
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sequence error");
			alert.setHeaderText(null);
			alert.setContentText("Please, complete the sequence THEN press Check button.");

			alert.showAndWait();
		}
	}
	

	@FXML
	protected void removeColor(MouseEvent event) {
		Circle pinToRemove = (Circle)event.getSource();
		//Paint color = pinToRemove.getFill();
		disablePin(pinToRemove);
		//enablePin(color);
	}
	

	@FXML
	protected void giveUp() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to give up?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			SceneCtrl.showMainMenu();
		}
	}
	
	protected void enablePin(Paint color) {
		for(int i = 0; i < inputPins.getChildren().size(); i++) {
			Circle currentCircle = (Circle)inputPins.getChildren().get(i);
			Paint defaultColor = (Paint)currentCircle.getProperties().get("defaultColor");
			if(color == defaultColor) {
				currentCircle.setFill(color);
			}
		}
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
		previousSequence.add(createHintPane(), sequence.getChildren().size() + 1, 0);
		return previousSequence;
	}
	
	private Circle createSequenceCircle(Paint color) {
		Circle circle = new Circle(RADIUS, color);
		circle.setStroke(Paint.valueOf("black"));
		return circle;
	}
	
	private GridPane createHintPane() {
		GridPane hintPane = createGrid(HINTPANE_ROW_NUMBER, hintPane_column_number);
		for (int i = 0; i < HINTPANE_ROW_NUMBER; i++) {
			for (int j = 0; j < hintPane_column_number; j++) {
				BorderPane cell = new BorderPane();
				cell.setStyle("-fx-border-color: black");
				cell.setCenter(new Circle(RADIUS/2, Paint.valueOf("black")));
				hintPane.add(cell, j, i);
			}
		}
		return hintPane;
	}
	
	private GridPane createGrid(int rows, int columns) {
		GridPane grid = new GridPane();
		for(int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(RADIUS);
			grid.getRowConstraints().add(row);
		}
		for(int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(RADIUS);
			grid.getColumnConstraints().add(column);
		}
		return grid;
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
