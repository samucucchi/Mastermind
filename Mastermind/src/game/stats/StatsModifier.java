package game.stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.enumerators.Difficulty;

public class StatsModifier {

	private double[][] stats;
	private final int DIFFICULTY_NUMBER = 3;
	private final int OPTIONS_NUMBER = 5;

	public StatsModifier() {
		this.stats = new double[DIFFICULTY_NUMBER][OPTIONS_NUMBER];
		readStats("src/game/stats/stats.txt");
	}

	public void readStats(String path) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String line;

			for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
				for (int j = 0; j < OPTIONS_NUMBER; j++) {
					line = br.readLine();
					if (line != null && line.length() == 1) {
						double statToInsert = Double.parseDouble(line);
						stats[i][j] = statToInsert;
					} else {
						throw new IOException();
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
}