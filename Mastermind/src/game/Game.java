package game;

import game.enumerators.Colors;
import game.enumerators.Difficulty;

public class Game {
	private final Difficulty difficulty; // game's difficulty level
	private Colors[] sequence; // game's sequence that has to be discovered by the player
	private int attempts; // updated number of the player's attemps
	private boolean win; // game win status: true or false.
	
	public Game(Difficulty difficulty, Colors[] sequence) {
		this.difficulty = difficulty;
		this.sequence = sequence;
		this.attempts = 0;
		this.win = false;
	}
	
	public Colors[] getSequence() {
		return sequence;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public int getAttempts() {
		return attempts;
	}
	
	public void setAttempts() {
		attempts++;
	}
	
	public boolean getWin() {
		return win;
	}
	
	// sets game's win attribute to a boolean "winCondition"
	public void setWin(boolean winCondition) {
		this.win = winCondition;
	}
	
}
