package sliceWars.logic.gameStates;

import sliceWars.logic.AttackOutcome;

public interface AttackCallback {

	public void attackedWithOutcome(AttackOutcome attackOutcome);

}
