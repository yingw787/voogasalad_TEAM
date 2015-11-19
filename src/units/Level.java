package units;

import java.util.List;

public class Level {
	private String myName;
	private List<Troop> myTroopList;
	private List<String> myPathsList;
	private double mySpawnRate;
	private double myWaveSpeed;
	
	
	/**  Constructor for Level object
	 *   @params Properties of Level object
	 **/
	public Level(String name, List<Troop> troopsList, List<String> pathsList, double spawnRate, double waveSpeed) {
		myName = name;
		myTroopList = troopsList;
		myPathsList = pathsList;
		mySpawnRate = spawnRate;
		myWaveSpeed = waveSpeed;
	}
	
	public String getName() {
		return myName;
	}
	
	public List<Troop> getTroops() {
		return myTroopList;
	}
	
	public List<String> getPathNames() {
		return myPathsList;
	}
	
	public double getSpawnRate() {
		return mySpawnRate;
	}
	
	public double getWaveSpeed() {
		return myWaveSpeed;
	}
}
