package sliceWars.remote;

import sliceWars.remote.messages.AnswerToTheInvitation;

public interface Game {

	public void answerToTheInvitation(final AnswerToTheInvitation received);

}
