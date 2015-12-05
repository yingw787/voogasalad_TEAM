package actions;

import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class DisappearAction implements IAction{

	@Override
	public void act(Unit unit,RuntimeEnvironment re) {
		// TODO Remove/delete the actor from the game scene
		re.removeUnit(unit.getID());

		if (unit.getStringAttribute("Type").equals("Troop")){
			re.increaseMoney(unit.getAttribute("SellCost"));
			
		}

	}

}
