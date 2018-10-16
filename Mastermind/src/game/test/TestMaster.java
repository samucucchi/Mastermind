package game.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.*;

class TestMaster {
	Master m;

	@Test
	void testCheckWin() {
		Colors[] c = { Colors.BLACK, Colors.BLUE, Colors.GREEN, Colors.ORANGE, Colors.RED };
		m = new Master(null, c);
		int[] result = m.checkWin(c);
		assertEquals(5, result[0]);
	}

	@Test
	void testGenerateSequence() {
		m = new Master(null, 5); // il costruttore genera automaticamente la sequenza da indovinare
		assertEquals(5, m.getSeqLength());
	}
}
