package conditions;

import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class CheckAttributeCondition implements ICondition{
	private String myAttributeToCheck;
	private double myLowerBound;
	private double myUpperBound;
	
	public CheckAttributeCondition(String attribute, double lower, double upper){
		myAttributeToCheck = attribute;
		myLowerBound = lower;
		myUpperBound = upper;
	}

	@Override
	public boolean checkCondition(Unit actor,RuntimeEnvironment re) {
		// TODO Implement storing attributes in a map in Unit
		double currentValue = actor.getAttribute(myAttributeToCheck);
		if(currentValue >= myLowerBound && currentValue <= myUpperBound){
			return true;
		}
		return false;
	}
	
	public CheckAttributeCondition clone(){
		CheckAttributeCondition cac = new CheckAttributeCondition(myAttributeToCheck,myLowerBound,myUpperBound);
		
		return cac;
	}

}
