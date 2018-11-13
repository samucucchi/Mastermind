package game.enumerators;

public enum Difficulty {
	EASY(4, 7, false), MEDIUM(4, 14, true), HARD(6, 28, true);

	private final int sequenceLength; // length of the sequence
	private final int attempts; // player's attemps limit
	private final boolean rep; // colors repetitions allowed: true or false

	Difficulty(int sequenceLength, int attempts, boolean rep) {
		this.sequenceLength = sequenceLength;
		this.attempts = attempts;
		this.rep = rep;
	}

	public int getSequenceLength() {
		return sequenceLength;
	}
	
	public int getAttempts() {
		return attempts;
	}
	
	public boolean getRep() {
		return rep;
	}
}
