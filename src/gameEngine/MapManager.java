package gameEngine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MapManager {

	/*
	 * MapManager.java is the backend engine module for Map.java, the GamePlayer module in the front-end. 
	 * Responsibilities: (keep adding to this as responsibilities grow and diverge: 
	 * 
	 * TODO still (update as needed)
	 * Make sure that everything in engine that the mapmanager needs to handle, that mapmanager can handle 
	 * 
	 */
	
	PathModel pathModel; 
	
	public void initialize(){
		// read data from the XML file; where to get that information 
	}
	
	public void convertToPathModel(List<List<PathPoint>> allAvailablePaths){
		
		
		
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
