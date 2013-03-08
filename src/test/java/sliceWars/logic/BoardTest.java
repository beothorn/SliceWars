package sliceWars.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Polygon;

import org.junit.Test;

import sliceWars.logic.BoardCell;
import sliceWars.logic.BoardImpl;
import sliceWars.logic.Cell;
import sliceWars.logic.Player;

public class BoardTest {

	@Test
	public void addSomeCellsAndLinkThem_CheckIfOk(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		BoardCell cell1 = subject.createAndAddToBoardCellForPolygon(irrelevant,null);
		BoardCell cell2 = subject.createAndAddToBoardCellForPolygon(irrelevant,null);
		BoardCell cell3 = subject.createAndAddToBoardCellForPolygon(irrelevant,null);
		
		subject.link(cell1,cell2);
		subject.link(cell2,cell3);
		assertTrue(subject.areLinked(cell1,cell2));
		assertTrue(subject.areLinked(cell2,cell1));
		assertTrue(subject.areLinked(cell2,cell3));
		assertFalse(subject.areLinked(cell1,cell3));
	}
	
	@Test
	public void addSomeCells_ThenRemoveOne(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		BoardCell cell1 = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cell2 = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		subject.link(cell1,cell2);
		
		subject.remove(cell1);
		assertTrue(!subject.getBoardCells().contains(cell1));
		assertFalse(subject.areLinked(cell1,cell2));
	}
	
	@Test
	public void addSomeCellsAndSetOwners_ShouldSayBoardIsFull(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		subject.createAndAddToBoardCellForPolygon(irrelevant, null).setOwner(Player.PLAYER1);
		subject.createAndAddToBoardCellForPolygon(irrelevant, null).setOwner(Player.PLAYER1);
		subject.createAndAddToBoardCellForPolygon(irrelevant, null).setOwner(Player.PLAYER1);
		assertTrue(subject.isFilled());
	}
	
	@Test
	public void addSomeCellsAndSetOwnersAndFillThem_ShouldSayPlayerCantPlay(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		BoardCell cell1 = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		cell1.setOwner(Player.PLAYER1);
		BoardCell cell2 = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		cell2.setOwner(Player.PLAYER1);
		cell1.setDiceCount(1);
		cell2.setDiceCount(1);
		assertTrue(!subject.areaAllCellsFilledByPlayer(Player.PLAYER1));
		cell1.setDiceCount(Cell.MAX_DICE);
		cell2.setDiceCount(Cell.MAX_DICE);
		assertTrue(subject.areaAllCellsFilledByPlayer(Player.PLAYER1));
		assertFalse(subject.areaAllCellsFilledByPlayer(Player.PLAYER3));
	}
	
	@Test
	public void addSomeCellsAndSetOwners_CheckIfLinkedCellCountIsRight(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		BoardCell cellLeftTop = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellMiddleTop = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellRightTop = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellLeftCenter = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellMiddleCenter = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellRightCenter = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellLeftBottom = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellMiddleBottom = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellRightBottom = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		
		subject.link(cellLeftTop, cellMiddleTop);
		subject.link(cellLeftTop, cellMiddleCenter);
		subject.link(cellLeftTop, cellLeftCenter);
		
		subject.link(cellMiddleTop,cellRightTop);
		subject.link(cellMiddleTop,cellRightCenter);
		subject.link(cellMiddleTop,cellMiddleCenter);
		subject.link(cellMiddleTop,cellLeftCenter);
		
		subject.link(cellRightTop,cellRightCenter);
		subject.link(cellRightTop,cellMiddleCenter);
		
		subject.link(cellLeftCenter, cellMiddleCenter);
		subject.link(cellLeftCenter, cellMiddleBottom);
		subject.link(cellLeftCenter, cellLeftBottom);
		
		subject.link(cellMiddleCenter,cellRightCenter);
		subject.link(cellMiddleCenter,cellRightBottom);
		subject.link(cellMiddleCenter,cellMiddleBottom);
		subject.link(cellMiddleCenter,cellLeftBottom);
		
		subject.link(cellRightCenter,cellRightBottom);
		subject.link(cellRightCenter,cellMiddleBottom);
		
		subject.link(cellLeftBottom,cellMiddleBottom);
		
		subject.link(cellMiddleBottom, cellRightBottom);
		
		cellLeftTop.setOwner(Player.PLAYER1);
		cellMiddleTop.setOwner(Player.PLAYER2);
		cellRightTop.setOwner(Player.PLAYER2);
		
		cellLeftCenter.setOwner(Player.PLAYER2);
		cellMiddleCenter.setOwner(Player.PLAYER2);
		cellRightCenter.setOwner(Player.PLAYER1);
		
		cellLeftBottom.setOwner(Player.PLAYER1);
		cellMiddleBottom.setOwner(Player.PLAYER1);
		cellRightBottom.setOwner(Player.PLAYER1);
		
		int linkedCount = subject.getBiggestLinkedCellCountForPlayer(Player.PLAYER1);
		assertEquals(4, linkedCount);
		
		int noLinkedCount = subject.getBiggestLinkedCellCountForPlayer(Player.PLAYER3);
		assertEquals(0, noLinkedCount);
	}
	
	@Test
	public void askIfRemovingCellWillLeaveOrphanCells(){
		BoardImpl subject = new BoardImpl();
		
		Polygon irrelevant = new Polygon();
		
		BoardCell cellLeft = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellMiddle = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		BoardCell cellRight = subject.createAndAddToBoardCellForPolygon(irrelevant, null);
		
		cellLeft.setDiceCount(1);
		cellMiddle.setDiceCount(2);
		cellRight.setDiceCount(3);
		
		subject.link(cellLeft,cellMiddle);
		subject.link(cellMiddle,cellRight);

		assertTrue(subject.removingCellWillLeaveOrphans(cellMiddle));
		assertFalse(subject.removingCellWillLeaveOrphans(cellLeft));
	}
}
