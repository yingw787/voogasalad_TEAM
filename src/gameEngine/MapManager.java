package gameEngine;

public class MapManager {

	/*
	 * MapManager.java is the backend engine module for Map.java, the GamePlayer module in the front-end. 
	 * Responsibilities: (keep adding to this as responsibilities grow and diverge: 
	 * 
	 * TODO still (update as needed:) 
	 * 
	 * 
	 */
	
	// graph for storing a path model. 
	
	// tuple class for storing path coordinates 
	private class PathPoint{
		
		double myXPosition, myYPosition; 
		
		public PathPoint(double x, double y){
			myXPosition = x; 
			myYPosition = y; 
		}
		
		public double[] returnPosition(){
			return new double[] {myXPosition, myYPosition}; 
		}
	}
	
	
}
