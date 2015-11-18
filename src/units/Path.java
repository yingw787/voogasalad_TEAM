package units;

import java.util.List;

public class Path {
	private List<Point> myCheckPoints;
	private String myName;
	
	public Path(String s, List<Point> p){
		myName = s;
		myCheckPoints = p;
	}
	
	public String getName(){
		return myName;
	}
	
	public List<Point> getPoints(){
		return myCheckPoints;
	}
}
