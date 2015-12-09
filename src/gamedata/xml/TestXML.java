package gamedata.xml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Unit;

/** 
 * Tester class for testing XML conversion
 * @author Dennis Xu
 *
 */

public class TestXML {

	List<Unit> myTowers;
	List<Unit> myTroops;
	List<Level> myLevels;
	List<PlayerInfo> myPlayerInfo;
	List<Path> myPaths;
	List<Unit> myTowersFromXML;
	List<Unit> myTroopsFromXML;
	List<Level> myLevelsFromXML;
	List<PlayerInfo> myPlayerInfoFromXML;

	public TestXML() {
		TestObjectHolder holder = new TestObjectHolder();
		myTowers = holder.getTowers();
		myTroops = holder.getTroops();
		myLevels = holder.getLevels();
		myPlayerInfo = holder.getPlayerInfo();
		myPaths = holder.getPaths();
	}

	public void testTo() throws UnsupportedEncodingException, IOException {
		XMLConverter c = new XMLConverter();
		for (Unit tower : myTowers) {
			c.toXML(tower, "Game 1", "Tower", tower.getStringAttribute("Name"));
		}
		for (Unit troop : myTroops) {
			c.toXML(troop, "Game 1", "Troop", troop.getStringAttribute("Name"));
		}
		for (Level level : myLevels) {
			c.toXML(level, "Game 1", "Level", level.getName());
		}
		for (PlayerInfo info : myPlayerInfo) {
			c.toXML(info, "Game 1", "PlayerInfo", "player info");
		}
		for (Path path : myPaths) {
			c.toXML(path, "Game 1", "Path", path.getName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testFrom() throws IOException {
		XMLConverter c = new XMLConverter();
//		myTowersFromXML = c.fromXML("Game 1", "Tower");
//		myTroopsFromXML = c.fromXML("Game 1", "Troop");
//		myLevelsFromXML = c.fromXML("Game 1", "Level");
//		myPlayerInfoFromXML = c.fromXML("Game 1", "PlayerInfo");
//		//ln(myTowersFromXML);
//		//ln(myTroopsFromXML);
//		//ln(myLevelsFromXML);
//		//ln(myPlayerInfoFromXML);
		myLevelsFromXML = c.getLevels("Game 1");
		//ln(myLevelsFromXML);
	}
}
