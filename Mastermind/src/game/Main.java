package game;

import player.*;
import java.util.Scanner;
import game.enumerators.Colors;
import game.enumerators.Difficulty;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Master master;

	public static void main(String[] args) {
		master = new Master(Difficulty.EASY, new Player("Sam"));
		for (int i = 0; i < master.getGame().getDifficulty().getAttempts(); i++) {
			Colors[] ins = takeSequence();
			int rightPosition = master.getRightPosition(ins);
			System.out.println(rightPosition + " numeri in posizione corretta");
			if(rightPosition == master.getGame().getDifficulty().getLength()) {
				System.out.println("Hai vinto");
			} else {
				System.out.println(master.getWrongPosition(ins) + " numeri in posizione errata");
			}	
		}
	}

	private static Colors[] takeSequence() {
		int seqLength = master.getGame().getDifficulty().getLength();
		Colors[] seq = new Colors[seqLength];
		for (int i = 0; i < seqLength; i++) {
			try {
				seq[i] = Colors.valueOf(sc.nextLine());
			} catch (Exception e) {
				System.out.println(e);
				i--;
				continue;
			}
		}
		return seq;
	}
}
