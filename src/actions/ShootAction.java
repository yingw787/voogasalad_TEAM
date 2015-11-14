package actions;

import units.Bullet;
import units.Unit;

public class ShootAction implements IAction{
	private Bullet myBullet;
	
	public ShootAction(Bullet bullet){
		myBullet = bullet;
	}
	
	@Override
	public void act(Unit actor) {
		// TODO Spawn a new instance of myBullet at the actor's location 
		// with a target calculated from the actor's range
		
	}

}
