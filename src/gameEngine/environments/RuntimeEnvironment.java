package gameEngine.environments;

import java.util.HashMap;
import java.util.Map;

import units.Unit;

public class RuntimeEnvironment extends Environment {
	private Map<Integer,Unit> myUnitsMap;
	
	
	
	public RuntimeEnvironment() {
		super();
		myUnitsMap = new HashMap<Integer,Unit>();
	}
	
	public Unit getUnit(int id) {
		return myUnitsMap.get(id);
	}
	
}
