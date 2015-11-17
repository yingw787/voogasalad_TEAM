package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Point;
import units.Tower;

public class BuyTowerRequest extends Request {

	private Tower myTower;
	private Point myPoint;
	
	public BuyTowerRequest(Tower tower, Point p) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
		this.myPoint = p;
	}

	@Override
	protected void execute(RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		
	}

}
