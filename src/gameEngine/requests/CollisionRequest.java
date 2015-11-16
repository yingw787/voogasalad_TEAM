package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.*;

public class CollisionRequest {
	private int myUnitId1;
	private int myUnitId2;
	
	public CollisionRequest (int unitId1, int unitId2) {
		super();
		myUnitId1 = unitId1;
		myUnitId2 = unitId2;
	}
	
	protected void execute(RuntimeEnvironment re) {
		Unit unit1 = re.getUnit(myUnitId1);
		Unit unit2 = re.getUnit(myUnitId2);
	}
}
