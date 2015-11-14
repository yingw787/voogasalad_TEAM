package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import interfaces.IEngine;
import interfaces.Request;
import units.PlayerInfo;
import units.Point;
import units.Tower;
import units.Troop;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	
	public Engine(Controller controller) {
		myController = controller;
		//read in XML
		//populate the View
	}

	public void testCaseMaker(){
		PlayerInfo playerinfo = new PlayerInfo(200, 3, 1);
		myController.updateUserInfo(playerinfo);
		HashMap<String, List<Unit>> myTestMap = new HashMap<String, List<Unit>>();
		List<Unit> TowerList = new ArrayList<Unit>();
		Tower t1 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(10,20), 1, 150, 75);
		Tower t2 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(20,40), 2, 150, 75);
		Tower t3 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(100,30), 3, 150, 75);
		Tower t4 = new Tower("Attack Turret", 200.0, 25.0, "turret.png", 
				new Point(50,20), 4, 250, 155);
		TowerList.add(t1);
		TowerList.add(t2);
		TowerList.add(t3);
		TowerList.add(t4);
		myTestMap.put("Towers", TowerList);
		List<Unit> TroopList = new ArrayList<Unit>();
		Troop tr1 = new Troop("Basic Minion", 50.0, 2.0, "purpleminion.png",
				new Point(290,30), 5, 50, 0);
		Troop tr2 = new Troop("Basic Minion", 50.0, 2.0, "purpleminion.png",
				new Point(130,130), 6, 50, 0);
		Troop tr3 = new Troop("Caster Minion", 150.0, 5.0, "casterminion.png",
				new Point(230,230), 7, 250, 0);
		TroopList.add(tr1);
		TroopList.add(tr2);
		TroopList.add(tr3);
		myTestMap.put("Troops", TroopList);
		myController.populateStore(myTestMap);
		List<Unit> mapUnits = new ArrayList<Unit>();
		List<Unit> mapUnits2 = new ArrayList<Unit>();
		mapUnits.addAll(TroopList);
		mapUnits.addAll(TowerList);
		myController.updateMap(mapUnits);

	}
	
	@Override
	public void update(List<Request> requests) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNewGame(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startWave(int i) {
		// TODO Auto-generated method stub
		
	}

}
