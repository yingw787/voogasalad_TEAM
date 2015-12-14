// This entire file is part of my masterpiece.
// Ying Wang


package gameEngine.pathModel;

import java.util.List;
import java.util.Queue;

import units.Path;

public interface IPathModel {
	
	public void generateCoordinatePathModel(List<Path> paths);
	
	public Queue<IPathPoint> generateCoordinatePath(boolean isRandom);
	
	public boolean isValidPath(IPathPoint a, IPathPoint b);
	
	public IPathPoint getStartingPoint(boolean isRandom);
	
	public IPathPoint getEndPoint(boolean isRandom);

}
