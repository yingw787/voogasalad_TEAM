// This entire file is part of my masterpiece.
// Ying Wang

package gameEngine.pathModel;

public class PathPoint implements IPathPoint{
	
	double myXPosition, myYPosition; 
	
	protected PathPoint(double x, double y){
		myXPosition = x; 
		myYPosition = y; 
	}
	
	public double[] getPosition(){
		return new double[] {myXPosition, myYPosition}; 
	}
}
