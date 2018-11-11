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
	
	public Game(Player player, Difficulty difficulty, Colors[] sequence) {
		this.player = player;
		this.difficulty = difficulty;
		this.sequence = sequence;
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
}
