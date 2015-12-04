package gameEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import gameEngine.environments.RuntimeEnvironment;
import units.Faction;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.Point;
import units.Troop;
import units.Unit;
import units.UnitType;

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
	 **/
	private IDGenerator myIDGenerator;
	private HashMap<Unit, Queue<Point>> myWalkManager;
	private List<Path> myCurrentPaths;
	private Level myCurrentLevel;
	private Point start, end;
	private int currentEnemy;
	private RuntimeEnvironment myRE;
	
	/**
	 * Constructor for MapManager.java. 
	 * Sets the runtime environment saved in the class
	 * Sets the initial number of enemies to be zero. 
	 * Initializes myWalkManager, which is a map of Units to a queue of Points (along the Path) in order to ensure each unit knows where it is going.  
	 * 
	 **/
	public MapManager(RuntimeEnvironment re, IDGenerator id){
		myRE = re;	
		myIDGenerator = id;
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
		myCurrentPaths = paths;
		
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
		t.setType(UnitType.Troop);
		myWalkManager.put(t, getRandomPath());
		t.setAttribute("ID", myIDGenerator.getID());
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
	private Queue<Point> getRandomPath(){
		Random randomGenerator = new Random();
		Path myPath = myCurrentPaths.get(randomGenerator.nextInt(myCurrentPaths.size()));
		Queue<Point> myPointsQueue = new LinkedList<Point>();
		for (Point p : myPath.getPoints()) {
			myPointsQueue.add(p);
		}
		return myPointsQueue;
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
		Point nextDestination = new Point(currX + deltaX, currY + deltaY);
		unit.setPoint(nextDestination);
		if ((((int) nextDestination.getX() == (int) target.getX())&&( (int) nextDestination.getY()== (int) target.getY()))
		|| ((Math.abs(nextDestination.getX()-target.getX()) < 0.75) &&((Math.abs(nextDestination.getY()-target.getY()) < 0.75)))){
			myWalkManager.get(unit).remove();
			if (myWalkManager.get(unit).peek()==null){
				myWalkManager.remove(unit);
				myRE.removeUnit(unit.getID());
			}
		} 
		
	}
	
	/**
	 * Request handling for collisions and setting of new towers on the board, and other events that happen in the front-end that affect the back-end map state. 
	 **/
	public void handleRequests(){
		// TODO: when a request object comes into the map, pass it into this method 
		
	}
	
	/**
	 * Converting the list of paths to the class used in the private class PathModel, for processing in convertToPathModel. 
	 **/
	public List<List<PathPoint>> convertToListOfListOfPathPoints(){
		return null;
		
	}
	
	/**
	 *  PRIVATE CLASS 
	 *  convert the List<List<PathPoint>> into a graph PathModel for future reference
	 *  TODO: 
	 *  - implement; assume that the first and the last points of the list are the set start and end position of the path. 
	 *  - Error checking for that will be done in another method. 
	 **/
	private void convertToPathModel(List<List<PathPoint>> allAvailablePaths){
		for(List<PathPoint> path : allAvailablePaths){
			for(int i = 0; i < path.size(); i++){
				
			}
			
		}
		
		
	}
	
	
	
	
	/**
	 *  PathModel is an extremely basic graph implementation for storing a path model. 
	 *  Currently it is not being used, as the model for the path is stored as a list of paths, which is itself a list of points. 
	 *  However, yw103 
	 *  
	 *  PathModel must be initialized with a start PathPoint and an end PathPoint. 
	 *  There should never be a need to make PathModel, PathPoint, or PathEdge public or protected because units need to be added to the MapManager anyways 
	 *  
	 *  TODO: 
	 *  Implement direction in the graph (because unless the enemies have some AI built into them, they need 
	 *  Be able to convert the 
	 */
	private class PathModel{
		HashSet<PathPoint> points; 
		HashSet<PathEdge> edges; 
		PathPoint start, end; 
		
		/**
		 * Generates a new PathModel based on the point coordinates in the start and the end points. 
		 */
		public PathModel(double myStartXCoordinate, double myStartYCoordinate, double myEndXCoordinate, double myEndYCoordinate){
			start = new PathPoint(myStartXCoordinate, myStartYCoordinate); 
			end = new PathPoint(myEndXCoordinate, myEndYCoordinate);
			points.add(start);
			points.add(end);
			
			PathEdge primaryEdge = new PathEdge(start, end);
			edges.add(primaryEdge);
		}
		
		/**
		 * Add a path point to the graph. 
		 */
		public void addPathPoint(PathPoint newPoint){
			points.add(newPoint);
		}
		
		/**
		 * Remove a path point from the graph. 
		 */
		public void deletePathPoint(PathPoint point){
			points.remove(point);
		}
		
		/**
		 * Add an edge to the graph. 
		 */
		public void addPathEdge(PathEdge newEdge){
			edges.add(newEdge);
		}
		
		/**
		 * Delete an edge to the graph. 
		 */
		public void deletePathEdge(PathEdge edge){
			edges.remove(edge);
		}
		
		/**
		 * Checks whether there is a valid path between two path points. 
		 */
		public boolean isValidPath(PathPoint a, PathPoint b){
			for(PathEdge edge : edges){
				if(edge.getVertices().contains(a) && edge.getVertices().contains(b)){
					return true;
				}
			}
			return false; 
			
		}
		
		/**
		 * retrieves the starting point of the pathModel. 
		 * TODO: 
		 * - Not sure whether multiple starting points should be supported. 
		 */
		public PathPoint getStartPoint(){
			return start; 
		}
		
		/**
		 * retrieves the ending point of the pathModel. 
		 * TODO: 
		 * - Not sure whether multiple ending points should be supported. 
		 */
		public PathPoint getEndPoint(){
			return end; 
		}
		
	}
	
	private class PathEdge{
		PathPoint a, b; 
		
		/**
		 * generates a PathEdge object, an edge in the PathModel graph model of the paths available. 
		 * @param a
		 * @param b
		 */
		public PathEdge(PathPoint a, PathPoint b){
			this.a = a; 
			this.b = b; 
		}
		
		/**
		 * gets the vertices of a particular edge. 
		 * @return a HashSet of PathPoints that describe the endpoints of these edges.
		 */
		public HashSet<PathPoint> getVertices(){
			HashSet<PathPoint> edge = new HashSet<PathPoint>();
			edge.add(a);
			edge.add(b);
			return edge; 
		}	
	}
	
	private class PathPoint{
		
		double myXPosition, myYPosition; 
		
		/**
		 * Generates a new PathPoint, a tuple of doubles. 
		 * @param x
		 * @param y
		 */
		public PathPoint(double x, double y){
			myXPosition = x; 
			myYPosition = y; 
		}
		
		/**
		 * 
		 * @return double[] of size 2, with x and y double coordinates. 
		 */
		public double[] getPosition(){
			return new double[] {myXPosition, myYPosition}; 
		}
	}



	
}
