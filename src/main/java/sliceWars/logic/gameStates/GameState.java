package sliceWars.logic.gameStates;

import sliceWars.logic.PlayOutcome;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;

public interface GameState {	
	
	public PlayOutcome play(int x, int y, GameStateContext gameStateContext);
	public String getPhaseName();
	public Player getWhoIsPlaying();
	public boolean canPass();
	public PlayOutcome pass(GameStateContext gameStateContext);
	public Phase getPhase();
}