package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Tower;

public class SellTowerRequest extends Request {

	Tower myTower;
	
	public SellTowerRequest(Tower tower) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
	}

	@Override
	public void execute(RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		
	}

}
