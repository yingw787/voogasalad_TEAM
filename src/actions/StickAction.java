package actions;

import java.util.Collection;

import conditions.ICondition;
import conditions.TimerCondition;
import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import rules.Rule;
import units.Faction;
import units.Point;
import units.Unit;

public class StickAction implements IAction {
	private double myRange;
	private double myRatio;
	private int myLastTime;
	
	public StickAction(double range, int lastTime, double ratio) {
		myRange = range;
		myLastTime = lastTime;
		myRatio = ratio;
	}
	
	@Override
	public void act(Unit unit, RuntimeEnvironment re, Controller contronler) {
		Point myPoint = unit.getPoint();
		ICondition tc = new TimerCondition(myLastTime); 
		Collection<Unit> units = re.getUnits();
		for (Unit i : units) {
			if ("Troop".equals(i.getStringAttribute("Type")) && i.getFaction() == Faction.enemy) {
				Point iPoint = i.getPoint();
				double distance = myPoint.getDistance(iPoint);
				if (distance < myRange) {
					IAction cac = new ChangeAttributeAction("Speed", i.getSpeed());
					i.setSpeed(i.getSpeed() * myRatio);
					Rule myRule = new Rule(tc, cac);
					i.setRule("stick", myRule);
				}
			}
		}
	}

}
