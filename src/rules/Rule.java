package rules;

import actions.IAction;
import conditions.ICondition;
import units.Unit;

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

//collision request{unit ID1, unit ID2}
//find in map: Unit unit1(bullet1), Unit unit2(tower2)
//collison_rule.run(unit1)
//collision_rule {
// myCondiiton = collison _condition
// myAction = 
//}
//

