package sliceWars;

import java.io.Serializable;

public class RemoteInvite implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final int _randomSeed;
	private final int _numberOfPlayers;
	private final int _lines;
	private final int _columns;
	private final int _randomScenariosCellsCount;

	public RemoteInvite(final int randomSeed, final int numberOfPlayers, final int lines, final int columns, final int randomScenariosCellsCount) {
		_randomSeed = randomSeed;
		_numberOfPlayers = numberOfPlayers;
		_lines = lines;
		_columns = columns;
		_randomScenariosCellsCount = randomScenariosCellsCount;
	}

	public int get_randomSeed() {
		return _randomSeed;
	}

	public int get_numberOfPlayers() {
		return _numberOfPlayers;
	}

	public int get_lines() {
		return _lines;
	}

	public int get_columns() {
		return _columns;
	}

	public int get_randomlyScenarioCells() {
		return _randomScenariosCellsCount;
	}

}
