package gamedata.xml;

import java.util.ArrayList;
import java.util.List;

import units.Level;
import units.PlayerInfo;
import units.Point;
import units.Tower;
import units.Troop;
import units.Unit;

public class TestObjectHolder {

	Tower tower1;
	Tower tower2;
	Troop troop1;
	Troop troop2;
	Level level1;
	Level level2;
	PlayerInfo playerInfo;
	List<Troop> level1Troops = new ArrayList<Troop>();
	List<Troop> level2Troops = new ArrayList<Troop>();
	List<Level> myLevels = new ArrayList<Level>();
	List<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
	List<String> myPaths = new ArrayList<String>();
	List<Unit> myTowers = new ArrayList<Unit>();
	List<Unit> myTroops = new ArrayList<Unit>();
	List<String> myGames = new ArrayList<String>();

	public TestObjectHolder() {
		
		// towers and troops
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
		
		// levels
		myPaths.add("Path1");
		myPaths.add("Path2");
		level1Troops.add(troop1);
		level1Troops.add(troop1);
		level1Troops.add(troop2);
		level1Troops.add(troop2);
		level1Troops.add(troop1);
		level1Troops.add(troop2);
		level2Troops.add(troop2);
		level2Troops.add(troop1);
		level2Troops.add(troop2);
		level1Troops.add(troop1);
		level1Troops.add(troop1);
		level1Troops.add(troop1);
		
		level1 = new Level("level1", level1Troops, myPaths, 1.0, 3.0);
		level2 = new Level("level2", level2Troops, myPaths, 1.5, 2.0);
		myLevels.add(level1);
		myLevels.add(level2);
		
		// player info
		playerInfo = new PlayerInfo(100, 3, "level1");
		playerList.add(playerInfo);
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
	
	public List<Level> getLevels() {
		return myLevels;
	}
	
	public List<PlayerInfo> getPlayerInfo() {
		return playerList;
	}
}
