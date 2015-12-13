// This entire file is part of my masterpiece.
// Vanessa Wu

package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.ResourceBundle;

import controller.Controller;
import image.ImageMaker;
import interfaces.IRequest;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import units.Path;
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
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;
	private double boardWidth;
	private double boardHeight;

	public Map(Controller c, Stage s, Player p){
		this.myPlayer = p;
		this.myStage = s;
		this.myController = c;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		this.boardWidth = Double.parseDouble(ResourceBundle.getBundle("resources/Default").getString("BoardWidth"));
		this.boardHeight = Double.parseDouble(ResourceBundle.getBundle("resources/Default").getString("BoardHeight"));
	}
	
	public Pane initialize(){
		myPane = new Pane();

		background = new ImageView();
		background.setFitWidth(boardWidth);
		background.setFitHeight(boardHeight);
		try {
			String bg = myController.getBackground();
			//ln(bg);
			background.setImage(ImageMaker.getImage(myController.getBackground()));
		} catch (Exception e) {
			background.setImage(ImageMaker.getImage(ResourceBundle.getBundle("resources/Default").getString("Background")));
		}
		myPane.getChildren().add(background);
		myPathInfoHolder = new PathInfoHolder();
		myBooleanHolder = new BooleanHolder();
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
	
	public void showPaths(List<Path> pathsForLevel, boolean visible){
		PathHandler myPathHandler = new PathHandler(myPane, myPathInfoHolder, myResource, visible);
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
		background.setImage(image);
//		myController.redisplayPath();
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
