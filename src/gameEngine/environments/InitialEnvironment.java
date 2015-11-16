package gameEngine.environments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import units.Unit;

public class InitialEnvironment extends Environment {
	
	private Map<String, List<Unit>> myStoreMap;
	
	public InitialEnvironment () {
		super();
		myStoreMap = new HashMap<String, List<Unit> >();
	}

}
