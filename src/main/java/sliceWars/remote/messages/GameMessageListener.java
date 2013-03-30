package sliceWars.remote.messages;

public interface GameMessageListener {

	public void play(RemotePlay play);

	public void allPlayersInviteResult(final AllPlayersInvitesResult allPlayersInvitesResult);
	
}
