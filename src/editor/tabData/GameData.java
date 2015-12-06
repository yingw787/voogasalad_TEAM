package editor.tabData;

import java.util.ArrayList;
import java.util.List;

import units.Game;

public class GameData extends ATabData {
	private Game myGame;
	
	public GameData(){
		myGame = new Game();
	}
	
	public Game getGame(){
		return myGame;
	}
	
	@Override
	public List<Object> getData() {
		List<Object> gameList = new ArrayList<Object>();
		gameList.add(myGame);
		return gameList;
	}

	@Override
	public void setData(List<Object> list) {
		for (Object o : list) {
			Game game = (Game) o;
			myGame = game;
		}
	}
}
