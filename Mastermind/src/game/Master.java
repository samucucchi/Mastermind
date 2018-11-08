//TODO remove static from all methods
package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import game.enumerators.Colors;
import game.enumerators.Difficulty;

public class Master {
	private Game game;
	private final Difficulty difficulty;

	public Master(Game game, Difficulty difficulty) {
		this.game = game;
		this.difficulty = difficulty;
		// GUI
	}

	/**
	 * @param ins : array containing the values inserted by the decoder
	 * @return how much values have been guessed in right position
	 */
	public int getRightPosition(Colors[] ins) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (true/*check sequence taking it from Game*/) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * @param ins : array containing the values inserted by the decoder
	 * @return how much values have been guessed in right position
	 */
	public int getWrongPosition(Colors[] ins) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			for (int c = 0; c < ins.length; c++) {
				if (true /*ins[i] == seq[c] && i != c*/) {
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
	 * @return the sequence generated, basing on the difficulty selected before
	 */
	public Colors[] generateSequence() {
		//TODO exception if difficulty has not been set
		if(difficulty == Difficulty.EASY) {
			//generates sequence with no repetitions 
			return generateEasySequence();
		} else { 
			//difficulty is medium or hard, generates sequence with repetitions 
			return generateHardSequence();
		}
	}
	
	/**
	 * Generates a sequence of medium or hard difficulty
	 * @return the sequence generated
	 */
	private Colors[] generateHardSequence() {
		//TODO test
		Colors[] colors = new Colors[difficulty.getLength()];
		for(int i = 0; i < difficulty.getLength(); i++) {
			//takes randomly an element (with repetitions) from the values into Colors
			colors[i] = Colors.values()[new Random().nextInt(difficulty.getLength())];
		}
		return colors; 
	}

	/**
	 * Generates a sequence of easy difficulty
	 * @return the sequence generated
	 */
	private Colors[] generateEasySequence() {
		//TODO: test
		Colors[] colors = new Colors[difficulty.getLength()];
		List<Colors> notInserted = new ArrayList<Colors>(Arrays.asList(Colors.values()));
		for(int i = 0; i < difficulty.getLength(); i++) {
			//insert into colors the remaining not inserted elements into notInserted
			colors[i] = notInserted.remove(new Random().nextInt(notInserted.size()));
		}
		return colors;
	}

	public Game getGame() {
		return game;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
}
