package rules;

import java.util.Optional;

import units.Unit;
import conditions.CheckAttributeCondition;
import conditions.ICondition;

public class CheckAttributeDialog extends AConditionDialog {

	@Override
	public ICondition ask(Unit currentUnit) {
		// Ask for attribute to change
		String[] availableAttributes = currentUnit.getAttributeArray();
		String attribute = askUser(availableAttributes, "Attribute");
		System.out.println(attribute + " chosen");

		double lower = 0.0;
		double higher = 0.0;
		String title = "Bounding Values";
		String header = "Please Enter a Lower Bound";
		String content = "Please enter a number:";
		// Ask for lower bound
		Optional<String> result = askUserForText(title, header, content);
		if (result.isPresent()){
			lower = Double.parseDouble(result.get());
		} else return null;
		// Ask for higher bound
		header = "Please Enter a Higher Bound";
		result = askUserForText(title, header, content);
		if (result.isPresent()){
			higher = Double.parseDouble(result.get());
		} else return null;				
		myCondition = new CheckAttributeCondition(attribute, lower, higher);
		myDescription = "When " + attribute + " is in (" + lower + ", " + higher + "), ";
		return myCondition;
	}

}
