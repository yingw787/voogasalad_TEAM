package rules;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import units.Bullet;
import units.Unit;
import actions.IAction;
import actions.ShootAction;
import actions.StickAction;

public class StickDialog extends AActionDialog {

	@Override
	public IAction ask(Unit currentUnit) {
		double range = 0.0;
		int duration = 0;
		double ratio = 0.0;

		// Ask for range
		range = askForDouble("Tower Range", "Please enter a range (pixels) for the tower to slow troops", "Positive numbers only");
		
		// Ask for duration
		duration = (int) askForDouble("Stick Duration", "Please enter a duration (frames) for the troop to be slowed", "Positive integers only");
		
		// Ask for ratio
		ratio = askForDouble("Slow Ratio", "Please enter a ratio for the troop to be slowed", "Numbers between 0 and 1 only");
		
		if(range < 0 || duration < 0 || ratio < 0){
			return null;
		}
		myAction = new StickAction(range, duration, ratio);
		myDescription = "shoot a sticky bullet that slows troops for " + duration + " frames by " + ratio + " with range " + range;
		return myAction;
	}

	/**
	 * @param range
	 * @return
	 */
	private double askForDouble(String title, String prompt, String subtext) {
		double response = 0.0;
		Optional<String> result1 = askUserForText(title, prompt, subtext);
		if (result1.isPresent()){
			response = Double.parseDouble(result1.get());
		} else return -1.0;
		return response;
	}



}
