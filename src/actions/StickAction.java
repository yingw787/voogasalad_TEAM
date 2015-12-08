package actions;

import java.util.Collection;
import java.util.Set;

import conditions.ICondition;
import conditions.TimerCondition;
import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import rules.OneTimeRule;
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
		Collection<Unit> units = re.getUnits();
		for (Unit i : units) {
			if ("Troop".equals(i.getStringAttribute("Type")) && i.getFaction() == Faction.enemy) {
				Point iPoint = i.getPoint();
				double distance = myPoint.getDistance(iPoint);
				if (distance < myRange) {
					Rule checkStick = i.getRule("Stick");
					if (checkStick == null) {
						ICondition tc = new TimerCondition(myLastTime); 
						IAction cac = new ChangeAttributeAction("Speed", i.getAttribute("Speed"));
						i.setAttribute("Speed", i.getAttribute("Speed") * myRatio);
						Rule myRule = new OneTimeRule(tc, cac);
						i.setRule("Stick", myRule);
					}
				}
			}
		}
	}

}
