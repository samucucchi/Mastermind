package gui.controller;

import game.Master;
import game.enumerators.Difficulty;

public class MediumGameController extends GameController {

	// subClass final attributes
	final double SEQUENCE_RADIUS = 31.25;
	final int PREVIOUS_SEQUENCE_RADIUS = 25;
	final int HINTPANE_COLUMN_NUMBER = 2;
	final int SEQUENCE_LENGTH = 4;
	
	public MediumGameController() {
		// EasyGameController's superclass (i.e. GameController) attributes override;
		super();
		super.master = new Master(Difficulty.MEDIUM);
		super.sequence_circle_radius = this.SEQUENCE_RADIUS;
		super.previous_sequence_circle_radius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
