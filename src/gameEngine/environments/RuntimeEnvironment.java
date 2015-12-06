package gameEngine.environments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameEngine.GameConfiguration;
import rules.Rule;
import units.Base;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Unit;


/**
 * The runtime environment
 * @author Wanning
 *
 */
public class RuntimeEnvironment  {
	
	protected String myGameName;
	protected List<Unit> myTowerTypes;
	protected List<Unit> myTroopTypes;
	protected List<Level> myLevels;
	protected List<Path> myPaths;
	protected PlayerInfo myPlayerInfo;
	protected GameConfiguration myConfig;
	protected List<Rule> myRules;
	protected Base myBase;
	private Map<Integer,Unit> myUnitsMap;

	
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
	 * @param id
	 */
	public RuntimeEnvironment(List<Unit> towers, List<Unit> troops, List<Level> levels, List<Path> paths, PlayerInfo playerInfo,
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
		
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	/**
	 * get the available items in stock
	 * @return
	 */
	public HashMap<String, List<Unit>> getStoreStock(){
		HashMap<String, List<Unit>> stock = new HashMap<String,List<Unit>>();
		stock.put("Towers", this.myTowerTypes);
		stock.put("Troops", this.myTroopTypes);
		return stock;
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
		myPlayerInfo.setMyLevelSize(myLevels.size());
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
	
	public int getlevelSize(){
		return myLevels.size();
		
	}
	
	public void increaseMoney(double d){
		myPlayerInfo.setMoney((int) (myPlayerInfo.getMoney()+d));
	}
	
	
	/**
	 * API to get the units of the specific ID
	 * @param id
	 * @return
	 */
	public Unit getUnit(int id) {
		return myUnitsMap.get(id);
	}
	
	/**
	 * API to remove a unit
	 * @param id
	 */
	public void removeUnit(int id){
		myUnitsMap.remove(id);
	}

	/**
	 * get all the units
	 * @return
	 */
	public List<Unit> getUnits() {
		// TODO Auto-generated method stub
		List<Unit> ret = new ArrayList<Unit>(myUnitsMap.values());
		//System.out.println("unit size:" + ret.size());
		return ret;
	}

	/**
	 * add a unit to the map
	 * @param id
	 * @param t
	 */
	public void addUnit(int id, Unit t) {
		// TODO Auto-generated method stub
		myUnitsMap.put(id, t);
	}

	/**
	 * jump to next level
	 */
	public void incrementLevel() {
		myPlayerInfo.setLevel(Integer.toString(Integer.parseInt(myPlayerInfo.getLevel())+1));
	}
	
	/**
	 * get the path for current level
	 * @param pathNames
	 * @return
	 */
	public List<Path> getPathsForLevel(List<String> pathNames){
		List<Path> ret = new ArrayList<Path>();
		for (String s : pathNames){
			for (Path p : myPaths){
				if (s.equals(p.getName())){
					ret.add(p);
				}
			}
		}
		return ret;
	}
	
	
	
}
