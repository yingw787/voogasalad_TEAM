package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import units.Faction;
import units.Point;
import units.Tower;
import units.Unit;

public class BuyTowerRequest extends Request {

	private Tower myTower;
	private Point myPoint;
	
	public BuyTowerRequest(Tower tower, Point p) {
		// TODO Auto-generated constructor stub
		this.myTower = tower;
		this.myPoint = p;
	}

	@Override
	public void execute(RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		
		Unit t = myTower.clone();
		
		t.setPoint(myPoint);
		t.setFaction(Faction.player);
		re.addUnit(t.getID(),t);
		
		int money = (new Double(re.getPlayerInfo().getMoney() - t.getAttribute("BuyCost"))).intValue();
		re.getPlayerInfo().setMoney(money);
		
	}

}
