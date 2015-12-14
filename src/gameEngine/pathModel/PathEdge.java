// This entire file is part of my masterpiece.
// Ying Wang

package gameEngine.pathModel;

import java.util.ArrayList;

import gameEngine.pathModel.PathPoint;

public class PathEdge implements IPathEdge{
	PathPoint a, b; 
	
	protected PathEdge(IPathPoint a, IPathPoint b){
		this.a = (PathPoint) a; 
		this.b = (PathPoint) b; 
	}
	
	public ArrayList<IPathPoint> getVertices(){
		ArrayList<IPathPoint> edge = new ArrayList<IPathPoint>();
		edge.add(a);
		edge.add(b);
		return edge; 
	}	
}
