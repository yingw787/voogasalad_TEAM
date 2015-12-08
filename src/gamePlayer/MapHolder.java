package gamePlayer;

import java.util.HashMap;

import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;

public class MapHolder {
	private HashMap<Double, MapUnit> myImageMap;
	private HashMap<Double, ProgressBar> myHealthMap;
	private HashMap<Double, Circle> myTowerRangeMap;
	
	public MapHolder(){
		myImageMap = new HashMap<Double,MapUnit>();
		myHealthMap = new HashMap<Double,ProgressBar>();
		myTowerRangeMap = new HashMap<Double,Circle>();
	}
	
	public HashMap<Double, MapUnit> getImageMap(){
		return myImageMap;
	}
	
	public HashMap<Double, ProgressBar> getHealthMap(){
		return myHealthMap;
	}
	
	public HashMap<Double, Circle> getTowerRangeMap(){
		return myTowerRangeMap;
	}
}
