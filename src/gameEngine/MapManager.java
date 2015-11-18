package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import controller.Controller;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.Point;
import units.Troop;
import units.Unit;

public class MapManager {
	

	/*
	 * MapManager.java is the backend engine module for Map.java, the GamePlayer module in the front-end. 
	 * Responsibilities: (keep adding to this as responsibilities grow and diverge: 
	 * 
	 * TODO still (update as needed)
	 * Make sure that everything in engine that the mapmanager needs to handle, that mapmanager can handle 
	 * 
	 */
	private Controller myController;
	private IDGenerator myIDGenerator;
//	PathModel pathModel; 
	private List<Unit> unitsOnBoard; // TODO: do we need to distinguish between the different types of units on the board, or use polymorphism in order to det. action?
	private HashMap<Unit, Queue<Point>> myWalkManager;
	private List<Path> myPaths;
	private List<Path> myCurrentPaths;
	private Level myCurrentLevel;
	private Point start, end;
	private int currentEnemy;
	
	public MapManager(Controller c, List<Path> myPaths2, IDGenerator id){
		myController = c;
		myIDGenerator = id;
		myPaths = myPaths2;
		unitsOnBoard = new ArrayList<Unit>();
		currentEnemy = 0;
		myWalkManager = new HashMap<Unit, Queue<Point>>();
	}
	
	public void startWave(Level level, List<String> pathNames) {
		myCurrentLevel = level;
		myCurrentPaths = new ArrayList<Path>();
		
		//this is bad and we need to change this
		for (String s : pathNames){
			for (Path p : myPaths) {
				if (p.getName().equals(s)){
					myCurrentPaths.add(p);
				}
			}
		}
	}
	
	public boolean hasMoreEnemies(){
		if (currentEnemy == myCurrentLevel.getTroops().size()){
			return false;
		}
		return true;
	}
	
	public void spawnNewEnemy(){
		Troop t = new Troop(myCurrentLevel.getTroops().get(currentEnemy));
		myWalkManager.put(t, getRandomPath());
		t.setAttribute("ID", myIDGenerator.getID());
		Point currentPoint = myWalkManager.get(t).remove();
		t.setAttribute("X", currentPoint.getX());
		t.setAttribute("Y", currentPoint.getY());
		unitsOnBoard.add(t);
		currentEnemy++;
	}
	
	private Queue<Point> getRandomPath(){
		Random randomGenerator = new Random();
		Path myPath = myCurrentPaths.get(randomGenerator.nextInt(myCurrentPaths.size()));
		Queue<Point> myPointsQueue = new LinkedList<Point>();
		for (Point p : myPath.getPoints()) {
			myPointsQueue.add(p);
		}
		return myPointsQueue;
	}
	
	public void walkUnitOnMap(Unit unit) {
		Point target = myWalkManager.get(unit).peek();
		double currX = unit.getAttribute("X");
		double currY = unit.getAttribute("Y");
		double theta = Math.atan((target.getY() - currY)/(target.getX() - currX));
		double deltaX = Math.cos(theta);
		double deltaY = Math.sin(theta);
		System.out.println(deltaX);
		if (target.getX() - currX < 0) {
			//subtract delta x instead 
			deltaX *= -1.0;
			System.out.println(deltaX);
		}
		Point nextDestination = new Point(currX + deltaX, currY + deltaY);
		unit.setPoint(nextDestination);
		if ((nextDestination.getX() == target.getX())&&(nextDestination.getY()==target.getY())){
			myWalkManager.get(unit).remove();	
			if (myWalkManager.get(unit).peek()==null){
				myWalkManager.remove(unit);
				unitsOnBoard.remove(unit);
			}
		}
		
	}
	
	public void handleRequests(){
		// TODO: when a request object comes into the map, pass it into this method 
		
	}
	
	public List<Unit> getUnitsOnBoard(){
		return unitsOnBoard;
	}
	
	// convert the Path object to the PathPoint object; 
	public List<List<PathPoint>> convertToListOfListOfPathPoints(){
		return null;
		
	}
	
	// convert the List<List<PathPoint>> into a graph PathModel for future reference
	// TODO: do all list<list<pathpoint>> start from the same start point and end at the same end point? 
	// TODO: if there is, then map the first point to the start and the last point to the end, and sequential order; 
	// if not, there has to be a way to tell where is the start and the end. 
	private void convertToPathModel(List<List<PathPoint>> allAvailablePaths){
		for(List<PathPoint> path : allAvailablePaths){
			for(int i = 0; i < path.size(); i++){
				
			}
			
		}
		
		
	}
	
	
	
	
	/*
	 *  PathModel is an extremely basic graph implementation for storing a path model. 
	 *  There is no need for indexes, direction, distance, etc.
	 *  PathModel must be initialized with a start PathPoint and an end PathPoint. 
	 *  There should never be a need to make PathModel, PathPoint, or PathEdge public or protected because units need to be added to the MapManager anyways 
	 */
	private class PathModel{
		HashSet<PathPoint> points; 
		HashSet<PathEdge> edges; 
		PathPoint start, end; 
		
		
		public PathModel(double myStartXCoordinate, double myStartYCoordinate, double myEndXCoordinate, double myEndYCoordinate){
			start = new PathPoint(myStartXCoordinate, myStartYCoordinate); 
			end = new PathPoint(myEndXCoordinate, myEndYCoordinate);
			points.add(start);
			points.add(end);
			
			PathEdge primaryEdge = new PathEdge(start, end);
			edges.add(primaryEdge);
		}
		
		public void addPathPoint(PathPoint newPoint){
			points.add(newPoint);
		}
		
		public void deletePathPoint(PathPoint point){
			points.remove(point);
		}
		
		public void addPathEdge(PathEdge newEdge){
			edges.add(newEdge);
		}
		
		public void deletePathEdge(PathEdge edge){
			edges.remove(edge);
		}
		
		public boolean isValidPath(PathPoint a, PathPoint b){
			for(PathEdge edge : edges){
				if(edge.getVertices().contains(a) && edge.getVertices().contains(b)){
					return true;
				}
			}
			return false; 
			
		}
		
		// methods below only if you have a particular start point and a particular end point
		public PathPoint getStartPoint(){
			return start; 
		}
		
		public PathPoint getEndPoint(){
			return end; 
		}
		
	}
	
	private class PathEdge{
		PathPoint a, b; 
		
		
		public PathEdge(PathPoint a, PathPoint b){
			this.a = a; 
			this.b = b; 
		}
		
		public HashSet<PathPoint> getVertices(){
			HashSet<PathPoint> edge = new HashSet<PathPoint>();
			edge.add(a);
			edge.add(b);
			return edge; 
		}	
	}
	
	// tuple class for storing path coordinates 
	private class PathPoint{
		
		double myXPosition, myYPosition; 
		
		public PathPoint(double x, double y){
			myXPosition = x; 
			myYPosition = y; 
		}
		
		public double[] getPosition(){
			return new double[] {myXPosition, myYPosition}; 
		}
	}



	
}
