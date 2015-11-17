package gamedata.xml;

import java.util.ArrayList;
import java.util.List;

import units.Faction;
import units.Point;
import units.Tower;
import units.Troop;
import units.Unit;
import units.UnitType;

public class TestObjectHolder {

	Tower tower1;
	Tower tower2;
	Troop troop1;
	Troop troop2;
	List<Unit> myTowers = new ArrayList<Unit>();
	List<Unit> myTroops = new ArrayList<Unit>();
	List<String> myGames = new ArrayList<String>();

	public TestObjectHolder() {
		tower1 = new Tower("tower1", 100.0, 5.0, "turret.png", new Point(60.0, 80.0), 1, 200, 100);
		tower2 = new Tower("tower2", 200.0, 7.0, "turret_transparent.png", new Point(100.0, 135.0), 2, 400, 200);
		troop1 = new Troop("troop1", 2.0, 2.0, "purpleminion.png", new Point(75.0, 100.0), 1, 10, 2);
		troop2 = new Troop("troop2", 4.0, 3.0, "casterminion.png", new Point(150.0, 35.0), 2, 20, 4);
		myGames.add("Game 1");
		myGames.add("Game 2");
		myGames.add("Game 3");
		myTowers.add(tower1);
		myTowers.add(tower2);
		myTroops.add(troop1);
		myTroops.add(troop2);
	}
	
	public List<Unit> getTowers() {
		return myTowers;
	}
	
	public List<Unit> getTroops() {
		return myTroops;
	}
	
	public List<String> getGames() {
		return myGames;
	}
}
