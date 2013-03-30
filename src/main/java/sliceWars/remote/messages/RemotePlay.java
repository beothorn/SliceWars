package sliceWars.remote.messages;

import java.io.Serializable;

public class RemotePlay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int _x;
	private final int _y;
	private final int _id;
	private static int idGen = 0;
	
	public RemotePlay(final int x,final int y) {
		_x = x;
		_y = y;
		_id = idGen++;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public int getId() {
		return _id;
	}

}
