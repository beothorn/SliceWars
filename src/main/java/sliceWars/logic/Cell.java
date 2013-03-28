package sliceWars.logic;

public class Cell {
	public static final int MAX_DICE = 6;
	private int diceCount = 0;
	public Player owner = Player.EMPTY;
	public int getDiceCount() {
		return diceCount;
	}
	
	public boolean canAddDie(){
		boolean didNotExcedMaximunDiceValue = getDiceCount()+1<=MAX_DICE;
		boolean isNotScenario = !owner.equals(Player.SCENARIO);
		return didNotExcedMaximunDiceValue && isNotScenario;
	}
	
	public void addDie() {
		if(!canAddDie()) throw new RuntimeException("Can't add die");
		setDiceCount(getDiceCount() + 1);
	}

	public void setDiceCount(int newDiceCount) {
		diceCount = newDiceCount;
	}

}
