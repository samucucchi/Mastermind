//TODO remove static from all methods
package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import game.enumerators.Colors;
import game.enumerators.Difficulty;
import player.Player;

public class Master {
	private Game game;

	public Master(Difficulty difficulty, Player player) {
		this.game = new Game(player, difficulty, generateSequence(difficulty));
		
		for(int i = 0; i < difficulty.getSequenceLength(); i++) {
			System.out.print(game.getSequence()[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * @return the sequence generated, basing on the difficulty selected before
	 */
	public Colors[] generateSequence(Difficulty difficulty) {
		//TODO exception if difficulty has not been set
		if(difficulty == Difficulty.EASY) {
			//generates sequence with no repetitions 
			return generateEasySequence(difficulty);
		} else { 
			//difficulty is medium or hard, generates sequence with repetitions 
			return generateHardSequence(difficulty);
		}
	}
	
	/**
	 * Generates a sequence of easy difficulty
	 * @return the sequence generated
	 */
	private Colors[] generateEasySequence(Difficulty difficulty) {
		//TODO: test
		Colors[] colors = new Colors[difficulty.getSequenceLength()];
		List<Colors> notInserted = new ArrayList<Colors>(Arrays.asList(Colors.values()));
		for(int i = 0; i < difficulty.getSequenceLength(); i++) {
			//insert into colors the remaining not inserted elements into notInserted
			colors[i] = notInserted.remove(new Random().nextInt(notInserted.size()));
		}
		return colors;
	}
	
	/**
	 * Generates a sequence of medium or hard difficulty
	 * @return the sequence generated
	 */
	private Colors[] generateHardSequence(Difficulty difficulty) {
		//TODO test
		Colors[] colors = new Colors[difficulty.getSequenceLength()];
		for(int i = 0; i < difficulty.getSequenceLength(); i++) {
			//takes randomly an element (with repetitions) from the values into Colors
			colors[i] = Colors.values()[new Random().nextInt(difficulty.getSequenceLength())];
		}
		return colors; 
	}


	/**
	 * @param ins : array containing the values inserted by the decoder
	 * @return how much values have been guessed in right position
	 */
	
	public int[] checkSequence(Colors[] ins) {
		int rightPos = 0;
		int wrongPos = 0;
		int [] result = {rightPos,wrongPos};
		Colors [] checkedSequence = copySequence(game.getSequence());
		rightPos = getRightPosition(ins,checkedSequence);
		if (rightPos != ins.length) {
			wrongPos = getWrongPosition(ins,checkedSequence);
		}
		else {
			return result;
		}
		result[0] = rightPos;
		result[1] = wrongPos;
		System.out.println("Giusti: " + rightPos);
		System.out.println("Sbagliati: " + wrongPos);
		game.setAttempts();
		if(game.getAttempts() == game.getDifficulty().getAttempts() ) {
			System.out.println("Brutto coglione hai perso! " + game.getAttempts() ); 
		}
		return result;
	}
	
	public int getRightPosition(Colors[] ins, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (ins[i] == game.getSequence()[i]) {
				counter++;
				ins[i] = null;
				checkedSequence[i] = null;
			}
			if (counter == ins.length) { // if the hole sequence has been correctly guessed, end the game
				System.out.println("CONGRATS, YOU WON!");
				break;
			}
		}
		return counter;
	}
	
	public int getWrongPosition(Colors[] ins, Colors[] checkedSequence) {
		int counter = 0;
		for (int i = 0; i < ins.length; i++) {
			if (ins[i]!=null) {
				for (int c = 0; c < ins.length; c++) {
					if (ins[i] == checkedSequence[c]) {
						counter++;
						if (counter == ins.length) { // if ins.length have been guessed, is useless to continue to search
							break;
						}
					}
				}
			}

		}
		return counter;
	}

	private Colors[] copySequence (Colors[] sequence) {
		Colors[] copy = new Colors[sequence.length];
		for (int i = 0; i < sequence.length; i++) {
			copy[i] = sequence[i];
		}
		return copy;
	}
	

	public Game getGame() {
		return game;
	}
}
