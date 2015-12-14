// This entire file is part of my masterpiece.
// William Yang

package rules;

import java.util.Optional;

import units.Unit;
import actions.ChangeAttributeAction;
import actions.IAction;

public class ChangeAttributeDialog extends AActionDialog {

	/**
	 * Ask for the parameters to ChangeAttributeAction
	 */
	@Override
	public IAction ask(Unit currentUnit) {
		double change = 0.0;
		String[] availableAttributes = currentUnit.getAttributeArray();
		String attribute = askUser(availableAttributes, "Please select an Attribute to change");
		Optional<String> result = askUserForText("Change value", "Please enter a number to be added to the current value", 
				"Positive or negative numbers only");
		if (result.isPresent()){
			change = Double.parseDouble(result.get());
		} else return null;
		myAction = new ChangeAttributeAction(attribute, change);
		myDescription = "change " + attribute + " by " + change;
		return myAction;
	}

}
