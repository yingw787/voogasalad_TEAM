package gameEngine;

import java.util.HashSet;
import java.util.LinkedList;

public class MapManager {

	/*
	 * MapManager.java is the backend engine module for Map.java, the GamePlayer module in the front-end. 
	 * Responsibilities: (keep adding to this as responsibilities grow and diverge: 
	 * 
	 * TODO still (update as needed:) 
	 * 
	 * 
	 */
	
	/*
	 *  PathModel is an extremely basic graph implementation for storing a path model. 
	 *  There is no need for indexes, direction, distance, etc.
	 *  PathModel must be initialized with a start PathPoint and an end PathPoint. 
	 *   
	 *   
	 */
	private class PathModel{
		HashSet<PathPoint> points; 
		HashSet<PathEdge> edges; 
		
		public PathModel(double myStartXCoordinate, double myStartYCoordinate, double myEndXCoordinate, double myEndYCoordinate){
			PathPoint start = new PathPoint(myStartXCoordinate, myStartYCoordinate); 
			PathPoint end = new PathPoint(myEndXCoordinate, myEndYCoordinate);
			points.add(start);
			points.add(end);
			
			PathEdge primaryEdge = new PathEdge(start, end);
			edges.add(primaryEdge);
			
		}
		
		public void addPathPoint(PathPoint newNode){
			points.add(newNode);
		}
		
		public void addPathEdge(PathEdge newEdge){
			edges.add(newEdge);
		}
		
		public boolean validPathEdgeAvailable(PathPoint a, PathPoint b){
			for(PathEdge edge : edges){
				if(edge.getVertices().contains(a) && edge.getVertices().contains(b)){
					return true;
				}
			}
			return false; 
			
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
