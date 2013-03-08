package sliceWars.logic.gameStates;

import java.util.Collection;

import sliceWars.logic.BoardCell;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.AttackCallback;
import sliceWars.logic.gameStates.DiceLeftCallback;
import sliceWars.logic.gameStates.GameState;
import sliceWars.logic.gameStates.GameStateContext;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;
import sliceWars.logic.gameStates.PlayListener;
import sliceWars.logic.gameStates.SelectedCallback;

public class GameStateContextMock implements GameStateContext {

	protected GameState _state;

	@Override
	public void setState(GameState state) {
		_state = state;
	}

	@Override
	public Phase getPhase() {
		return _state.getPhase();
	}

	@Override
	public Player getWhoIsPlaying() {
		return _state.getWhoIsPlaying();
	}

	@Override
	public String getPhaseName() {
		return _state.getPhaseName();
	}

	@Override
	public void setAttackCallback(AttackCallback attackCallback) {
	}

	@Override
	public Collection<BoardCell> getBoardCells() {
		return null;
	}

	@Override
	public void pass() {
	}

	@Override
	public boolean canPass() {
		return false;
	}

	@Override
	public void play(int x, int y) {
	}

	@Override
	public void setDiceLeftCallback(DiceLeftCallback diceLeftCallback) {
	}

	@Override
	public void addPlayListener(PlayListener playCallback) {
	}

	@Override
	public void setSelectedCellCallback(SelectedCallback selectedCellCall) {
	}

}
