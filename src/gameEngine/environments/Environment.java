package gameEngine.environments;

import java.util.ArrayList;
import java.util.List;

import gameEngine.GameConfiguration;
import rules.Rule;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Tower;
import units.Troop;

public class Environment {
	String myGameName;
	List<Tower> myTowerType;
	List<Troop> myTroopType;
	List<Level> myLevels;
	List<Path> myPaths;
	PlayerInfo myPlayerInfo;
	GameConfiguration myConfig;
	List<Rule> myRules;
	
	public Environment() {
		myGameName = "Salad";
		myTowerType = new ArrayList<Tower>();
		myTroopType = new ArrayList<Troop>();
		myLevels = new ArrayList<Level>();
		myPaths =new ArrayList<Path>();
		myPlayerInfo = new PlayerInfo(0, 0, 0);
		myConfig = new GameConfiguration();
		myRules = new ArrayList<Rule>();
	}

	public List<Rule> getRules() {
		return myRules;
	}

	public void setRules(List<Rule> myRules) {
		this.myRules = myRules;
	}

	public String getGameName() {
		return myGameName;
	}

	public void setGameName(String GameName) {
		myGameName = GameName;
	}

	public List<Tower> getTowerType() {
		return myTowerType;
	}

	public void setTowerType(List<Tower> TowerType) {
		this.myTowerType = TowerType;
	}

	public List<Troop> getTroopType() {
		return myTroopType;
	}

	public void setTroopType(List<Troop> TroopType) {
		this.myTroopType = TroopType;
	}

	public List<Level> MyLevels() {
		return myLevels;
	}

	public void setLevels(List<Level> Levels) {
		this.myLevels = Levels;
	}

	public List<Path> getPaths() {
		return myPaths;
	}

	public void setPaths(List<Path> Paths) {
		this.myPaths = Paths;
	}

	public PlayerInfo getPlayerInfo() {
		return myPlayerInfo;
	}

	public void setPlayerInfo(PlayerInfo PlayerInfo) {
		this.myPlayerInfo = PlayerInfo;
	}

	public GameConfiguration getConfig() {
		return myConfig;
	}

	public void setMyConfig(GameConfiguration Config) {
		this.myConfig = Config;
	}
}
