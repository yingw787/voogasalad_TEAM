package gameEngine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import units.Faction;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Point;
import units.Troop;
import units.Unit;


public class MapManager {
	

	/**
	 * MapManager.java is the corresponding back-end engine module for Map.java, the gamePlayer module in the front-end. 
	 * Responsibilities include: (keep adding to this as responsibilities grow and diverge: 
	 * - being able to either read the map model from XML, or have it passed to itself 
	 * - keeping inventory of the units on the board, including bullets, enemies, troops, and towers. 
	 * - storing the path on the board in a PathModel 
	 * - updating the position and or velocity of every piece on the map that can move. 
	 * - handling Requests from the front-end Map whenever collisions occur between pieces, and removing/updating pieces as that happens. 
	 * - spawning new enemies and placing them onto the Map 
	 * - remaining extensible to further use cases and development. 
	 * 
	 * TODO: (update as needed)
	 * - Be able to translate a List<Path> to a PathModel in order to improve performance and remove any possibility of duplication
	 * - Handle Requests from Map.java and updating the pieces involved in the Request as needed. 
	 * - Communicating with the sprite utility in order to animate movable pieces on the board. 
	 * - Re-factoring the entire class in order to keep with extensible design patterns
	 * 
	 * @author yw103, vanessawuhoo, wanning (yw103: that's all I know of so far) 
	 * 
	 * 
	 **/
	private HashMap<Unit, Queue<Point>> myWalkManager;
	private List<Path> myLegacyPaths; // SHOULD BE DEPRECATED
	
//	private PathModel myPathModel = new PathModel(); 
	private boolean isRandom = true; // isRandom is set to be true; used for heuristics
	
	private Level myCurrentLevel;
	private int currentEnemy;
	private RuntimeEnvironment myRE;
	private Engine myEngine; 
	private Controller myController; 
	
	/**
	 * Constructor for MapManager.java. 
	 * Sets the runtime environment saved in the class
	 * Sets the initial number of enemies to be zero. 
	 * Initializes myWalkManager, which is a map of Units to a queue of Points (along the Path) in order to ensure each unit knows where it is going.  
	 * 
	 **/
	public MapManager(Engine engine){
		myEngine = engine; 
		myRE = engine.getRuntimeEnvironment();	
		currentEnemy = 0;
		myWalkManager = new HashMap<Unit, Queue<Point>>();
		
	}
	
	/**
	 * Generates a new wave of enemies and places them on the board. 
	 * Updates the current level of the game to the level set in the arguments. 
	 * sets the list of current paths to be the paths available in the arguments. 
	 **/
	public void startWave(Level level, List<Path> paths) {
		currentEnemy = 0;
		myCurrentLevel = level;
		
		
		
		myLegacyPaths = paths; 
		
//		myPathModel.generateCoordinatePathModel(paths);
				
	}
	
	/**
	 * Keeps track of whether more enemies need to be generated. 
	 * If the number of enemies generated is less than the current level's worth of enemies, hasMoreEnemies() will return true 
	 * This will enable the MapManager to decide when to stop generating enemies for a given level. 
	 **/
	public boolean noMoreEnemies(){
		return (currentEnemy == myCurrentLevel.getTroops().size());
	}
	
	/**
	 * Spawn an enemy and place it onto the Map. 
	 * - Generates a Troop. 
	 * - the WalkManager, an object that decides which path the enemy will take, places the enemy onto a random path. 
	 * - The Troop is assigned a random ID, which is then checked against the other pieces on the board to ensure it is unique. 
	 * - The WalkManager retrieves the current point corresponding to the random path that the Troop is placed on by popping it off the queue. 
	 * - The Troop's position is to be the initial point generated. 
	 * - The Troop is added to the runtime environment. 
	 * - The number of current enemies is incremented. 
	 * 
	 **/
	public void spawnNewEnemy(){
		Troop t = new Troop(myCurrentLevel.getTroops().get(currentEnemy));
		t.setFaction(Faction.enemy);
		myWalkManager.put(t, getPath(true));
		t.setAttribute("ID", IDGenerator.getID());
		Point currentPoint = myWalkManager.get(t).remove();
		t.setAttribute("X", currentPoint.getX());
		t.setAttribute("Y", currentPoint.getY());
		myRE.addUnit(t.getID(), t);
		currentEnemy++;
	}
	
	/**
	 * PRIVATE CLASS 
	 * chooses a random path for the enemy to walk on. 
	 * - A random number generator is instantiated. 
	 * - A Path is chosen using the random number generator out of all the paths that are available. 
	 * - The points in the Path are added to a new queue of points, and the queue of points is returned. 
	 **/
	
	
	private Queue<Point> getPath(boolean isRandom){
		
		Random randomGenerator = new Random();
		Path myPath = myLegacyPaths.get(randomGenerator.nextInt(myLegacyPaths.size()));
		Queue<Point> myPointsQueue = new LinkedList<Point>();
		for (Point p : myPath.getPoints()) {
			myPointsQueue.add(p);
		}
		return myPointsQueue;
		
//		return myPathModel.generateCoordinatePath(isRandom);
		
		
		
		
	}
	
	/**
	 * Walks the unit on the map. 
	 * - The next point that the unit needs to move to is saved as the target position. 
	 * - the X and Y coordinates from that point are generated. 
	 * - the direction the unit needs to walk to is generated by finding the angle between the two points relative to the X-Y coordinate plane, and the distance is calculated using trig.
	 * - angle calculations have to remember that arcTangent returns values between -pi/2 and pi/2 and only cover one quadrant. 
	 * - The next point the unit needs to move to is calculated by adding the current position and the deltas of X and Y. 
	 * - If the unit is at its final location, then remove the unit from the board and the runtime environment. 
	 **/
	public void walkUnitOnMap(Unit unit) {
		Point target = myWalkManager.get(unit).peek();
		double currX = unit.getAttribute("X");
		double currY = unit.getAttribute("Y");
		double theta = Math.atan((target.getY() - currY)/(target.getX() - currX));
		double deltaX = Math.cos(theta);
		double deltaY = Math.sin(theta);
		if (target.getX() - currX < 0) { 
			deltaX *= -1.0;
			deltaY *= -1.0;
		}
		deltaX *= unit.getSpeed();
		deltaY *= unit.getSpeed();
		Point nextDestination = new Point(currX + deltaX, currY + deltaY);
		unit.setPoint(nextDestination);
		
		// assuming that this function is the one that removes the unit from the end of the path given the unit reaches the end 
		if ((((int) nextDestination.getX() == (int) target.getX())&&( (int) nextDestination.getY()== (int) target.getY()))
		|| ((Math.abs(nextDestination.getX()-target.getX()) < 0.75) &&((Math.abs(nextDestination.getY()-target.getY()) < 0.75)))){
			myWalkManager.get(unit).remove();
			if (myWalkManager.get(unit).peek()==null){
				
				
				unitReachedEndOfPathSuccessfully(unit);
			}
		} 
		
	}
	
	// what to do when the unit makes it to the end of the path successfully 
	public void unitReachedEndOfPathSuccessfully(Unit unit){
		System.out.println("Unit removed from the path and not ended by the tower bullet"+unit.getID());
		
		
		PlayerInfo myPlayerInfo = myRE.getPlayerInfo();
		int numberOfLives = myPlayerInfo.getLives(); 
		numberOfLives -= 1; 
		myPlayerInfo.setLives(numberOfLives);
		
		myController = myEngine.getController();
		myController.updateUserInfo(myPlayerInfo);
		myController.updateInfo(myPlayerInfo);
		
		myWalkManager.remove(unit);
		myRE.removeUnit(unit.getID());
		
	}
	

	
}
