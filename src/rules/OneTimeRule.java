// This entire file is part of my masterpiece.
// Wanning Jiang

package rules;

import actions.IAction;
import conditions.ICondition;
import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class OneTimeRule extends Rule {

	public OneTimeRule(ICondition condition, IAction action) {
		super(condition, action);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(Unit unit,RuntimeEnvironment re,Controller controller){
//		super.run(unit, re, controller);
//		unit.removeRules(this);
		if (super.myCondition.checkCondition(unit, re)) {
			super.getAction().act(unit, re, controller);
			unit.removeRules(this);
		}
	}
}
