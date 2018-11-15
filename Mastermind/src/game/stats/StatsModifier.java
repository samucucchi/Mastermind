package game.stats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.print.DocFlavor.URL;

import game.enumerators.Difficulty;

public class StatsModifier {

	private final int WINS = 0;
	private final int LOSSES = 1;
	private final int AVERAGE = 2;
	private final int BEST = 3;
	private final int WORST = 4;

	private int[][] stats;
	private final int DIFFICULTY_NUMBER = 3;
	private final int OPTIONS_NUMBER = 5;
	private final String PATH = "stats.txt";

	public StatsModifier() {
		this.stats = new int[DIFFICULTY_NUMBER][OPTIONS_NUMBER];
		readStats();
	}

	// reads stats from file
	private void readStats() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(PATH)));

			String line;

			// inserts each file's value in stats matrix
			for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
				for (int j = 0; j < OPTIONS_NUMBER; j++) {
					line = br.readLine();
					if (line != null) {
						int statToInsert = Integer.parseInt(line);
						stats[i][j] = statToInsert;
					}
				}
			}
			// closes buffered reader
			br.close();
			// catches exceptions
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file \"" + PATH + "\".");
		} catch (IOException ex) {
			System.out.println("Error while reading file \"" + PATH + "\".");
		}
	}

	// writes stats to file
	private void writeStats() throws IOException {
		File file = new File(PATH);
		PrintWriter writer = new PrintWriter(file);
		for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
			for (int j = 0; j < OPTIONS_NUMBER; j++) {
				writer.println(stats[i][j]);
			}
		}
		writer.close();
	}

	// gets stats from file, based on selected difficulty
	// (used in the HistoryController comboBox event handler)
	public int[] getStats(Difficulty difficulty) {
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

	public void setStats(int[] stat, boolean win, int attempts) throws IOException {

		// if it's a win, updates wins count, best score and worst score
		if (win) {
			stat[WINS]++;
			if ((stat[BEST] > attempts) || (stat[BEST] == 0)) {
				stat[BEST] = attempts;
			}
			if (stat[WORST] < attempts) {
				stat[WORST] = attempts;
			}

			// if it's a loss, updates only losses count
		} else {
			stat[LOSSES]++;
		}

		// updates average
		// calculates arithmetic average
		if (stat[WINS] + stat[LOSSES] < 2) {
			stat[AVERAGE] = attempts;
		} else {
			stat[AVERAGE] = (stat[AVERAGE] + attempts) / 2;
		}

		// finally writes updated stats on the file
		writeStats();
	}

	// resets the stats, setting all the stats values to 0
	// and writes them on the file
	public void resetStats() throws IOException {
		for (int i = 0; i < DIFFICULTY_NUMBER; i++) {
			for (int j = 0; j < OPTIONS_NUMBER; j++) {
				stats[i][j] = 0;
			}
		}
		writeStats();
	}

}
