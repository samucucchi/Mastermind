package gui.controller;

public class MediumGameController extends GameController {

	final int RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	final int SEQUENCE_LENGTH = 4;
	
	public MediumGameController() {
		super.RADIUS = this.RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
