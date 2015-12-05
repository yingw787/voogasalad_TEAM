package gameEngine.requests;

import controller.Controller;
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
	public void execute(RuntimeEnvironment re,Controller myController) {
		
		re.removeUnit(myTower.getID());
		
		int money = (new Double(re.getPlayerInfo().getMoney()+myTower.getAttribute("SellCost"))).intValue();
		re.getPlayerInfo().setMoney(money);
		
		super.update(re, myController);
	}

}
