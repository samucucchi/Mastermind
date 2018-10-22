package game.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.*;

class TestMaster {
	Master m;

	@Test
	void testGenerateSequence() {
		m = new Master(null, 5); // il costruttore genera automaticamente la sequenza da indovinare
		assertEquals(5, m.getSeqLength());
	}
}

// STO PROVANDO IL LOADING 