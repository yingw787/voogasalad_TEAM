package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import units.Unit;




public class UnitHandler {
	
	private HashMap<Double, MapUnit> myImageMap;
	private HashMap<Double, ProgressBar> myHealthMap;
	private HashMap<Double, Circle> myTowerRangeMap;
	private ResourceBundle myResource;
	private MapUnit selectedUnit;
	private Pane myPane;
	private Map myMap;
	
	public UnitHandler(ResourceBundle rb, Pane p, Map m) {
		myImageMap = new HashMap<Double, MapUnit>();
		myHealthMap = new HashMap<Double, ProgressBar>();
		myTowerRangeMap = new HashMap<Double, Circle>();
		myResource = rb;
		myMap = m;
		myPane = p;
	}
	
	public void updateMap(List<Unit> units) {
		List<Double> onMap = new ArrayList<Double>();
		List<Double> removeUnits = new ArrayList<Double>();
		for (Unit unit : units) {
			if (!myImageMap.containsKey(unit.getAttribute("ID"))){
				MapUnit mapUnit = new MapUnit(new Image(unit.getStringAttribute("Image")),unit);
				mapUnit.setPreserveRatio(true);
				mapUnit.setFitHeight(Integer.parseInt(myResource.getString("nodesHeight")));
				if (unit.getStringAttribute("Type").equals("Bullet")){
					mapUnit.setFitHeight(Integer.parseInt(myResource.getString("bulletHeight")));
				}
				if (unit.getStringAttribute("Type").equals("Tower")){
					mapUnit.setFitHeight(Integer.parseInt(myResource.getString("towerHeight")));
				}
				if (!unit.getStringAttribute("Type").equals("Bullet")){
					ProgressBar health = mapUnit.getHealth();
					myHealthMap.put(unit.getAttribute("ID"), health);
					health.setLayoutX(unit.getAttribute("X")-Integer.parseInt(myResource.getString("healthXOffset")));
					health.setLayoutY(unit.getAttribute("Y")-Integer.parseInt(myResource.getString("healthYOffset")));
					health.setMaxWidth(Integer.parseInt(myResource.getString("healthWidth")));
					myPane.getChildren().addAll(health);
				}
				myImageMap.put(unit.getAttribute("ID"), mapUnit);
				myPane.getChildren().addAll(mapUnit);
				mapUnit.setX(unit.getAttribute("X"));
				mapUnit.setY(unit.getAttribute("Y"));
				mapUnit.setOnMouseClicked(e->{
					selectedUnit = mapUnit;
					myMap.enableSelling(selectedUnit);
				});
				onMap.add(unit.getAttribute("ID"));
			} else if (myImageMap.containsKey(unit.getAttribute("ID"))) {
				ImageView imageview = myImageMap.get(unit.getAttribute("ID"));
				imageview.setX(unit.getAttribute("X"));
				imageview.setY(unit.getAttribute("Y"));
				if (!unit.getStringAttribute("Type").equals("Bullet")){
					ProgressBar health = myHealthMap.get(unit.getAttribute("ID"));
					health.setLayoutX(unit.getAttribute("X"));
					health.setLayoutY(unit.getAttribute("Y")-Integer.parseInt(myResource.getString("healthYOffset")));
					health.setProgress(unit.getAttribute("Health")/unit.getAttribute("MaxHealth"));	
				}
				onMap.add(unit.getAttribute("ID"));
			}
		}
		for (Entry<Double, MapUnit> entry : myImageMap.entrySet()){
			if (!onMap.contains(entry.getKey())){
				removeUnits.add(entry.getKey());
			}
		}
		for (double d : removeUnits) {
			myPane.getChildren().remove(myImageMap.get(d));
			myPane.getChildren().remove(myHealthMap.get(d));
			myPane.getChildren().remove(myTowerRangeMap.get(d));
			myImageMap.remove(d);
			myHealthMap.remove(d);
			myTowerRangeMap.remove(d);
		}
		if(selectedUnit!=null)
			myMap.updateSelected(selectedUnit);
	}
	
	public HashMap<Double, MapUnit> getImageMap(){
		return myImageMap;
	}
}


