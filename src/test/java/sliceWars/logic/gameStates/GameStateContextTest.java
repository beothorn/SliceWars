package sliceWars.logic.gameStates;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import sliceWars.logic.AttackOutcome;
import sliceWars.logic.BoardCell;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.Attack;
import sliceWars.logic.gameStates.AttackCallback;
import sliceWars.logic.gameStates.GameStateContext;
import sliceWars.logic.gameStates.GameStateContextImpl;

public class GameStateContextTest {
	
	@Test
	public void testGetAttackOutcome(){
		final BoardCellMock attacker = new BoardCellMock(Player.PLAYER1);
		final BoardCellMock defender = new BoardCellMock(Player.PLAYER2);
		
		final int boardCellCount = 2;
		Attack attackPhase = new Attack(new Player(1, 2),new BoardMockAdapter() {
			
			@Override
			public BoardCell getCellAtOrNull(int x, int y) {
				if(x == 0) return attacker;
				return defender;
			}

			@Override
			public int getCellCount() {
				return boardCellCount;
			}
			
			@Override
			public boolean areLinked(BoardCell c1, BoardCell c2) {
				return true;
			}
			
			@Override
			public int getBiggestLinkedCellCountForPlayer(Player player) {
				return 1;
			}
		});
		GameStateContext subject = new GameStateContextImpl(null, attackPhase);
		final AtomicBoolean attacked = new AtomicBoolean(false);
		subject.setAttackCallback(new AttackCallback() {@Override public void attackedWithOutcome(AttackOutcome attackOutcome) {
			attacked.set(true);
		}});
		subject.play(0, 0);
		subject.play(1, 0);
		
		assertTrue(attacked.get());
	}

}
