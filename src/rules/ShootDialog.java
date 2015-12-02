package rules;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import editor.tabData.BulletsData;
import units.Bullet;
import units.Unit;
import actions.IAction;
import actions.ShootAction;

public class ShootDialog extends AActionDialog {

	@Override
	public IAction ask(Unit currentUnit) {
		double range = 0.0;

		String[] availableBullets = ((BulletsData) myDataController.getData("Bullets")).getBulletNamesArray();
		
		if(availableBullets.length == 0){
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setTitle("Warning");
			warning.setHeaderText("You're trying to shoot a bullet...");
			warning.setContentText("But you haven't made any bullets yet!");
			warning.show();
			return null;
		}
		String bulletName = askUser(availableBullets, "Please select a bullet to shoot");
		Bullet bullet = ((BulletsData) myDataController.getData("Bullets")).get(bulletName);
		Optional<String> result2 = askUserForText("Tower Range", "Please enter a range (pixels) for the tower to shoot this bullet", "Positive numbers only");
		if (result2.isPresent()){
			range = Double.parseDouble(result2.get());
		} else return null;
		myAction = new ShootAction(bullet, range);
		myDescription = "shoot a " + bulletName + " with range " + range;
		return myAction;
	}

}
