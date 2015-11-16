package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import units.Unit;

public class Map extends Observable implements IViewNode {
/*
 * Map.java is the actual game board where the game pieces are put into play. 
 */
	private Pane myPane;

	private HashMap<Double, MapUnit> myImageMap;
	private HashMap<Double, ProgressBar> myHealthMap;
	private View myView;
	
	public Map(View v){
		this.myView = v;
	}
	
	public Pane initialize(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: green;");
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				System.out.println(arg0.getSceneX() + " " + arg0.getSceneY());
			}
		});
		myImageMap = new HashMap<Double, MapUnit>();
		myHealthMap = new HashMap<Double, ProgressBar>();
		return myPane;
	}


	@Override
	public void setWidth(double width) {
		myPane.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		myPane.setPrefHeight(height);
	}


	public void updateMap(List<Unit> units) {
		List<Double> onMap = new ArrayList<Double>();
		List<Double> removeUnits = new ArrayList<Double>();
		for (Unit unit : units) {
			if (!myImageMap.containsKey(unit.getAttribute("ID"))){
				MapUnit mapUnit = new MapUnit(new Image(unit.getStringAttribute("Image")),unit);
				mapUnit.setPreserveRatio(true);
				mapUnit.setFitHeight(50);
				ProgressBar health = mapUnit.getHealth();
				myImageMap.put(unit.getAttribute("ID"), mapUnit);
				myHealthMap.put(unit.getAttribute("ID"), health);
				myPane.getChildren().addAll(mapUnit, health);
				mapUnit.setX(unit.getAttribute("X"));
				mapUnit.setY(unit.getAttribute("Y"));
				health.setLayoutX(unit.getAttribute("X"));
				health.setLayoutY(unit.getAttribute("Y")-20);
				mapUnit.setOnMouseClicked(e->enableSelling(mapUnit));
				onMap.add(unit.getAttribute("ID"));
			} else if (myImageMap.containsKey(unit.getAttribute("ID"))) {
				ImageView imageview = myImageMap.get(unit.getAttribute("ID"));
				imageview.setX(unit.getAttribute("X"));
				imageview.setY(unit.getAttribute("Y"));
				ProgressBar health = myHealthMap.get(unit.getAttribute("ID"));
				health.setLayoutX(unit.getAttribute("X"));
				health.setLayoutY(unit.getAttribute("Y")-20);
				health.setProgress(unit.getAttribute("Health")/unit.getAttribute("MaxHealth"));
				//reset health value here
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
			myImageMap.remove(d);
			myHealthMap.remove(d);
		}
		
	}

	private void enableSelling(MapUnit mapUnit){
		if (mapUnit.getUnit().getClass().toString().equals("class units.Tower")){
			myView.enableSell();
		}
	}

}
