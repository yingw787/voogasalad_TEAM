package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Faction;
import units.Point;
import units.Tower;
import units.Unit;

/**
 * request for buying a tower
 * @author Wanning
 *
 */
public class BuyTowerRequest extends Request {

	private Tower myTower;
	private Point myPoint;
	
	/**
	 * constructor
	 * @param tower
	 * @param p
	 */
	public BuyTowerRequest(Tower tower, Point p) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
		this.myPoint = p;
	}

	/**
	 * execute the buy tower logic
	 */
	@Override
	public void execute(RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		
		Unit t = new Tower(myTower);
		t.setID(re.getNewID());
		t.setPoint(myPoint);
		t.setFaction(Faction.player);
		re.addUnit(t.getID(),t);
		
		int money = (new Double(re.getPlayerInfo().getMoney() - t.getAttribute("BuyCost"))).intValue();
		re.getPlayerInfo().setMoney(money);
		
	}

}
