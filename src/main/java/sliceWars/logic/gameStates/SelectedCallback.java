package sliceWars.logic.gameStates;

import sliceWars.logic.BoardCell;

public interface SelectedCallback {
	public void selectedOrNull(BoardCell selectedCellOrNull);
}
