package sliceWars.remote.messages;

import java.io.Serializable;

public class AllPlayersInvitesResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final boolean _allPlayersHaveAccepted;
	
	public AllPlayersInvitesResult(boolean allPlayersAccepted) {
		_allPlayersHaveAccepted = allPlayersAccepted;
	}

	public boolean isAllPlayersHaveAccepted() {
		return _allPlayersHaveAccepted;
	}

}
