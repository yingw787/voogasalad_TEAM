package actions;

import gameEngine.environments.RuntimeEnvironment;
import units.Bullet;
import units.Unit;

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
		
	}

}
