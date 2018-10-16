//TODO remove static from all methods
package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Master {
	private Game game;
	private Colors[] seq;

	public Master(Game game, int seqLength) {
		this.game = game;
		this.seq = generateSequence(seqLength);
	}

	public Master(Game game, Colors[] seq) {
		this.game = game;
		this.seq = seq;
	}

	/**
	 * @param ins : sequence inserted from the player
	 * @param seq : sequence to guess
	 * @return Integer[]: [0] color guessed in right position, [1] color guessed in
	 *         wrong position
	 */
	public int[] checkWin(Colors[] ins) {
		int[] g = new int[2];
		for (int i = 0; i < ins.length; i++) {
			for (int c = 0; c < ins.length; c++) {
				if (ins[i] == seq[c]) {
					if (i == c)
						g[0]++;
					else
						g[1]++;
					break;
				}
			}
		}
		return g;
	}

	/**
	 * Generates a sequence of Colors, without repetitions
	 * 
	 * @param length : size of sequence to guess
	 * @return generated sequence
	 */
	private Colors[] generateSequence(int length) {
		Colors[] generated = new Colors[length];
		List<Colors> left = new LinkedList<Colors>(Arrays.asList(Colors.values()));

		for (int i = 0; i < length; i++)
			generated[i] = left.remove(new Random().nextInt(left.size()));
		return generated;
	}

	public Game getGame() {
		return game;
	}

	public int getSeqLength() {
		return seq.length;
	}
}
