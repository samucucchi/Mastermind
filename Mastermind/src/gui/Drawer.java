package gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Drawer {

	public Circle createCircle(double radius, Paint color) {
		Circle circle = new Circle(radius, color);
		circle.setStroke(Paint.valueOf("black"));
		return circle;
	}

	public GridPane createGrid(int rows, int columns, double dimension) {
		GridPane grid = new GridPane();
		for (int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(dimension);
			grid.getRowConstraints().add(row);
		}
		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(dimension);
			grid.getColumnConstraints().add(column);
		}
		return grid;
	}

	private GridPane createHintPane(int rows, int columns, double dimension, int[] result) {
		int rightPos = 0;
		int wrongPos = 0;
		GridPane hintPane = createGrid(rows, columns, dimension);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				BorderPane cell = new BorderPane();
				if (rightPos < result[0]) {
					cell.setCenter(createCircle(dimension / 2, Paint.valueOf("black")));
					rightPos++;
				} else if (wrongPos < result[1]) {
					cell.setCenter(createCircle(dimension / 2, Paint.valueOf("grey")));
					wrongPos++;
				}
				// cell.setStyle("-fx-border-color: black");
				hintPane.add(cell, j, i);
			}
		}
		return hintPane;
	}

	public GridPane createPreviousSequence(GridPane sequence, double radius, int rows, int columns, int[] result) {
		GridPane previousSequence = new GridPane();
		for (int i = 0; i < sequence.getChildren().size(); i++) {
			Paint pinColor = ((Circle) sequence.getChildren().get(i)).getFill();
			Circle previousPin = createCircle(radius, pinColor);
			previousSequence.add(previousPin, i, 0);
		}
		previousSequence.add(createHintPane(rows, columns, radius, result), sequence.getChildren().size() + 1, 0);
		previousSequence.setHgap(5);
		return previousSequence;
	}
}
