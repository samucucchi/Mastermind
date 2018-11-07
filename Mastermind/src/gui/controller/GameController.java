package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	@FXML private GridPane sequence;
	
	@FXML
	private void selectColor(ActionEvent event) {
		String id = ((Button)event.getSource()).getId();
		String color = null;
		switch(id) {
		case "redPin":
			color = "red";
			break;
		case "bluePin":
			color = "blue";
			break;
		case "greenPin":
			color = "green";
			break;
		case "yellowPin":
			color = "yellow";
			break;
		case "orangePin":
			color = "orange";
			break;
		case "purplePin":
			color = "purple";
			break;
		case "brownPin":
			color = "brown";
			break;
		case "blackPin":
			color = "black";
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
