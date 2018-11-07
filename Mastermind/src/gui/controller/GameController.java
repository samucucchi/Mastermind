package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController {

	@FXML
	private void selectColor(ActionEvent event) {
		String id = ((Button)event.getSource()).getId();
		System.out.println(id);
	}
}
