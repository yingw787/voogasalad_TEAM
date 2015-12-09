package rules;

import java.util.Optional;

import units.Unit;
import conditions.ICondition;
import conditions.TimerCondition;

public class TimerDialog extends AConditionDialog {

	@Override
	public ICondition ask(Unit currentUnit) {
		// Ask for delay
		int delay = 0;
		Optional<String> result = askUserForText("Timer Delay", "Enter a delay (ms) between actions", "Positive numbers only");
		if (result.isPresent()){
			delay = Integer.parseInt(result.get());
		} else return null;
		myCondition = new TimerCondition(delay);
		myDescription = "Every " + delay + "milliseconds, ";
		return myCondition;
	}

}
