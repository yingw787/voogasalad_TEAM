package rules;

import units.Unit;
import actions.IAction;
import conditions.ICondition;

public class Rule {
	private ICondition myCondition;
	private IAction myAction;
	
	public Rule(ICondition condition, IAction action){
		this.myCondition = condition;
		this.myAction = action;
	}
	
	// Call this method with the containing unit as the parameter
	// For the CheckAttribute condition, run this method in the event handler for any changed attribute action
	// For the Collision condition, run this method in the event handler for intersects
	// For the Time condition, check the TimerCondition class for instructions
	public void run(Unit actor){
		if(myCondition.checkCondition(actor)){
			myAction.act(actor);
		}
	}
}
