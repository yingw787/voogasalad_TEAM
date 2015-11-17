package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Tower;

public class BuyTowerRequest extends Request {

	private Tower myTower;
	
	public BuyTowerRequest( Tower tower) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
	}

	@Override
	protected void execute(RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		
	}

}
