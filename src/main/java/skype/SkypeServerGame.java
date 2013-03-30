package skype;

import com.skype.Stream;

import sliceWars.remote.Game;
import sliceWars.remote.messages.AnswerToTheInvitation;

public class SkypeServerGame implements Game {

	private Stream _stream;

	public SkypeServerGame(final Stream stream) {
		_stream = stream;
	}
	
	@Override
	public void answerToTheInvitation(AnswerToTheInvitation answerToTheInvitation) {
		SkypeApp.sendMessageThroughStream(answerToTheInvitation, _stream);
	}

}
