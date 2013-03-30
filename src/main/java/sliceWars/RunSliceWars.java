package sliceWars;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import sliceWars.gui.GuiPlayer;
import sliceWars.logic.Player;
import sliceWars.remote.messages.AllPlayersInvitesResult;
import sliceWars.remote.messages.RemotePlay;
import sliceWars.remote.messages.GameMessageListener;

public class RunSliceWars implements GameMessageListener{

	private List<GuiPlayer> _players;

	public static void main(String[] args) {
		new RunSliceWars();
	}
	
	public RunSliceWars() {
		Random random = new Random(Calendar.getInstance().getTimeInMillis());
		int nextInt = random.nextInt();
		int numberOfPlayers = 2;
		int lines = 6;
		int columns = 6;
		int randomlyRemoveCells = 12;
				
		_players = new ArrayList<GuiPlayer>();
		
		Player player = new Player(1, numberOfPlayers);
		GuiPlayer player1 = new GuiPlayer(player, this, nextInt,numberOfPlayers,lines,columns,randomlyRemoveCells);
		player1.setKillOnClose();
		_players.add(player1);
		
		for (int i = 1; i < numberOfPlayers; i++) {
			player = player.next();
			GuiPlayer newGuiPlayer = new GuiPlayer(player, this, nextInt,numberOfPlayers,lines,columns,randomlyRemoveCells);
			newGuiPlayer.setKillOnClose();
			_players.add(newGuiPlayer);
		}
	}

	@Override
	public void play(RemotePlay play) {
		for (GuiPlayer guiPlayer : _players) {
			guiPlayer.play(play);
		}
	}

	@Override
	public void allPlayersInviteResult(
			AllPlayersInvitesResult allPlayersInvitesResult) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}
}
