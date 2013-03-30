package sliceWars.remote.messages;

import java.io.Serializable;

public class AnswerToTheInvitation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final boolean _accepted;
	private final String _contactName;
	
	public AnswerToTheInvitation(final String contactName, boolean accepted) {
		_contactName = contactName;
		_accepted = accepted;
	}

	public boolean isAccepted() {
		return _accepted;
	}

	public String getContactName() {
		return _contactName;
	}

}
