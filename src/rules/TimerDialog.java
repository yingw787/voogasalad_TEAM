// This entire file is part of my masterpiece.
// William Yang

package rules;

import java.util.Optional;

import units.Unit;
import conditions.ICondition;
import conditions.TimerCondition;

public class TimerDialog extends AConditionDialog {

	/**
	 * Ask for the parameters to TimerCondition
	 */
	@Override
	public ICondition ask(Unit currentUnit) {
		int delay = 0;
		Optional<String> result = askUserForText("Timer Delay", "Enter a delay (frames) between actions", "Positive numbers only");
		if (result.isPresent()){
			delay = Integer.parseInt(result.get());
		} else return null;
		myCondition = new TimerCondition(delay);
		myDescription = "Every " + delay + " frames, ";
		return myCondition;
	}

}
