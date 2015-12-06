package actions;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public interface IAction {
	public void act(Unit unit,RuntimeEnvironment re,Controller contronler);


}
