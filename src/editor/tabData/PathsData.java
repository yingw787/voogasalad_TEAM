package editor.tabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.Path;
import units.Point;

public class PathsData implements ITabData {

	public static Map<String, Path> myPaths;
	
	public PathsData(){
		myPaths = new HashMap<String, Path>();
	}
	
	public void addPath(String id, Path p){
		myPaths.put(id, p);
	}
	
	public void remove(String id){
		myPaths.remove(id);
	}
	
	public Path get(String id){
		return myPaths.get(id);
	}
	
	public List<String> pointsToString(String pathID) {
		Path p = myPaths.get(pathID);
		List<String> myStringPoints = new ArrayList<String>();
		List<Point> myPointsList = new ArrayList<Point>(p.getPoints());
		for (Point point : myPointsList) {
			myStringPoints.add("(" + point.getX() + ", " + point.getY() + ")");
		}
		return myStringPoints;
	}
	
	@Override
	public List<Object> getData() {
		List<Object> allTowers = new ArrayList<Object>();
		allTowers.addAll(myPaths.values());
		return allTowers;
	}
}
