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


/**
 * Environment contains all the units, rules and other configurations that make sense for the current game player
 * @author Wanning
 *
 */
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
	
	/**
	 * constructor
	 * @param
	 */
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
	
	/**
	 * constructor
	 * @param towers
	 * @param troops
	 * @param levels
	 * @param paths
	 * @param playerInfo
	 * @param config
	 * @param rules
	 * @param base
	 */
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

	/**
	 * an API for others to get the current rules
	 * @return a list of rules
	 */
	public List<Rule> getRules() {
		return myRules;
	}

	/**
	 * an API for setting new rules
	 * @param myRules the new rules
	 */
	public void setRules(List<Rule> myRules) {
		this.myRules = myRules;
	}

	/**
	 * an API for others to get the current name of game
	 * @return the name of game
	 */
	public String getGameName() {
		return myGameName;
	}

	/**
	 * an API for others to set a new name of game
	 * @param GameName the new name
	 */
	public void setGameName(String GameName) {
		myGameName = GameName;
	}

	/**
	 * an API for others to get the tower types in configuration
	 * @return all the possible tower types in this game
	 */
	public List<Unit> getTowerType() {
		return myTowerTypes;
	}

	/**
	 * an API for others to set the tower types
	 * @param TowerType
	 */
	public void setTowerType(List<Unit> TowerType) {
		this.myTowerTypes = TowerType;
	}

	/**
	 * an API for getting the troop types in configuration
	 * @return all the possible troop types in this game
	 */
	public List<Unit> getTroopType() {
		return myTroopTypes;
	}
	
	/**
	 * set the troop types
	 * @param TroopType
	 */
	public void setTroopType(List<Unit> TroopType) {
		this.myTroopTypes = TroopType;
	}

	/**
	 * get all the levels
	 * @return all the levels
	 */
	public List<Level> GetLevels() {
		return myLevels;
	}

	/**
	 * set all the levels
	 * @param Levels
	 */
	public void setLevels(List<Level> Levels) {
		this.myLevels = Levels;
	}

	/**
	 * an API for getting the path in configuration
	 * @return
	 */
	public List<Path> getPaths() {
		return myPaths;
	}

	/**
	 * set the paths
	 * @param Paths
	 */
	public void setPaths(List<Path> Paths) {
		this.myPaths = Paths;
	}

	/**
	 * an API for getting the player information
	 * @return the player information
	 */
	public PlayerInfo getPlayerInfo() {
		return myPlayerInfo;
	}
	
	/**
	 * an API for getting the ith level in configuration
	 * @param i the number of the level
	 * @return the level
	 */
	public Level getLevel(int i){
		return myLevels.get(i);
	}

	/**
	 * set the player information
	 * @param PlayerInfo
	 */
	public void setPlayerInfo(PlayerInfo PlayerInfo) {
		this.myPlayerInfo = PlayerInfo;
	}

	/**
	 * get the configuration
	 * @return
	 */
	public GameConfiguration getConfig() {
		return myConfig;
	}

	/**
	 * set the configuration
	 * @param Config
	 */
	public void setMyConfig(GameConfiguration Config) {
		this.myConfig = Config;
	}
	
	/**
	 * get the base
	 * @return
	 */
	public Base getBase() {
		return myBase;
	}
}
