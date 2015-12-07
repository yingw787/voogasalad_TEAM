package rules;

import units.Unit;
import actions.DisappearAction;
import actions.IAction;

public class DisappearDialog extends AActionDialog {

	@Override
	public IAction ask(Unit currentUnit) {
		myAction = new DisappearAction();
		myDescription = "disappear from the board";
		return myAction;
	}

}
