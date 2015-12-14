// This entire file is part of my masterpiece.
// Ying Wang

package gameEngine.pathModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	Set<IPathPoint> myPathPoints; 
	Set<IPathEdge> myPathEdges; 
	Set<IPathPoint> myStartingPoints, myEndingPoints; 
		
	public PathModel(){
		this(new HashSet<IPathPoint>(), new HashSet<IPathPoint>(), new HashSet<IPathEdge>());
	}
	
	/**
	 * Generates a new PathModel based on the point coordinates in the start and the end points. 
	 */
	protected PathModel(HashSet<IPathPoint> listOfStartingPoints, HashSet<IPathPoint> listOfEndingPoints, HashSet<IPathEdge> listOfPathEdges){
		
		myStartingPoints = listOfStartingPoints; 
		myEndingPoints = listOfEndingPoints; 
		
		myPathPoints.addAll(myStartingPoints);
		myPathPoints.addAll(myEndingPoints);
		myPathEdges.addAll(listOfPathEdges);
		
		if(myPathEdges.size() == 0){
			IPathEdge initialEdge = new PathEdge(this.getStartingPoint(true), this.getEndPoint(true));
			myPathEdges.add(initialEdge);
		}
	}
	
	/**
	 * Converting the list of paths to the class used in the private class PathModel, for processing in convertToPathModel. 
	 * Path has a lot more complexity than the PathPoints model at the moment; will attempt rest of functionality later 
	 **/
	
	public void generateCoordinatePathModel(List<Path> paths){
				
		for(Path path: paths){
			List<Point> list = path.getPoints();
			List<IPathPoint> newList = convertPathToListOfPathPoints(list);
			
			myStartingPoints.add(newList.get(0));
			myEndingPoints.add(newList.get(newList.size()-1));
			
			for(int i = 0; i < newList.size() - 1; i++){
				IPathPoint IPathPoint = newList.get(i);
				
				myPathPoints.add(IPathPoint);
				IPathEdge edge = new PathEdge(newList.get(i), newList.get(i+1));
				myPathEdges.add(edge);
			}
			myPathPoints.add(newList.get(newList.size() - 1));
		}
	}
	
		private List<IPathPoint> convertPathToListOfPathPoints(List<Point> path){
			List<IPathPoint> newPath = new ArrayList<IPathPoint>();
			for(int i = 0; i < path.size(); i++){
				Point point = path.get(i);
				IPathPoint IPathPoint = new PathPoint(point.getX(), point.getY());
				newPath.add(IPathPoint);
			}
			return newPath; 
			
		}
	
	
	public Queue<IPathPoint> generateCoordinatePath(boolean isRandom){
		return (isRandom) ? generateRandomPath() : generateHeuristicallyDeterminedPath(); 
	}
	
		private Queue<IPathPoint> generateRandomPath(){
			boolean isRandom = true;
			IPathPoint startingPoint = getStartingPoint(isRandom);
			IPathPoint endingPoint = getEndPoint(isRandom);
			
			// some kind of path generator that will allow for the generation of some random path from the startingPoint to the endingPoint 
			// return the Queue of PathPoints that will allow for that to happen 
			
			@SuppressWarnings("unchecked")
			Queue<IPathPoint> path = (Queue<IPathPoint>) new ArrayList<IPathPoint>();
			IPathPoint point = startingPoint; 
			while(!point.equals(endingPoint)){
				path.add(point);
				List<IPathPoint> listOfNextPoints = retrieveListOfPossibleNextPoints(point);
				Random rn = new Random();
				point = listOfNextPoints.get(rn.nextInt() % listOfNextPoints.size());
			}
			return path; 
				
		}
		
		private Queue<IPathPoint> generateHeuristicallyDeterminedPath(){
			// add heuristic of some sort in order to determine path; 
			// can add Dijistrka's algorithm or something 
			// not the point here, since generateRandomPath should be working  
			return null; 
		}
	
	
	public List<IPathPoint> retrieveListOfPossibleNextPoints(IPathPoint a){
		List<IPathPoint> list = new ArrayList<IPathPoint>();
		for(IPathPoint otherPoint : myPathPoints){
			if(isValidPath(a, otherPoint) && !a.equals(otherPoint))
				list.add(otherPoint);
		}
		
		return list;
	}
	
	/**
	 * Checks whether there is a valid path between two path points. 
	 */
	public boolean isValidPath(IPathPoint a, IPathPoint b){
		for(IPathEdge edge : myPathEdges){
			// make sure it is a directed path; order of the PathPoints matter 
			if(edge.getVertices().get(0).equals(a) && edge.getVertices().get(1).equals(b)){
				return true;
			}
		}
		return false; 
		
	}
	
	public IPathPoint getStartingPoint(boolean isRandom){
		// default is getRandomStartPoint() 
		// if the user set a heuristic in terms of how to choose the start point, use a separate function 
		return (isRandom) ? getRandomStartPoint() : getHeuristicallyDeterminedStartPoint(); 
	}
	
		private IPathPoint getRandomStartPoint(){
			Random rn = new Random(); 
			int randomNumber = rn.nextInt() % myStartingPoints.size();
			ArrayList<IPathPoint> list = new ArrayList<IPathPoint>(); 
			list.addAll(myStartingPoints);
			return list.get(randomNumber);		
		}
	
		private IPathPoint getHeuristicallyDeterminedStartPoint(){
			// add a heuristically determined start point
			return null; 
		}
	
	public IPathPoint getEndPoint(boolean isRandom){
		// default is getRandomEndPoint()
		// if the user set a heuristic in terms of how to choose the end point, use a separate function 
		return (isRandom) ? getRandomEndPoint() : getHeuristicallyDeterminedEndPoint(); 
	}
	
		private IPathPoint getRandomEndPoint(){
			Random rn = new Random(); 
			int randomNumber = rn.nextInt() % myEndingPoints.size();
			ArrayList<IPathPoint> list = new ArrayList<IPathPoint>(); 
			list.addAll(myEndingPoints);
			return list.get(randomNumber);
		}
		
		private IPathPoint getHeuristicallyDeterminedEndPoint(){
			// add a heuristically determined end point
			return null; 
		}
}


