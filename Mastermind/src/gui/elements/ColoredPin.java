package gui.elements;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ColoredPin {

	private Circle circle;
	
	public ColoredPin(float radius, String color) {
		this.circle = new Circle(radius, Paint.valueOf(color));
		this.circle.getProperties().put("defaultColor", color);
	}
	
	public Circle getCircle() {
		return this.circle;
	}
}
