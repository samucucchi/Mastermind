package game.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.*;
import game.enumerators.Colors;
import game.enumerators.Difficulty;
import player.Player;

class TestMaster {
	Master m;

	@Test
	public void checkGenerateEasySequence() {
		m = new Master(Difficulty.EASY, new Player("test"));
		Colors[] col = m.getGame().getSequence();
		for (int i = 0; i < col.length; i++) {
			for (int c = 0; c < col.length; c++) {
				if (i != c)
					assertNotEquals(col[i], col[c]);
			}
		}
	}
	

	@Test
	public void checkGetRightWrongPosition() {
		m = new Master(Difficulty.EASY, new Player("test"));
		int length = m.getGame().getDifficulty().getLength();
		for(int i = 0; i < m.getGame().getSequence().length; i++) {
			System.out.println(m.getGame().getSequence()[i]);
		}
		Colors[] seq = m.getGame().getSequence();
		assertEquals(length, m.getRightPosition(seq));
		assertEquals(0, m.getWrongPosition(m.getGame().getSequence()));
	}
}