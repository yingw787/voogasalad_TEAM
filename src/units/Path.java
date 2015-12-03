package units;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<Point> myCheckPoints;
	private String myName;
	private double myRadius;
	
	/**  Constructor for Path object
	 *   @param String Name of path 
	 *   @param List   List of points in path
	 **/
	public Path(String s, List<Point> p){
		myName = s;
		myCheckPoints = p;
	}
	
	//temporary for testing purposes
	public void setRadius(Double d){
		myRadius = d;
	}
	
	public double getRadius(){
		return myRadius;
	}
	
	public String getName() {
		return myName;
	}
	
	public List<Point> getPoints(){
		return myCheckPoints;
	}
}
