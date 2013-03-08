package sliceWars.logic.gameStates;

import java.util.Collection;

import sliceWars.logic.BoardCell;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;

public interface GameStateContext {
	public abstract void setState(GameState state);
	public abstract Phase getPhase();
	public abstract Player getWhoIsPlaying();
	public abstract String getPhaseName();
	public abstract void addPlayListener(PlayListener playListener);
	public abstract void setAttackCallback(AttackCallback attackCallback);
	public abstract void setDiceLeftCallback(DiceLeftCallback diceLeftCallback);
	public abstract void setSelectedCellCallback(SelectedCallback selectedCellCall);
	public abstract Collection<BoardCell> getBoardCells();
	public abstract void pass();
	public abstract boolean canPass();
	public abstract void play(int x, int y);
}