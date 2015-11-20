package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Tower;

/**
 * request for selling a tower
 * @author Wanning
 *
 */
public class SellTowerRequest extends Request {

	Tower myTower;
	
	/**
	 * constructor
	 * @param tower
	 */
	public SellTowerRequest(Tower tower) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
	}

	/**
	 * execute the sell tower logic
	 */
	@Override
	public void execute(RuntimeEnvironment re) {
		
		re.removeUnit(myTower.getID());
		
		int money = (new Double(re.getPlayerInfo().getMoney()+myTower.getAttribute("SellCost"))).intValue();
		re.getPlayerInfo().setMoney(money);
		
	}

}
