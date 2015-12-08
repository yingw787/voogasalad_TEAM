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
	private MapHolder myMapHolder;
	private PathInfoHolder myPathInfoHolder;
	private BooleanHolder myBooleanHolder;
	private CursorHandler myCursorHandler;
	private Player myPlayer;
	@SuppressWarnings("unused")
	private Stage myStage;
	private Controller myController;
	private ImageView background;
	private ImageView myImage;
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	public Map(Controller c, Stage s, Player p){
		this.myPlayer = p;
		this.myStage = s;
		this.myController = c;
		//myPane = new Pane();
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
	}
	
	public Pane initialize(){
		myPane = new Pane();
		Image grassBG = new Image(getClass().getClassLoader().getResourceAsStream("grass.jpg"));
		background = new ImageView(grassBG);
		myPathInfoHolder = new PathInfoHolder();
		myBooleanHolder = new BooleanHolder();
		myPane.getChildren().add(background);
		myImage = new ImageView();
		myMapHolder = new MapHolder();
		myCursorHandler = new CursorHandler(myBooleanHolder, myPane, myController, myResource, myPathInfoHolder);
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				myCursorHandler.paneClicked(arg0);
			}			
		});
		return myPane;
	}
	
	public void showPaths(List<Path> pathsForLevel){
		PathHandler myPathHandler = new PathHandler(myPane, myPathInfoHolder, myResource);
		myPathHandler.showPaths(pathsForLevel);
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
		UnitHandler myUnitHandler = new UnitHandler(myResource, myPane, this);
		CollisionHandler myCollisionHandler = new CollisionHandler(this);
		myUnitHandler.updateMap(myUnits, myMapHolder);
		myCollisionHandler.checkCollisions(myMapHolder);
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
	

	public void enableTowerPurchase(Unit u){
		myCursorHandler.enableTowerPurchase(u);
	}
	

	public void sendRequest(List<IRequest> requestSender) {
		myController.update(requestSender);
	}

}
