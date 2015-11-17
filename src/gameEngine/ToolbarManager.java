package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import gameEngine.environments.InitialEnvironment;
import units.Tower;
import units.Troop;
import units.Unit;

public class ToolbarManager {

	/*
	 * This class handles the different toolbars available in the front-end. 
	 * This should be general enough to handle the different kinds of toolbars without needing to extend it too much. 
	 */
	Controller myController;
	List<Unit> myTowers;
	List<Unit> myTroops;
	
	public ToolbarManager(Controller controller,InitialEnvironment initialEnviorn){
		this.myController = controller;
		
		myTowers = new ArrayList<Unit>();
		myTroops = new ArrayList<Unit>();
		List<Tower>towers = initialEnviorn.getTowerType();
		List<Troop>troops = initialEnviorn.getTroopType();
		
		myTowers.addAll(towers);
		myTroops.addAll(troops);
		
		
		upLoadStore();
		
	}
	
	private void upLoadStore(){
		HashMap<String,List<Unit>>storeMap = new HashMap<String,List<Unit>>();
		
		storeMap.put("Towers", myTowers);
		storeMap.put("Troops", myTroops);
		
	   myController.populateStore(storeMap);
	}
	
	
}
