// This entire file is part of my masterpiece.
// cw272
package rules;

import actions.IAction;
import conditions.ICondition;
import controller.Controller;
import units.Unit;
import gameEngine.environments.RuntimeEnvironment;

public class Rule {
	protected ICondition myCondition;
	protected IAction myAction;
	
	public Rule(ICondition condition, IAction action){
		this.myCondition = condition;
		this.myAction = action;
	}
	
	
	// Call this method with the containing unit as the parameter
	// For the CheckAttribute condition, run this method in the event handler for any changed attribute action
	// For the Collision condition, run this method in the event handler for intersects
	// For the Time condition, check the TimerCondition class for instructions
	public void run(Unit unit,RuntimeEnvironment re,Controller contronler){
		if(myCondition.checkCondition(unit,re)){
			myAction.act(unit,re,contronler);
		}
	}
	
	public Rule clone(){
		Rule rule = new Rule(this.myCondition.clone(),this.myAction);
		return rule;
	}
	
	public IAction getAction(){
		return myAction;
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

