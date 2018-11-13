package game;

import java.util.UUID;
import game.enumerators.Colors;
import game.enumerators.Difficulty;

public class Game {
	private UUID gameId;
	private final Difficulty difficulty;
	private Colors[] sequence;
	private int attempts;
	private boolean win;
	
	public Game(Difficulty difficulty, Colors[] sequence) {
		this.gameId = gameId;
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
	
	public void setWin(boolean winCondition) {
		this.win = winCondition;
	}
	
}
