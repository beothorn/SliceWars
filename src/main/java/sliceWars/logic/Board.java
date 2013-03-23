package sliceWars.logic;

import java.util.Collection;

public interface Board {

	public BoardCell getCellAtOrNull(int x, int y);
	public boolean isFilled();
	public Collection<BoardCell> getBoardCells();
	public int getCellCount();
	public int getBiggestLinkedCellCountForPlayer(Player player);
	public abstract boolean areLinked(BoardCell c1, BoardCell c2);
	public boolean areaAllCellsFilledByPlayer(Player currentPlaying);
	public abstract void remove(BoardCell cell);
	public abstract boolean removingCellWillLeaveOrphans(BoardCell cell);
	public int getCellCountForPlayer(Player player);
	int getValidCellsCount();

}