package gameEngine.environments;

import java.util.Collection;
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
	
	public void removeUnit(int id){
		myUnitsMap.remove(id);
	}

	public  Collection<Unit> getUnits() {
		// TODO Auto-generated method stub
		return   myUnitsMap.values();
	}

	public void addUnit(int id, Unit t) {
		// TODO Auto-generated method stub
		myUnitsMap.put(id, t);
	}
}
