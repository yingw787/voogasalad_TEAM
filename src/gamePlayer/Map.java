package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.ResourceBundle;

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
	private Player myPlayer;
	private UnitHandler myUnitHandler;
	private CollisionHandler myCollisionHandler;
	@SuppressWarnings("unused")
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
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	public Map(Controller c, Stage s, Player p){
		this.myPlayer = p;
		this.myStage = s;
		this.myController = c;
		purchaseEnabled = false;
		clickEnabled = false;
		//myPane = new Pane();
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
	}
	
	public Pane initialize(){
		myPane = new Pane();
		Image grassBG = new Image(getClass().getClassLoader().getResourceAsStream("grass.jpg"));
		background = new ImageView(grassBG);
		myPane.getChildren().add(background);
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
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
		myCurrentPaths = new ArrayList<Line>();
		myIllegalZones = new ArrayList<Line>();
		myImage = new ImageView();
		myUnitHandler = new UnitHandler(myResource, myPane, this);
		myCollisionHandler = new CollisionHandler(this);
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

	public void updateMap(List<Unit> myUnits){
		myUnitHandler.updateMap(myUnits);
		myCollisionHandler.checkCollisions(myUnitHandler.getImageMap());
	}
	
	public MapUnit getSelected(){
		return selectedUnit;
	}
	
	public void updateSelected(MapUnit myUnit) {
		myPlayer.updateSelected(myUnit);
	}

	public void setBackgroundMap(Image image) {
		myImage = new ImageView(image);
		myPane.getChildren().remove(background);
		myPane.getChildren().add(myImage);
		myController.redisplayPath();
	}

	public void enableSelling(MapUnit mapUnit){
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
				myRange.setOpacity(Double.parseDouble(myResource.getString("rangeOpacity")));
				myRange.setRadius(u.getHealth());
				towerCursor.setPreserveRatio(true);
				towerCursor.setFitHeight(Integer.parseInt(myResource.getString("towerHeight")));
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
		path.setStartX(startLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setStartY(startLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setEndX(endLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setEndY(endLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setStrokeLineCap(StrokeLineCap.ROUND);
		path.setStrokeWidth(Integer.parseInt(myResource.getString("pathStrokeWidth")));
		zone.setStartX(startLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setStartY(startLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setEndX(endLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setEndY(endLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setStrokeLineCap(StrokeLineCap.ROUND);
		zone.setStrokeWidth(Integer.parseInt(myResource.getString("zoneStrokeWidth")));
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

	public void sendRequest(List<IRequest> requestSender) {
		myController.update(requestSender);
	}

}
