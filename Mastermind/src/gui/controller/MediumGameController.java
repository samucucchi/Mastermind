package gui.controller;

import game.Master;
import game.enumerators.Difficulty;
import player.Player;

public class MediumGameController extends GameController {

	final double SEQUENCE_RADIUS = 31.25;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 25;
	
	final int HINTPANE_COLUMN_NUMBER = 2;
	
	final int SEQUENCE_LENGTH = 4;
	
	public MediumGameController() {
		super.master = new Master(Difficulty.MEDIUM, new Player("ciao"));
		super.sequence_circle_radius = this.SEQUENCE_RADIUS;
		super.previous_sequence_circle_radius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
