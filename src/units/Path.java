package units;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<Point> myCheckPoints;
	private String myName;
	
	/**  Constructor for Path object
	 *   @param String Name of path 
	 *   @param List   List of points in path
	 **/
	public Path(String s, List<Point> p){
		myName = s;
		myCheckPoints = p;
	}
	
	public String getName() {
		return myName;
	}
	
	public List<Point> getPoints(){
		return myCheckPoints;
	}
}
