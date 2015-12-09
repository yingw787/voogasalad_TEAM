package gamePlayer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class PathInfoHolder {
	
	private List<Line> myCurrentPaths;
	private List<Line> myIllegalZones;
	
	public PathInfoHolder(){
		myCurrentPaths = new ArrayList<Line>();
		myIllegalZones = new ArrayList<Line>();
	}
	
	public List<Line> getCurrentPaths(){
		return myCurrentPaths;
	}
	
	public List<Line> getIllegalZones(){
		return myIllegalZones;
	}
}
