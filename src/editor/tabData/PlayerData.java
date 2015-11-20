package editor.tabData;

import java.util.ArrayList;
import java.util.List;

import units.PlayerInfo;

public class PlayerData implements ITabData {
	
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

}
