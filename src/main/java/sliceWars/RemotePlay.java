package sliceWars;

import java.io.Serializable;

public class RemotePlay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int _x;
	private final int _y;

	public RemotePlay(final int x,final int y) {
		_x = x;
		_y = y;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

}
