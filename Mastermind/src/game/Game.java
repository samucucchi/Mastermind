package game;

import java.util.UUID;
import game.enumerators.Colors;
import game.enumerators.Difficulty;
import player.*;

public class Game {
	private UUID gameId;
	private final Difficulty difficulty;
	private Player player;
	private Colors[] sequence;
	private int attempts;
	private boolean win;
	
	public Game(Player player, Difficulty difficulty, Colors[] sequence) {
		this.player = player;
		this.difficulty = difficulty;
		this.sequence = sequence;
		this.attempts = 0;
		this.win = false;
	}

	public Player getPlayer() {
		return player;
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
