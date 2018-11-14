package game.stats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import game.enumerators.Difficulty;

public class StatsModifier {

	private final int WINS = 0;
	private final int LOSSES = 1;
	private final int AVERAGE = 2;
	private final int BEST = 3;
	private final int WORST = 4;
	
	private double[][] stats;
	private final int DIFFICULTY_NUMBER = 3;
	private final int OPTIONS_NUMBER = 5;
	private final String PATH = "Mastermind/src/game/stats/stats.txt";

	public StatsModifier() {
		this.stats = new double[DIFFICULTY_NUMBER][OPTIONS_NUMBER];
		readStats();
	}

	private void readStats() {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(PATH);
			br = new BufferedReader(fr);

			String line;

			for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
				for (int j = 0; j < OPTIONS_NUMBER; j++) {
					line = br.readLine();
					if (line != null) {
						double statToInsert = Double.parseDouble(line);
						stats[i][j] = statToInsert;
					} else {
						System.out.println("shit");;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
	
	private void writeStats() throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
	    for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
			for (int j = 0; j < OPTIONS_NUMBER; j++) {
				writer.write(stats[i][j] + "\n");
			}
		}
	    writer.close();
	}

	public double[] getStats(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			return stats[0];
		case MEDIUM:
			return stats[1];
		case HARD:
			return stats[2];
		default:
			return null;
		}

	}
	
	public void setStats(double[] stat, boolean win, int attempts) throws IOException {

		if (win) {
			stat[WINS]++;
			
			stat[AVERAGE] = (stat[AVERAGE] + attempts) / stat[WINS];
			if ( (stat[BEST] > attempts) || (stat[BEST] == 0) ) {
				stat[BEST] = attempts;
			}
			if (stat[WORST] < attempts) {
				stat[WORST] = attempts;
			}
		} else {
			
			stat[LOSSES]++;
		}

		writeStats();
	}
	
}
