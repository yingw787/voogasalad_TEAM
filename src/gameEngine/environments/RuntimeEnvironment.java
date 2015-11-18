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

public class RuntimeEnvironment extends Environment {
	private Map<Integer,Unit> myUnitsMap;
	private IDGenerator myIDGenerator;
	public RuntimeEnvironment() {
		super();
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	public RuntimeEnvironment(List<Unit> towers, List<Unit> troops, List<Level> levels, List<Path> paths, PlayerInfo playerInfo,
			GameConfiguration config, List<Rule> rules, Base base, IDGenerator id) {
		super(towers, troops, levels, paths, playerInfo, config, rules, base);
		myIDGenerator = id;
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	public HashMap<String, List<Unit>> getStoreStock(){
		HashMap<String, List<Unit>> stock = new HashMap<String,List<Unit>>();
		stock.put("Towers", this.myTowerTypes);
		stock.put("Troops", this.myTroopTypes);
		return stock;
	}
	
	public Unit getUnit(int id) {
		return myUnitsMap.get(id);
	}
	
	public void removeUnit(int id){
		myUnitsMap.remove(id);
	}

	public List<Unit> getUnits() {
		// TODO Auto-generated method stub
		List<Unit> ret = new ArrayList<Unit>(myUnitsMap.values());
		return ret;
	}

	public void addUnit(int id, Unit t) {
		// TODO Auto-generated method stub
		myUnitsMap.put(id, t);
	}

	public void incrementLevel() {
		myPlayerInfo.setLevel(Integer.toString(Integer.parseInt(myPlayerInfo.getLevel())+1));
	}
	
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
	
	public int getNewID(){
		return myIDGenerator.getID();
	}
}
