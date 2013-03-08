package sliceWars.logic.gameStates;

import static org.junit.Assert.assertEquals;

import java.awt.Polygon;

import org.junit.Test;

import sliceWars.logic.BoardCell;
import sliceWars.logic.BoardCellImpl;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.FirstAttacks;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;

public class FirstAttackPhaseTest {

	@Test
	public void testState(){
		final BoardCellImpl boardCell = new BoardCellImpl(new Polygon(), null);
		boardCell.setOwner(Player.PLAYER1);
		boardCell.setDiceCount(1);
		final int boardCellCount = 1;
		FirstAttacks subject = new  FirstAttacks(new Player(1, 2),new BoardMockAdapter() {
			
			@Override
			public BoardCell getCellAtOrNull(int x, int y) {
				return boardCell;
			}

			@Override
			public int getCellCount() {
				return boardCellCount;
			}
		});
		GameStateContextMock gameStateContextMock = new GameStateContextMock();
		subject.play(0, 0,gameStateContextMock);
		assertEquals(Phase.FIRST_ATTACKS,gameStateContextMock.getPhase());
		subject.pass(gameStateContextMock);
		subject.play(0, 0,gameStateContextMock);
		assertEquals(Phase.FIRST_ATTACKS,gameStateContextMock.getPhase());
		subject.pass(gameStateContextMock);
		assertEquals(Phase.DICE_DISTRIBUTION,gameStateContextMock.getPhase());
	}
	
}
