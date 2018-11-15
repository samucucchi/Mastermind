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

		// if you want to cheat, uncommnet this code.
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			System.out.print(game.getSequence()[i] + " ");
		}
		System.out.println();
		// */
	}

	// Returns the generated sequence, based on the selected difficulty
	public Colors[] generateSequence(Difficulty difficulty) {
		if (difficulty == Difficulty.EASY) {
			// difficulty is easy -> generates game sequence with no repetitions
			return generateEasySequence(difficulty);
		} else {
			// difficulty is medium or hard -> generates game sequence with repetitions
			return generateHardSequence(difficulty);
		}
	}

	
	// Generates a sequence for easy difficulty (repetitions not allowed)
	// and returns the generated sequence
	private Colors[] generateEasySequence(Difficulty difficulty) {
		Colors[] easySequence = new Colors[difficulty.getSequenceLength()];
		List<Colors> notinputSequenceerted = new ArrayList<Colors>(Arrays.asList(Colors.values()));
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			// inputSequenceert the remaining not inputSequenceerted elements (i.e. colors) into notinputSequenceerted
			easySequence[i] = notinputSequenceerted.remove(new Random().nextInt(notinputSequenceerted.size()));
		}
		return easySequence;
	}

	
	// Generates a sequence for medium or hard difficulty (repetitions allowed)
	// return the generated sequence
	private Colors[] generateHardSequence(Difficulty difficulty) {
		Colors[] hardSequence = new Colors[difficulty.getSequenceLength()];
		for (int i = 0; i < difficulty.getSequenceLength(); i++) {
			// randomly picks an element (repetitions are allowed) from the values in Colors
			hardSequence[i] = Colors.values()[new Random().nextInt(Colors.values().length)];
		}
		return hardSequence;
	}

	
	// Given an input sequence, returns an array int[2] containing:
	// 1 - right colors in right positions;
	// 2 - right colors in wrong positions;
	public int[] checkSequence(Colors[] inputSequence) {
		
		game.setAttempts();
		int rightPos = 0;	// right colors in right position
		int wrongPos = 0;	// right colors in wrong position
		int[] result = { rightPos, wrongPos }; // array of two integers containing the result
		
		// temporary sequence that's going to be modified for analysis purposes
		Colors[] checkedSequence = game.getSequence().clone();
		
		rightPos = getRightPosition(inputSequence, checkedSequence);
		result[0] = rightPos;
		// check if all the right colors are in the right positions
		if (rightPos != inputSequence.length) {
			// if not, checks for right colors in right positions
			wrongPos = getWrongPosition(inputSequence, checkedSequence);
			result[1] = wrongPos;
		} else {
			// else set game's "win" attribute as true
			game.setWin(true);
			return result;
		}
		return result;
	}

	// checks which color is guessed right and in the right position
	// then changes the two sequences' correct elements' values to null
	// so that they won't be counted again after
	public int getRightPosition(Colors[] inputSequence, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < inputSequence.length; i++) {
			if (inputSequence[i] == checkedSequence[i]) {
				counter++;
				inputSequence[i] = null;
				checkedSequence[i] = null;
			}
		}
		return counter;
	}

	// checks which color is guessed right but in the position
	// then changes the game's sequence's correct element's value to null
	// so that it won't be counted again after
	public int getWrongPosition(Colors[] inputSequence, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < inputSequence.length; i++) {
			if (inputSequence[i] != null) {
				for (int c = 0; c < inputSequence.length; c++) {
					if (inputSequence[i] == checkedSequence[c]) {
						checkedSequence[c] = null;
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
