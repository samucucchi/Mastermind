package gui.controller;

import game.Master;
import game.enumerators.Difficulty;

public class HardGameController extends GameController{

	final double SEQUENCE_RADIUS = 20.83;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 15;
	
	final int HINTPANE_COLUMN_NUMBER = 3;
	
	final int SEQUENCE_LENGTH = 6;
	
	public HardGameController() {
		super();
		super.master = new Master(Difficulty.HARD);
		super.sequence_circle_radius = this.SEQUENCE_RADIUS;
		super.previous_sequence_circle_radius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
