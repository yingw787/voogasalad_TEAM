// This entire file is part of my masterpiece.
// cw272

package actions;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class DisappearAction implements IAction{

	@Override
	public void act(Unit unit,RuntimeEnvironment re,Controller contronler) {
		// TODO Remove/delete the actor from the game scene
		re.removeUnit(unit.getID());

		if (unit.getStringAttribute("Type").equals("Troop")){
			re.increaseMoney(unit.getAttribute("KillReward"));
			contronler.resetStore();
			contronler.updateUserInfo(re.getPlayerInfo());
			contronler.updateInfo(re.getPlayerInfo());
			
		}

	}

}
