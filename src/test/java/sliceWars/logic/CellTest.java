package sliceWars.logic;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class CellTest {

	@Test
	public void tryToAddDieToScenario_cantDoIt(){
		Cell cell = new Cell();
		cell.owner = Player.SCENARIO;
		assertFalse(cell.canAddDie());
	}
}
