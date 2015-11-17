package gamedata.xml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import units.Unit;

public class TestXML {

	List<Unit> myTowers;
	List<Unit> myTroops;
	List<Unit> myTowersFromXML;
	List<Unit> myTroopsFromXML;

	public TestXML() {
		TestObjectHolder holder = new TestObjectHolder();
		myTowers = holder.getTowers();
		myTroops = holder.getTroops();
	}

	public void testTo() throws UnsupportedEncodingException, IOException {
		XMLConverter c = new XMLConverter();
		for (Unit tower : myTowers) {
			c.toXML(tower, "Game 1", "Tower", tower.getStringAttribute("Name"));
		}
		for (Unit troop : myTroops) {
			c.toXML(troop, "Game 1", "Troop", troop.getStringAttribute("Name"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testFrom() throws IOException {
		XMLConverter c = new XMLConverter();
		myTowersFromXML = c.fromXML("Game 1", "Tower");
		myTroopsFromXML = c.fromXML("Game 1", "Troop");
		System.out.println(myTowersFromXML);
		System.out.println(myTroopsFromXML);
	}
}
