package actions;

import java.util.Collection;

import gameEngine.environments.RuntimeEnvironment;
import units.Bullet;
import units.Faction;
import units.Point;
import units.Unit;
import units.UnitType;

public class ShootAction implements IAction{
	private Bullet myBullet;
	
	private double myRange;
	
	public ShootAction(Bullet bullet,double range){
		myBullet = bullet;
		myRange = range;
	}
	
	@Override
	public void act(Unit unit, RuntimeEnvironment re) {
		// TODO Spawn a new instance of myBullet at the actor's location 
		// with a target calculated from the actor's range
		Point myPoint = unit.getPoint();
		Point basePoint = re.getBase().getPoint();
		Collection<Unit> units = re.getUnits();
		Point target = null;
		double mmin = Double.MAX_VALUE;
		for (Unit i : units) {
			if (i.getType() == UnitType.Troop && i.getFaction() == Faction.enemy) {
				Point iPoint = i.getPoint();
				double distance = myPoint.getDistance(iPoint);
				double harmDistance = basePoint.getDistance(iPoint);
				if (distance < myRange) {
					if (mmin > harmDistance) {
						mmin = harmDistance;
						target = iPoint;
					}
				}
			}
		}
		if (target != null) {
			Bullet blt = new Bullet(myBullet.clone());
			
			
			double x = target.getX() - myPoint.getX();
			double y = target.getY() - myPoint.getY();
			double d = Math.sqrt(x*x + y*y);
			blt.setAttribute("SpedX", x/d);
			blt.setAttribute("SpedY", y/d);
			blt.setFaction(Faction.player);
			blt.setPoint(myPoint);
			re.addUnit(blt.getID(), blt);
		}
	}

}
