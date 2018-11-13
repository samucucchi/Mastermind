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

	public Master(Difficulty difficulty) {
		this.game = new Game(difficulty, generateSequence(difficulty));

		// TODO remove this part (prints in terminal the game sequence
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			System.out.print(game.getSequence()[i] + " ");
		}
		System.out.println();
	}

	/**
	 * @return the sequence generated, basing on the difficulty selected before
	 */
	public Colors[] generateSequence(Difficulty difficulty) {
		if (difficulty == Difficulty.EASY) {
			// generates game sequence with no repetitions
			return generateEasySequence(difficulty);
		} else {
			// difficulty is medium or hard -> generates game sequence with repetitions
			return generateHardSequence(difficulty);
		}
	}

	/**
	 * Generates a sequence for easy difficulty
	 * 
	 * @return the sequence generated
	 */
	private Colors[] generateEasySequence(Difficulty difficulty) {
		// TODO: test
		Colors[] easySequence = new Colors[difficulty.getSequenceLength()];
		List<Colors> notInserted = new ArrayList<Colors>(Arrays.asList(Colors.values()));
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			// insert into colors the remaining not inserted elements into notInserted
			easySequence[i] = notInserted.remove(new Random().nextInt(notInserted.size()));
		}
		return easySequence;
	}

	/**
	 * Generates a sequence of medium or hard difficulty
	 * 
	 * @return the sequence generated
	 */
	private Colors[] generateHardSequence(Difficulty difficulty) {
		// TODO test
		Colors[] hardSequence = new Colors[difficulty.getSequenceLength()];
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			// randomly picks an element (repetitions are allowed) from the values in Colors
			hardSequence[i] = Colors.values()[new Random().nextInt(Colors.values().length)];
		}
		return hardSequence;
	}

	/**
	 * @param ins : array containing the values inserted by the player
	 * @return how many values have been guessed in right or wrong position
	 */

	public int[] checkSequence(Colors[] ins) {
		int rightPos = 0;	// right colors in right position
		int wrongPos = 0;	// right colors in wrong position
		int[] result = { rightPos, wrongPos }; // array of two integers containing the result

		Colors[] checkedSequence = game.getSequence().clone(); //
		rightPos = getRightPosition(ins, checkedSequence);
		result[0] = rightPos;
		if (rightPos != ins.length) {
			wrongPos = getWrongPosition(ins, checkedSequence);
			result[1] = wrongPos;
		} else {
			game.setWin(true);
			return result;
		}
		System.out.println("Giusti: " + rightPos);
		System.out.println("Sbagliati: " + wrongPos);
		game.setAttempts();
		if (game.getAttempts() == game.getDifficulty().getAttempts()) {
			System.out.println("Brutto coglione hai perso! " + game.getAttempts());
		}
		return result;
	}

	public int getRightPosition(Colors[] ins, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (ins[i] == checkedSequence[i]) {
				counter++;
				ins[i] = null;
				checkedSequence[i] = null;
			}
		}
		return counter;
	}

	public int getWrongPosition(Colors[] ins, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (ins[i] != null) {
				for (int c = 0; c < ins.length; c++) {
					if (ins[i] == checkedSequence[c]) {
						counter++;
						break;
					}
				}
			}

		}
		return counter;
	}

	public Game getGame() {
		return game;
	}
}
