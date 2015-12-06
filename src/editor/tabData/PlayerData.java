package editor.tabData;

import java.util.ArrayList;
import java.util.List;

import units.Game;
import units.PlayerInfo;

public class PlayerData extends ATabData {
	
	private PlayerInfo myPlayerInfo;
	
	public PlayerData(){
		myPlayerInfo = new PlayerInfo();
	}

	public PlayerInfo getPlayerInfo(){
		return myPlayerInfo;
	}
	
	@Override
	public List<Object> getData() {
		List<Object> infoList = new ArrayList<Object>();
		infoList.add(myPlayerInfo);
		return infoList;
	}

	@Override
	public void setData(List<Object> list) {
		System.out.println(list);
		for (Object o : list) {
			PlayerInfo player = (PlayerInfo) o;
			myPlayerInfo = player;
		}
	}

}
