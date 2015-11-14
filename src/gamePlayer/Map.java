package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import units.Unit;

public class Map implements IViewNode {
	private Pane myPane;

	private HashMap<Integer, MapUnit> myImageMap;
	private HashMap<Integer, ProgressBar> myHealthMap;
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
		myImageMap = new HashMap<Integer, MapUnit>();
		myHealthMap = new HashMap<Integer, ProgressBar>();
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
		List<Integer> onMap = new ArrayList<Integer>();
		List<Integer> removeUnits = new ArrayList<Integer>();
		for (Unit unit : units) {
			if (!myImageMap.containsKey(unit.getID())){
				MapUnit mapUnit = new MapUnit(new Image(unit.getImage()),unit);
				mapUnit.setPreserveRatio(true);
				mapUnit.setFitHeight(50);
				ProgressBar health = mapUnit.getHealth();
				myImageMap.put(unit.getID(), mapUnit);
				myHealthMap.put(unit.getID(), health);
				myPane.getChildren().addAll(mapUnit, health);
				mapUnit.setX(unit.getPoint().getX());
				mapUnit.setY(unit.getPoint().getY());
				health.setLayoutX(unit.getPoint().getX());
				health.setLayoutY(unit.getPoint().getY()-20);
				mapUnit.setOnMouseClicked(e->enableSelling(mapUnit));
				onMap.add(unit.getID());
			} else if (myImageMap.containsKey(unit.getID())) {
				ImageView imageview = myImageMap.get(unit.getID());
				imageview.setX(unit.getPoint().getX());
				imageview.setY(unit.getPoint().getY());
				ProgressBar health = myHealthMap.get(unit.getID());
				health.setLayoutX(unit.getPoint().getX());
				health.setLayoutY(unit.getPoint().getY()-20);
				//reset health value here
				onMap.add(unit.getID());
			}
		}
		for (Entry<Integer, MapUnit> entry : myImageMap.entrySet()){
			if (!onMap.contains(entry.getKey())){
				removeUnits.add(entry.getKey());
			}
		}
		for (int i : removeUnits) {
			myPane.getChildren().remove(myImageMap.get(i));
			myImageMap.remove(i);
			myHealthMap.remove(i);
		}
		
	}

	private void enableSelling(MapUnit mapUnit){
		if (mapUnit.getUnit().getClass().toString().equals("class units.Tower")){
			myView.enableSell();
		}
	}

}
