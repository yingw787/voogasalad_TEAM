package rules;

import units.Unit;
import conditions.ICondition;

public abstract class AConditionDialog extends ADialog{

	protected ICondition myCondition;
	
	public abstract ICondition ask(Unit currentUnit);

}
