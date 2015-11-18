package gameEngine.environments;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameEngine.GameConfiguration;
import groovy.ui.SystemOutputInterceptor;
import rules.Rule;
import units.Base;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Unit;

public class RuntimeEnvironment extends Environment {
	private Map<Integer,Unit> myUnitsMap;
	
	public RuntimeEnvironment() {
		super();
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	public RuntimeEnvironment(List<Unit> towers, List<Unit> troops, List<Level> levels, List<Path> paths, PlayerInfo playerInfo,
			GameConfiguration config, List<Rule> rules, Base base) {
		super(towers, troops, levels, paths, playerInfo, config, rules, base);
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	public HashMap<String, List<Unit>> getStoreStock(){
		HashMap<String, List<Unit>> stock = new HashMap<String,List<Unit>>();
		stock.put("Tower", this.myTowerTypes);
		stock.put("Troop", this.myTroopTypes);
		return stock;
	}
	
	public Unit getUnit(int id) {
		return myUnitsMap.get(id);
	}
	
	public void removeUnit(int id){
		myUnitsMap.remove(id);
	}

	public Collection<Unit> getUnits() {
		// TODO Auto-generated method stub
		return   myUnitsMap.values();
	}

	public void addUnit(int id, Unit t) {
		// TODO Auto-generated method stub
		myUnitsMap.put(id, t);
	}
	
}
