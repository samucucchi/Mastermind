package gui.controller;

public class HardGameController extends GameController{

	final int RADIUS = 15;
	
	final int HINTPANE_COLUMN_NUMBER = 3;
	
	final int SEQUENCE_LENGTH = 6;
	
	public HardGameController() {
		super.RADIUS = this.RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
