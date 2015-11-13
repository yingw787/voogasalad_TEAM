package conditions;

import units.Unit;

public class CollisionCondition implements ICondition{
	private String myCollider;
	
	public CollisionCondition(String collider){
		myCollider = collider;
	}
	
	
	@Override
	public boolean checkCondition(Unit actor) {
		// TODO Turn the pseudocode below into real code
//		if(actor.getIntersectingElement().getName().equals(myCollider)){
//			return true;
//		}
		return false;
	}

}
