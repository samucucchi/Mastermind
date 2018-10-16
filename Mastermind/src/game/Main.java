package game;

import player.*;
import java.util.Scanner;

import game.exception.ExceptionMain;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Master master;

	public static void main(String[] args) {
		try {
			startGame();
		} catch (ExceptionMain e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean win = false; 
		for(int i = 0; i < master.getGame().getMaxAttempts(); i++) {
			if(win = tryToGuess()) {
				System.out.println("Sequenza indovinata"); 
				return;
			}
		}
		System.out.println("Sequenza non indovinata, hai perso");
	}

	/**
	 * Starts a new game, basing on the number of players that wants to play, the
	 * length of the sequence to guess and the maximum number of attempts
	 * 
	 * @throws ExceptionMain if wrong number of players have been inserted
	 */
	private static void startGame() throws ExceptionMain {
		System.out.println("Inserire numero giocatori");
		int numOfPlayers = Integer.parseInt(sc.nextLine()), maxAttempts, seqLength;
		if (numOfPlayers > 0 && numOfPlayers < 3) {
			System.out.println("Inserire la lunghezza della sequenza da indovinare");
			seqLength = Integer.parseInt(sc.nextLine());
			System.out.println("Inserire numero massimo di tentativi");
			maxAttempts = Integer.parseInt(sc.nextLine());
			System.out.println("Inserire username decodificatore");
			Decoder d = new Decoder(sc.nextLine());
			if (numOfPlayers == 1) {
				master = new Master(new Game(d, maxAttempts), seqLength);
			} else if (numOfPlayers == 2) {
				System.out.println("Inserire il nome del codificatore");
				Codifier c = new Codifier(sc.nextLine());
				System.out.println("Inserire la sequenza da indovinare");
				master = new Master(new Game(d, c, maxAttempts), takeSequence(seqLength));
			}
		} else {
			throw new ExceptionMain();
		}
	}

	/**
	 * 
	 * @param l length of sequence
	 * @return sequence inserted
	 */
	private static Colors[] takeSequence(int l) {
		Colors[] seq = new Colors[l];
		for (int i = 0; i < l; i++) {
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
	
	private static boolean tryToGuess() {
		System.out.println("Inserire una sequenza");
		int result[] = master.checkWin(takeSequence(master.getSeqLength()));
		if(result[0] == master.getSeqLength()) {
			return true; 
		} else {
			System.out.println(result[0] + " colori in posizione corretta");
			System.out.println(result[1] + " colori in posizione errata");
			System.out.println((master.getSeqLength() - (result[0] + result[1])) + " colori errati");
			return false;
		}
	}
}
