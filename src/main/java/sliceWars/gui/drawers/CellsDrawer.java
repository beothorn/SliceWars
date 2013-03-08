package sliceWars.gui.drawers;

import java.awt.Graphics2D;
import java.util.Collection;

import sliceWars.logic.BoardCell;
import sliceWars.logic.gameStates.GameStateContext;

public class CellsDrawer implements Drawer {

	private CellDrawer simpleCellDrawer;
	private GameStateContext _gameContext;
	
	public CellsDrawer(GameStateContext gameContext,CellDrawer cellDrawer) {
		_gameContext = gameContext;
		simpleCellDrawer = cellDrawer;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		Collection<BoardCell> boardCells = _gameContext.getBoardCells();
		for (BoardCell boardCell : boardCells) {
			simpleCellDrawer.draw(boardCell,g2);
		}
	}
	
}
