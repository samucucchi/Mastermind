package game;

import java.util.UUID;
import player.*;

public class Game {
	private Player player1;
	private Player player2;
	private UUID gameId;
	private boolean finished;
	private int maxAttempts;

	public Game(Decoder player1, Codifier player2, int attempts) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameId = UUID.randomUUID();
		this.finished = false;
		this.maxAttempts = attempts;
	}
	
	public Game(Decoder player1, int attempts) {
		this.player1 = player1;
		this.player2 = null; 
		this.gameId = UUID.randomUUID();
		this.finished = false; 
		this.maxAttempts = attempts;
	}

	public Player[] getPlayers() {
		Player[] p = { player1, player2 };
		return p;
	}
	
	public void saveGame() {
		this.finished = true;
		// TODO set DB to store games history
	}
	
	public int getMaxAttempts() {
		return maxAttempts;
	}
}
