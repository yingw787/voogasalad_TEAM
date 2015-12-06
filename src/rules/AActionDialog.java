package rules;

import units.Unit;
import actions.IAction;

public abstract class AActionDialog extends ADialog {
	
	protected IAction myAction;
	
	public abstract IAction ask(Unit currentUnit);

}
