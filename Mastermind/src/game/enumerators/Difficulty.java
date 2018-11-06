package game.enumerators;

public enum Difficulty {
	EASY(4, 7, false), MEDIUM(4, 14, true), HARD(6, 28, true);

	private final int length;
	private final int attempts;
	private final boolean rep;

	Difficulty(int length, int attempts, boolean rep) {
		this.length = length;
		this.attempts = attempts;
		this.rep = rep;
	}

	public int getLength() {
		return length;
	}
	
	public int getAttempts() {
		return attempts;
	}
	
	public boolean getRep() {
		return rep;
	}
}
