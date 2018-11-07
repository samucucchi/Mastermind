package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	@FXML private Button redPin;
	@FXML private Button bluePin;
	@FXML private Button greenPin;
	@FXML private Button yellowPin;
	@FXML private Button orangePin;
	@FXML private Button purplePin;
	@FXML private Button brownPin;
	@FXML private Button blackPin;
	
	@FXML private GridPane sequence;
	
	@FXML
	private void selectColor(ActionEvent event) {
		String id = ((Button)event.getSource()).getId();
		String color = null;
		switch(id) {
		case "redPin":
			color = "red";
			redPin.setStyle("-fx-background-color: white");
			redPin.setDisable(true);
			break;
		case "bluePin":
			color = "blue";
			bluePin.setStyle("-fx-background-color: white");
			bluePin.setDisable(true);
			break;
		case "greenPin":
			color = "green";
			greenPin.setStyle("-fx-background-color: white");
			greenPin.setDisable(true);
			break;
		case "yellowPin":
			color = "yellow";
			yellowPin.setStyle("-fx-background-color: white");
			yellowPin.setDisable(true);
			break;
		case "orangePin":
			color = "orange";
			orangePin.setStyle("-fx-background-color: white");
			orangePin.setDisable(true);
			break;
		case "purplePin":
			color = "purple";
			purplePin.setStyle("-fx-background-color: white");
			purplePin.setDisable(true);
			break;
		case "brownPin":
			color = "brown";
			brownPin.setStyle("-fx-background-color: white");
			brownPin.setDisable(true);
			break;
		case "blackPin":
			color = "black";
			blackPin.setStyle("-fx-background-color: white");
			blackPin.setDisable(true);
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
}
