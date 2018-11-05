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
		//difficoltà
		//GUI
	}
	
	/**
	 * @param ins : array containing the values inserted by the decoder
	 * @return how much values have been guessed in right position
	 */
	public int getRightPosition(Colors[] ins) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (ins[i] == seq[i]) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * 
	 * @param ins : array containing the values inserted by the decoder
	 * @return how much values have been guessed in right position
	 */
	public int getWrongPosition(Colors[] ins) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			for (int c = 0; c < ins.length; c++) {
				if (ins[i] == seq[c] && i != c) {
					counter++;
					if (counter == ins.length) { // if ins.lenght have been guessed, is useless to continue to search
						break;
					}
				}
			}
		}
		return counter;
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
