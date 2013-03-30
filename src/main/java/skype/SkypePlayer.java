package skype;

import sliceWars.remote.messages.AllPlayersInvitesResult;
import sliceWars.remote.messages.RemotePlay;
import sliceWars.remote.messages.GameMessageListener;

import com.skype.Stream;

public class SkypePlayer implements GameMessageListener {

	private Stream _stream;
	
	public SkypePlayer(final Stream stream) {
		_stream = stream;
	}
	
	@Override
	public void play(RemotePlay play) {
		SkypeApp.sendMessageThroughStream(play, _stream);
	}

	@Override
	public void allPlayersInviteResult(AllPlayersInvitesResult allPlayersInvitesResult) {
		SkypeApp.sendMessageThroughStream(allPlayersInvitesResult, _stream);
	}

}
