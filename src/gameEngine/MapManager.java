package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gamePlayer.MapUnit;
import gamedata.xml.XMLConverter;
import units.Level;
import units.Point;
import units.Unit;

public class MapManager {
	
	
	private Engine myEngine;
	/*
	 * MapManager.java is the backend engine module for Map.java, the GamePlayer module in the front-end. 
	 * Responsibilities: (keep adding to this as responsibilities grow and diverge: 
	 * 
	 * TODO still (update as needed)
	 * Make sure that everything in engine that the mapmanager needs to handle, that mapmanager can handle 
	 * 
	 */
	PathModel pathModel; 
	private List<Unit> unitsOnBoard; // TODO: do we need to distinguish between the different types of units on the board, or use polymorphism in order to det. action? 
	private List<Unit> myPossibleTroops;
	private List<Point> myPaths;
	private Level myCurrentLevel;
	private Point start, end;
	private int currentEnemy;
	
	public MapManager(Engine e, List<Unit> list, List<Point> paths){
		myEngine = e;
		myPossibleTroops = list;
		myPaths = paths;
		start = myPaths.get(0);
		end = myPaths.get(myPaths.size()-1);
		currentEnemy = 0;
	}
	
	public void startWave(Level level) {
		myCurrentLevel = level;
		
	}
	
	public void spawnNewEnemy(){
		System.out.println(myCurrentLevel.getTroops().get(currentEnemy).getStringAttribute("Name"));
		currentEnemy++;
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
