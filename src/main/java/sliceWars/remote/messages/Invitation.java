package sliceWars.remote.messages;

import java.io.Serializable;
import java.util.List;

public class Invitation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final int _randomSeed;
	private final int _playerNumber;
	private final int _lines;
	private final int _columns;
	private final int _randomScenariosCellsCount;
	private final List<String> _playerList;

	public Invitation(final int randomSeed, final List<String> playerList, final int playerNumber, final int lines, final int columns, final int randomScenariosCellsCount) {
		_randomSeed = randomSeed;
		_playerList = playerList;
		_playerNumber = playerNumber;
		_lines = lines;
		_columns = columns;
		_randomScenariosCellsCount = randomScenariosCellsCount;
	}

	public int getRandomSeed() {
		return _randomSeed;
	}

	public int getPlayersCount() {
		return _playerList.size()+1;
	}

	public int getLines() {
		return _lines;
	}

	public int getColumns() {
		return _columns;
	}

	public int getRandomlyScenarioCells() {
		return _randomScenariosCellsCount;
	}

	public int getPlayerNumber() {
		return _playerNumber;
	}

	public List<String> getPlayerNames() {
		return _playerList;
	}

}
