package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import units.Path;
import units.Point;

/**
 *  PathModel stores the path model to be traversed by the enemies/allied troops. 
 *  @author yw103
 *  
 *  PathModel must be initialized with some set of starting PathPoints and ending PathPoints. 
 *  
 *  
 */
public class PathModel implements IPathModel{
	Set<PathPoint> myPathPoints; 
	Set<PathEdge> myPathEdges; 
	Set<PathPoint> myStartingPoints, myEndingPoints; 
	
	List<Path> myLegacyPaths;
	Map<Point, PathPoint> legacyMap = new HashMap<Point, PathPoint>(); // to interface with legacy code 
	
	
	
	boolean chooseDefault = true;
	
	
	protected PathModel(){
		this(new HashSet<PathPoint>(), new HashSet<PathPoint>(), new HashSet<PathEdge>());
	}
	
	/**
	 * Generates a new PathModel based on the point coordinates in the start and the end points. 
	 */
	protected PathModel(HashSet<PathPoint> listOfStartingPoints, HashSet<PathPoint> listOfEndingPoints, HashSet<PathEdge> listOfPathEdges){
		
		myStartingPoints = listOfStartingPoints; 
		myEndingPoints = listOfEndingPoints; 
		
		myPathPoints.addAll(myStartingPoints);
		myPathPoints.addAll(myEndingPoints);
		myPathEdges.addAll(listOfPathEdges);
		
		if(myPathEdges.size() == 0){
			PathEdge initialEdge = new PathEdge(this.getStartingPoint(), this.getEndPoint());
			myPathEdges.add(initialEdge);
		}
	}
	
	
	// converting the list of path points to be in the path model
	
	/**
	 * Request handling for collisions and setting of new towers on the board, and other events that happen in the front-end that affect the back-end map state. 
	 * TODO: if anything happens on the path edit this method 
	 **/
//	protected void handleRequests(){
//		// TODO: when a request object comes into the pathModel, pass it into this method 
//		
//	}
	
	/**
	 * Converting the list of paths to the class used in the private class PathModel, for processing in convertToPathModel. 
	 * TODO: Path has a lot more complexity than the PathPoints model at the moment; will attempt rest of functionality later 
	 **/
	
	protected void generateCoordinatePathModel(List<Path> paths){
		
		myLegacyPaths = paths; 
		
		for(Path path: paths){
			List<Point> list = path.getPoints();
			List<PathPoint> newList = convertPathToListOfPathPoints(list);
			
			myStartingPoints.add(newList.get(0));
			myEndingPoints.add(newList.get(newList.size()-1));
			
			for(int i = 0; i < newList.size() - 1; i++){
				Point point = list.get(i);
				PathPoint pathPoint = newList.get(i);
				
				legacyMap.put(point, pathPoint);
				myPathPoints.add(pathPoint);
				
				PathEdge edge = new PathEdge(newList.get(i), newList.get(i+1));
				myPathEdges.add(edge);
			}
			myPathPoints.add(newList.get(newList.size() - 1));
		}
	}
	
		private List<PathPoint> convertPathToListOfPathPoints(List<Point> path){
			List<PathPoint> newPath = new ArrayList<PathPoint>();
			for(int i = 0; i < path.size(); i++){
				Point point = path.get(i);
				PathPoint pathPoint = new PathPoint(point.getX(), point.getY());
				newPath.add(pathPoint);
			}
			return newPath; 
			
		}
	
	
	protected Queue<Point> generateCoordinatePath(boolean isRandom){
		return (isRandom) ? generateRandomPath() : generateHeuristicallyDeterminedPath(); 
	}
	
		private Queue<Point> generateRandomPath(){
//			Random rn = new Random(); 
//			int randomStartingIndex = rn.nextInt(myStartingPoints.size()/2);
//			ArrayList<PathPoint> listOfStartingPoints = new ArrayList<PathPoint>();
//			listOfStartingPoints.addAll(myStartingPoints);
//			PathPoint startingPoint = listOfStartingPoints.get(randomStartingIndex);
//			
//			int randomEndingIndex = rn.nextInt(myEndingPoints.size()/2);
//			ArrayList<PathPoint> listOfEndingPoints = new ArrayList<PathPoint>(); 
//			listOfEndingPoints.addAll(myEndingPoints);
//			PathPoint endingPoint = listOfEndingPoints.get(randomEndingIndex);
			

			
			// old code 
			Random randomGenerator = new Random();
			Path myPath = myLegacyPaths.get(randomGenerator.nextInt(myLegacyPaths.size()));
			Queue<Point> myPointsQueue = new LinkedList<Point>();
			for (Point p : myPath.getPoints()) {
				myPointsQueue.add(p);
			}
			return myPointsQueue;
			
			
			
			
			
			
		}
		
		private Queue<Point> generateHeuristicallyDeterminedPath(){
			return null; 
		}
	
	
	
	
	/**
	 * Checks whether there is a valid path between two path points. 
	 */
	protected boolean isValidPath(PathPoint a, PathPoint b){
		for(PathEdge edge : myPathEdges){
			// make sure it is a directed path; order of the PathPoints matter 
			if(edge.getVertices().get(0).equals(a) && edge.getVertices().get(1).equals(b)){
				return true;
			}
		}
		return false; 
		
	}
	
	/**
	 * Add a path point to the graph. 
	 */
	protected void addPathPoint(PathPoint newPathPoint){
		myPathPoints.add(newPathPoint);
	}
	
	/**
	 * Remove a path point from the graph. 
	 */
	protected void deletePathPoint(PathPoint newPathPoint){
		myPathPoints.remove(newPathPoint);
	}
	
	/**
	 * Add an edge to the graph. 
	 */
	protected void addPathEdge(PathEdge newPathEdge){
		myPathEdges.add(newPathEdge);
	}
	
	/**
	 * Delete an edge to the graph. 
	 */
	protected void deletePathEdge(PathEdge newPathEdge){
		myPathEdges.remove(newPathEdge);
	}
	
	
	protected PathPoint getStartingPoint(){
		// default is getRandomStartPoint() 
		// if the user set a heuristic in terms of how to choose the start point, use a separate function 
		return (chooseDefault) ? getRandomStartPoint() : getHeuristicallyDeterminedStartPoint(); 
	}
	
		private PathPoint getRandomStartPoint(){
			Random rn = new Random(); 
			int randomNumber = rn.nextInt(myStartingPoints.size() - 1);
			ArrayList<PathPoint> list = new ArrayList<PathPoint>(); 
			list.addAll(myStartingPoints);
			return list.get(randomNumber);		
		}
	
		private PathPoint getHeuristicallyDeterminedStartPoint(){
			return null; 
		}
	
	protected PathPoint getEndPoint(){
		// default is getRandomEndPoint()
		// if the user set a heuristic in terms of how to choose the end point, use a separate function 
		return (chooseDefault) ? getRandomEndPoint() : getHeuristicallyDeterminedEndPoint(); 
	}
	
		private PathPoint getRandomEndPoint(){
			Random rn = new Random(); 
			int randomNumber = rn.nextInt(myEndingPoints.size() - 1);
			ArrayList<PathPoint> list = new ArrayList<PathPoint>(); 
			list.addAll(myEndingPoints);
			return list.get(randomNumber);
		}
		
		private PathPoint getHeuristicallyDeterminedEndPoint(){
			return null; 
		}
	
	protected class PathEdge{
		PathPoint a, b; 
		
		protected PathEdge(PathPoint a, PathPoint b){
			this.a = a; 
			this.b = b; 
		}
		
		protected ArrayList<PathPoint> getVertices(){
			ArrayList<PathPoint> edge = new ArrayList<PathPoint>();
			edge.add(a);
			edge.add(b);
			return edge; 
		}	
	}

	protected class PathPoint{
		
		double myXPosition, myYPosition; 
		
		protected PathPoint(double x, double y){
			myXPosition = x; 
			myYPosition = y; 
		}
		
		protected double[] getPosition(){
			return new double[] {myXPosition, myYPosition}; 
		}
	}
	
	
	
}


