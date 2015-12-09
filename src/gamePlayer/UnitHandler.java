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

	private ResourceBundle myResource;
	private MapUnit selectedUnit;
	private Pane myPane;
	private Map myMap;
	
	public UnitHandler(ResourceBundle rb, Pane p, Map m) {
		myResource = rb;
		myMap = m;
		myPane = p;
	}
	
	public void updateMap(List<Unit> units, MapHolder mapHolder) {
		List<Double> onMap = new ArrayList<Double>();
		List<Double> removeUnits = new ArrayList<Double>();
		for (Unit unit : units) {
			if (!mapHolder.getImageMap().containsKey(unit.getAttribute("ID"))){
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
					mapHolder.getHealthMap().put(unit.getAttribute("ID"), health);
					health.setLayoutX(unit.getAttribute("X")-Integer.parseInt(myResource.getString("healthXOffset")));
					health.setLayoutY(unit.getAttribute("Y")-Integer.parseInt(myResource.getString("healthYOffset")));
					health.setMaxWidth(Integer.parseInt(myResource.getString("healthWidth")));
					myPane.getChildren().addAll(health);
				}
				mapHolder.getImageMap().put(unit.getAttribute("ID"), mapUnit);
				myPane.getChildren().addAll(mapUnit);
				mapUnit.setX(unit.getAttribute("X"));
				mapUnit.setY(unit.getAttribute("Y"));
				mapUnit.setOnMouseClicked(e->{
					selectedUnit = mapUnit;
					myMap.enableSelling(selectedUnit);
				});
				onMap.add(unit.getAttribute("ID"));
			} else if (mapHolder.getImageMap().containsKey(unit.getAttribute("ID"))) {
				ImageView imageview = mapHolder.getImageMap().get(unit.getAttribute("ID"));
				imageview.setX(unit.getAttribute("X"));
				imageview.setY(unit.getAttribute("Y"));
				if (!unit.getStringAttribute("Type").equals("Bullet")){
					ProgressBar health = mapHolder.getHealthMap().get(unit.getAttribute("ID"));
					health.setLayoutX(unit.getAttribute("X"));
					health.setLayoutY(unit.getAttribute("Y")-Integer.parseInt(myResource.getString("healthYOffset")));
					health.setProgress(unit.getAttribute("Health")/unit.getAttribute("MaxHealth"));	
				}
				onMap.add(unit.getAttribute("ID"));
			}
		}
		for (Entry<Double, MapUnit> entry : mapHolder.getImageMap().entrySet()){
			if (!onMap.contains(entry.getKey())){
				removeUnits.add(entry.getKey());
			}
		}
		for (double d : removeUnits) {
			myPane.getChildren().remove(mapHolder.getImageMap().get(d));
			myPane.getChildren().remove(mapHolder.getHealthMap().get(d));
			myPane.getChildren().remove(mapHolder.getTowerRangeMap().get(d));
			mapHolder.getImageMap().remove(d);
			mapHolder.getHealthMap().remove(d);
			mapHolder.getTowerRangeMap().remove(d);
		}
		if(selectedUnit!=null)
			myMap.updateSelected(selectedUnit);
	}
}


