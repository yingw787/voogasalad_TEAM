package gameEngine;

import java.io.IOException;
import java.util.List;

import gamedata.xml.XMLConverter;
import units.Level;
import units.PlayerInfo;
import units.Unit;

public class ToolbarManager {

	/*
	 * This class handles the different toolbars available in the front-end. 
	 * This should be general enough to handle the different kinds of toolbars without needing to extend it too much. 
	 */
	private Engine myEngine;
	
	public ToolbarManager(Engine e) throws IOException{
		this.myEngine = e;

//		upLoadStore();
		
	}
	
//	public void upLoadStore(){
//		HashMap<String,List<Unit>>storeMap = new HashMap<String,List<Unit>>();
//		
//		storeMap.put("Towers", myTowers);
//		storeMap.put("Troops", myTroops);
//		
//	   myController.populateStore(storeMap);
//	}
	
	
}
