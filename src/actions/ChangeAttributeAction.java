package actions;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class ChangeAttributeAction implements IAction{
	private String myAttributeToChange;
	private double myChange;
	
	public ChangeAttributeAction(String attribute, double change){
		myAttributeToChange = attribute;
		myChange = change;
	}


	@Override
	public void act(Unit actor,RuntimeEnvironment re,Controller contronler) {
		// TODO Implement attributes as a map in Unit
		double currentValue = actor.getAttribute(myAttributeToChange);
		actor.setAttribute(myAttributeToChange, currentValue + myChange);
	}
	
}
