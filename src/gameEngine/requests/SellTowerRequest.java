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
		
		re.removeUnit(myTower.getID());
		
		int money = (new Double(re.getPlayerInfo().getMoney()+myTower.getAttribute("SellCost"))).intValue();
		re.getPlayerInfo().setMoney(money);
		
	}

}
