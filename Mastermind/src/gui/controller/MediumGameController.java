package gui.controller;

public class MediumGameController extends GameController {

	final double SEQUENCE_RADIUS = 31.25;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	final int SEQUENCE_LENGTH = 4;
	
	public MediumGameController() {
		super.sequenceCircleRadius = this.SEQUENCE_RADIUS;
		super.previousSequenceCircleRadius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
