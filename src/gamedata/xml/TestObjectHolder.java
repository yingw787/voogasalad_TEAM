package gamedata.xml;

import java.util.ArrayList;
import java.util.List;

import units.Point;
import units.Tower;
import units.Troop;

public class TestObjectHolder {

	Tower tower1;
	Tower tower2;
	Troop troop1;
	Troop troop2;
	List<Tower> myTowers = new ArrayList<Tower>();
	List<Troop> myTroops = new ArrayList<Troop>();

	public TestObjectHolder() {
		
		tower1 = new Tower("tower1", "Tower", 100.0, 5.0, "tower1.jpg", new Point(60.0, 80.0), 1, 200, 100);
		tower2 = new Tower("tower2", "Tower", 200.0, 7.0, "tower2.jpg", new Point(100.0, 135.0), 2, 400, 200);
		troop1 = new Troop("troop1", "Troop", 2.0, 2.0, "troop1.jpg", new Point(75.0, 100.0), 1, 10, 2);
		troop2 = new Troop("troop2", "Troop", 4.0, 3.0, "troop2.jpg", new Point(150.0, 35.0), 2, 20, 4);
		
		myTowers.add(tower1);
		myTowers.add(tower2);
		myTroops.add(troop1);
		myTroops.add(troop2);
	}
	
	public List<Tower> getTowers() {
		return myTowers;
	}
	
	public List<Troop> getTroops() {
		return myTroops;
	}
}
