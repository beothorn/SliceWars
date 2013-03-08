package sliceWars.logic.gameStates;

import sliceWars.logic.PlayOutcome;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.GameState;
import sliceWars.logic.gameStates.GameStateContext;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;

public class GameStateMockAdapter implements GameState {

	@Override
	public PlayOutcome play(int x, int y, GameStateContext gameStateContext) {
		return null;
	}
	
	@Override
	public String getPhaseName() {
		return null;
	}

	@Override
	public Player getWhoIsPlaying() {
		return null;
	}

	@Override
	public boolean canPass() {
		return false;
	}

	@Override
	public PlayOutcome pass(GameStateContext gameStateContext) {
		return null;
	}

	@Override
	public Phase getPhase() {
		return null;
	}


}
