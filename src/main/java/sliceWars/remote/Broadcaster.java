package sliceWars.remote;

import java.util.ArrayList;
import java.util.List;

import sliceWars.remote.messages.AllPlayersInvitesResult;
import sliceWars.remote.messages.RemotePlay;
import sliceWars.remote.messages.GameMessageListener;

public class Broadcaster implements GameMessageListener{

	private GameMessageListener local;
	private List<GameMessageListener> remotePlayers;

	public Broadcaster() {
		remotePlayers = new ArrayList<GameMessageListener>();
	}
	
	public void setLocalPlayer(GameMessageListener local){
		this.local = local;
	}
	
	public void addRemotePlayer(final GameMessageListener remotePlayer){
		remotePlayers.add(remotePlayer);
	}
	
	@Override
	public void play(RemotePlay play) {
		sendPlayToOtherPlayers(play);
		sendPlayToMyself(play);
	}

	private void sendPlayToMyself(RemotePlay play) {
		local.play(play);
	}

	private void sendPlayToOtherPlayers(RemotePlay play) {
		for (GameMessageListener player : remotePlayers) {
			player.play(play);
		}
	}

	@Override
	public void allPlayersInviteResult(AllPlayersInvitesResult allPlayersInvitesResult) {
		for (GameMessageListener player : remotePlayers) {
			player.allPlayersInviteResult(allPlayersInvitesResult);
		}
	}

	public int getPlayerCount() {
		return remotePlayers.size();
	}	

}
