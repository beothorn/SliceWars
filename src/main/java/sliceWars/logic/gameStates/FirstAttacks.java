package sliceWars.logic.gameStates;

import sliceWars.logic.Board;
import sliceWars.logic.PlayOutcome;
import sliceWars.logic.Player;
import sliceWars.logic.gameStates.GameStateContextImpl.Phase;

public class FirstAttacks implements GameState {

	private Board _board;
	private Attack _attackPhase;

	public FirstAttacks(Player currentPlayer, Board board) {
		_board = board;
		_attackPhase = new Attack(currentPlayer,board);
	}

	@Override
	public PlayOutcome play(int x, int y, GameStateContext gameStateContext){
		PlayOutcome playOutcome = _attackPhase.play(x, y, gameStateContext);
		gameStateContext.setState(this);
		return playOutcome;
	}

	@Override
	public String getPhaseName() {
		return "Attack phase";
	}

	@Override
	public Player getWhoIsPlaying() {
		return _attackPhase.getWhoIsPlaying();
	}

	@Override
	public boolean canPass() {
		return true;
	}

	@Override
	public PlayOutcome pass(GameStateContext gameStateContext){
		if(_attackPhase.getWhoIsPlaying().isLastPlayer()){
			DiceDistribution diceDistribution = new DiceDistribution(_attackPhase.getWhoIsPlaying().next(),_board);
			gameStateContext.setState(diceDistribution);
			return new PlayOutcome(diceDistribution.getDiceToAdd());
		}
		_attackPhase = new Attack(_attackPhase.getWhoIsPlaying().next(),_board);
		return null;
	}

	@Override
	public Phase getPhase(){
		return Phase.FIRST_ATTACKS;
	}
}
