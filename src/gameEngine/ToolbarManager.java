package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import gameEngine.environments.InitialEnvironment;
import gamedata.xml.XMLConverter;
import units.Unit;

public class ToolbarManager {

	/*
	 * This class handles the different toolbars available in the front-end. 
	 * This should be general enough to handle the different kinds of toolbars without needing to extend it too much. 
	 */
	Controller myController;
	List<Unit> myTowers;
	List<Unit> myTroops;
	
	public ToolbarManager(Controller controller) throws IOException{
		this.myController = controller;
		
		myTowers = new ArrayList<Unit>();
		myTroops = new ArrayList<Unit>();
		
		XMLConverter myConverter = new XMLConverter();
		
		List<Unit> towers = myConverter.fromXML("Tower");
		List<Unit> troops = myConverter.fromXML("Troop");
		
		myTowers.addAll(towers);
		myTroops.addAll(troops);
		
		upLoadStore();
		
	}
	
	public void upLoadStore(){
		HashMap<String,List<Unit>>storeMap = new HashMap<String,List<Unit>>();
		
		storeMap.put("Towers", myTowers);
		storeMap.put("Troops", myTroops);
		
	   myController.populateStore(storeMap);
	}
	
	
}
