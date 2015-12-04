package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import controller.Controller;
import gameEngine.requests.BuyTowerRequest;
import gameEngine.requests.CollisionRequest;
import interfaces.IRequest;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import units.Path;
import units.Point;
import units.Tower;
import units.Unit;

public class Map extends Observable implements IViewNode {
/*
 * Map.java is the actual game board where the game pieces are put into play. 
 */
	private Pane myPane;
	private MapUnit selectedUnit;
	private HashMap<Double, MapUnit> myImageMap;
	private HashMap<Double, ProgressBar> myHealthMap;
	private HashMap<Double, Circle> myTowerRangeMap;
	private Player myPlayer;
	private Stage myStage;
	private Controller myController;
	private boolean purchaseEnabled, clickEnabled;
	private Unit potentialPurchase;
	private List<Line> myCurrentPaths;
	private List<Line> myIllegalZones;
	private ImageView background;
	private ImageView myImage;
	private Circle myRange;
	private ImageView towerCursor;

	public Map(Controller c, Stage s, Player p){
		this.myPlayer = p;
		this.myStage = s;
		this.myController = c;
		purchaseEnabled = false;
		clickEnabled = false;
		//myPane = new Pane();

	}
	
	public Pane initialize(){
		myPane = new Pane();
		//myPane.setStyle("-fx-background-color: green;");
		Image grassBG = new Image(getClass().getClassLoader().getResourceAsStream("grass.jpg"));
		background = new ImageView(grassBG);
		myPane.getChildren().add(background);
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				System.out.println("PE: " + purchaseEnabled);
				System.out.println("CE: " + clickEnabled);
				if (purchaseEnabled&&clickEnabled){
					BuyTowerRequest buyRequest = new BuyTowerRequest((Tower) potentialPurchase, new Point(arg0.getX(), arg0.getY()));
					List<IRequest> requestSender = new ArrayList<IRequest>();
					requestSender.add(buyRequest);
					myController.update(requestSender);
					purchaseEnabled = false;
					myPane.getChildren().remove(myRange);
					myPane.getChildren().remove(towerCursor);
				}
			}

			
		});
		myImageMap = new HashMap<Double, MapUnit>();
		myHealthMap = new HashMap<Double, ProgressBar>();
		myTowerRangeMap = new HashMap<Double, Circle>();
		myCurrentPaths = new ArrayList<Line>();
		myIllegalZones = new ArrayList<Line>();
		myImage = new ImageView();
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
				mapUnit.setFitHeight(30);
				if (unit.getStringAttribute("Type").equals("Bullet")){
					mapUnit.setFitHeight(10);
				}
				if (unit.getStringAttribute("Type").equals("Tower")){
					mapUnit.setFitHeight(55);
				}
				if (!unit.getStringAttribute("Type").equals("Bullet")){
					ProgressBar health = mapUnit.getHealth();
					myHealthMap.put(unit.getAttribute("ID"), health);
					health.setLayoutX(unit.getAttribute("X")-7);
					health.setLayoutY(unit.getAttribute("Y")-10);
					health.setMaxWidth(40);
					myPane.getChildren().addAll(health);
				}
				myImageMap.put(unit.getAttribute("ID"), mapUnit);
				myPane.getChildren().addAll(mapUnit);
				mapUnit.setX(unit.getAttribute("X"));
				mapUnit.setY(unit.getAttribute("Y"));
				mapUnit.setOnMouseClicked(e->{
					selectedUnit = mapUnit;
					enableSelling(selectedUnit);
				});
				onMap.add(unit.getAttribute("ID"));
			} else if (myImageMap.containsKey(unit.getAttribute("ID"))) {
				ImageView imageview = myImageMap.get(unit.getAttribute("ID"));
				imageview.setX(unit.getAttribute("X"));
				imageview.setY(unit.getAttribute("Y"));
				if (!unit.getStringAttribute("Type").equals("Bullet")){
					ProgressBar health = myHealthMap.get(unit.getAttribute("ID"));
					health.setLayoutX(unit.getAttribute("X"));
					health.setLayoutY(unit.getAttribute("Y")-10);
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
			updateSelected(selectedUnit);
//		checkForCollisions();
	}

	private void checkForCollisions(){
		outerloop: {
		for (MapUnit unit1 : myImageMap.values()){
			for (MapUnit unit2 : myImageMap.values()){
				if ((unit1.equals(unit2))||(unit1.getUnit().getStringAttribute("Type").equals(unit2.getUnit().getStringAttribute("Type")))){
					continue;
				} else {
					if (unit1.getBoundsInLocal().intersects(unit2.getBoundsInLocal())){
						CollisionRequest cr = new CollisionRequest(unit1.getUnit(), unit2.getUnit());
						List<IRequest> requestSender = new ArrayList<IRequest>();
						requestSender.add(cr);
						System.out.println("REQUEST");
						myController.update(requestSender);
						break outerloop;
					}
				}
			}
		}
	}
		
	}
	
	public MapUnit getSelected(){
		return selectedUnit;
	}
	
	private void updateSelected(MapUnit myUnit) {
		myPlayer.updateSelected(myUnit);
	}

	public void setBackgroundMap(Image image) {
		myImage = new ImageView(image);
		//myCurrentBackground.getImage();
		System.out.println(myPane.getChildren().size());
		myPane.getChildren().remove(background);
        System.out.println(" selected");
		System.out.println(myPane.getChildren().size());
		myPane.getChildren().add(myImage);
		System.out.println("new size: " + myPane.getChildren().size());
	}


	
	private void enableSelling(MapUnit mapUnit){
		myPlayer.enableSell(mapUnit);
	}
	
	private boolean validPlacement(MouseEvent me){
		boolean valid = true;
		for (Line l : myIllegalZones){
			if (l.contains(me.getSceneX(), me.getSceneY())){
				valid = false;
			}
		}
		return valid;
	}

	public void enableTowerPurchase(Unit u) {
		towerCursor = new ImageView(new Image(u.getStringAttribute("Image")));
		myRange = new Circle();
		clickEnabled = true;
		purchaseEnabled = true;
				myRange.setFill(Color.RED);
				myRange.setStroke(Color.RED);
				myRange.setOpacity(0.3);
				myRange.setRadius(u.getHealth());
				towerCursor.setPreserveRatio(true);
				towerCursor.setFitHeight(55);
				myPane.getChildren().add(myRange);
				myPane.getChildren().addAll(towerCursor);
				myPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						towerCursor.setLayoutX(me.getX());
						towerCursor.setLayoutY(me.getY());
						if (validPlacement(me)) {
							clickEnabled = true;
							towerCursor.setImage(new Image(u.getStringAttribute("Image")));
							myRange.setCenterX(me.getX() + towerCursor.getFitWidth() + 10);
							myRange.setCenterY(me.getY() + towerCursor.getFitHeight() / 2);
							myRange.setVisible(true);
						} else {
							clickEnabled = false;
							towerCursor.setImage(new Image("xmark.png"));
							myRange.setVisible(false);
						}
					}
				});
		potentialPurchase = u;
	}
    	
	
	
	private Line drawPath(Point startLoc, Point endLoc){
		Line path = new Line();
		Line zone = new Line();
		path.setStartX(startLoc.getX()+25);
		path.setStartY(startLoc.getY()+25);
		path.setEndX(endLoc.getX()+25);
		path.setEndY(endLoc.getY()+25);
		path.setStrokeLineCap(StrokeLineCap.ROUND);
		path.setStrokeWidth(10);
		zone.setStartX(startLoc.getX()+25);
		zone.setStartY(startLoc.getY()+25);
		zone.setEndX(endLoc.getX()+25);
		zone.setEndY(endLoc.getY()+25);
		zone.setStrokeLineCap(StrokeLineCap.ROUND);
		zone.setStrokeWidth(70);
		zone.setVisible(false);
		myIllegalZones.add(zone);
		return path;
	}
	
	public void showPaths(List<Path> pathsForLevel) {
		myPane.getChildren().removeAll(myCurrentPaths);
		myPane.getChildren().removeAll(myIllegalZones);
		myIllegalZones.clear();
		myCurrentPaths.clear();
		for (Path p : pathsForLevel){
			List<Point> myPoints = p.getPoints();
			for (int i = 0; i < myPoints.size()-1; i++){
				myCurrentPaths.add(drawPath(myPoints.get(i),myPoints.get(i+1)));
			}
		}
		for (Line l : myCurrentPaths){
			l.setStrokeType(StrokeType.OUTSIDE);
			LinearGradient linearGradient = new LinearGradient(50-18d, 0d, 50+18d, 0d,
					 false, CycleMethod.REFLECT,new Stop(0,Color.BURLYWOOD), new Stop(1,Color.PERU));
			l.setStroke(linearGradient);
		}
		myPane.getChildren().addAll(myCurrentPaths);
		myPane.getChildren().addAll(myIllegalZones);
	}

}
