package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import units.Unit;

public class Map implements IViewNode {
	private Pane myPane;
	private HashMap<Integer, ImageView> myImageMap;

	public Pane initialize(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: green;");
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getSceneX() + " " + arg0.getSceneY());
			}
		});
		myImageMap = new HashMap<Integer, ImageView>();
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
				ImageView imageview = new ImageView(new Image(unit.getImage()));
				imageview.setPreserveRatio(true);
				imageview.setFitHeight(50);
				myImageMap.put(unit.getID(), imageview);
				myPane.getChildren().add(imageview);
				imageview.setX(unit.getPoint().getX());
				imageview.setY(unit.getPoint().getY());
				onMap.add(unit.getID());
			} else if (myImageMap.containsKey(unit.getID())) {
				ImageView imageview = myImageMap.get(unit.getID());
				imageview.setX(unit.getPoint().getX());
				imageview.setY(unit.getPoint().getY());
				onMap.add(unit.getID());
			}
		}
		for (Entry<Integer, ImageView> entry : myImageMap.entrySet()){
			if (!onMap.contains(entry.getKey())){
				removeUnits.add(entry.getKey());
			}
		}
		for (int i : removeUnits) {
			myPane.getChildren().remove(myImageMap.get(i));
			myImageMap.remove(i);
		}
		
	}
}
