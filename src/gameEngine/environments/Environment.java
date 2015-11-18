package gameEngine.environments;

import java.util.ArrayList;
import java.util.List;

import gameEngine.GameConfiguration;
import rules.Rule;
import units.Base;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Unit;
import units.Unit;
import units.Unit;

public class Environment {
	protected String myGameName;
	protected List<Unit> myTowerTypes;
	protected List<Unit> myTroopTypes;
	protected List<Level> myLevels;
	protected List<Path> myPaths;
	protected PlayerInfo myPlayerInfo;
	protected GameConfiguration myConfig;
	protected List<Rule> myRules;
	protected Base myBase;
	
	public Environment(){
		myTowerTypes = new ArrayList<Unit>();
		myTroopTypes = new ArrayList<Unit>();
		myLevels = new ArrayList<Level>();
		myPaths =new ArrayList<Path>();
		myPlayerInfo = new PlayerInfo(0, 0, "");
		myConfig = new GameConfiguration();
		myRules = new ArrayList<Rule>();
		myBase = new Base();
	}
	
	public Environment(List<Unit> towers, List<Unit> troops, List<Level> levels, List<Path> paths, PlayerInfo playerInfo,
			GameConfiguration config, List<Rule> rules, Base base) {
		myGameName = "Salad";
		myTowerTypes = towers;
		myTroopTypes = troops;
		myLevels = levels;
		myPaths = paths;
		myPlayerInfo = playerInfo;
		myConfig = config;
		myRules = rules;
		myBase = base;
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

	public List<Unit> getTowerType() {
		return myTowerTypes;
	}

	public void setTowerType(List<Unit> TowerType) {
		this.myTowerTypes = TowerType;
	}

	public List<Unit> getTroopType() {
		return myTroopTypes;
	}

	public void setTroopType(List<Unit> TroopType) {
		this.myTroopTypes = TroopType;
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
	
	public Base getBase() {
		return myBase;
	}
}
