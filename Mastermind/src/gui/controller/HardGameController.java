package gui.controller;

import game.Master;
import game.enumerators.Difficulty;
import player.Player;

public class HardGameController extends GameController{

	final double SEQUENCE_RADIUS = 20.83;
	
	final int PREVIOUS_SEQUENCE_RADIUS = 15;
	
	final int HINTPANE_COLUMN_NUMBER = 3;
	
	final int SEQUENCE_LENGTH = 6;
	
	public HardGameController() {
		super.master = new Master(Difficulty.HARD, new Player("ciao"));
		super.sequenceCircleRadius = this.SEQUENCE_RADIUS;
		super.previousSequenceCircleRadius = this.PREVIOUS_SEQUENCE_RADIUS;
		super.hintPane_column_number = this.HINTPANE_COLUMN_NUMBER;
		super.sequence_length = this.SEQUENCE_LENGTH;
	}
}
