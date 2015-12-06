package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import gameEngine.requests.SellTowerRequest;
import interfaces.IPlayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import units.Path;
import units.PlayerInfo;
import units.Unit;

public class Player implements IPlayer {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private Controller myController;
	private int myWidth = Integer.parseInt(myDefaults.getString("Width"));
	private int myHeight = Integer.parseInt(myDefaults.getString("Height"));
	private Stage myStage;
	private HUD myHUD;
	private Store myStore;
	private Map myMap;
	private Scene myScene;
	private Menus myMenus;
	private PlayerInfo myPlayerInfo;
	private Button addMapButton;
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	public Player(Controller controller, Stage s) {
		this.myController = controller;
		this.myStage = s;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		Group root = new Group();
		myHUD = new HUD(myController, this);
		myMap = new Map(myController, myStage, this);
		myMenus = new Menus(myController, myMap, myStage);
		myStore = new Store(this);
		BorderPane borderPane = new BorderPane();
		populate(borderPane);
		root.getChildren().add(borderPane);
		myScene = new Scene(root, myWidth, myHeight);
		myStage.setScene(myScene);
		initialize(s);
	}
	
	public MapUnit getSelected(){
		return myMap.getSelected();
	}

	public Stage getStage(){
		return myStage;
	}
	
	private void populate(BorderPane bp){
		bp.setTop(topMenuBar());
		bp.setLeft(myMap.initialize());
		bp.setRight(myHUD.initialize());
		bp.setBottom(myStore.initialize());
		bp.setStyle("-fx-background-color: linear-gradient(#FEF0C9, #61a2b1);");
		configure();
	}
	
	public void initialize(Stage stage) {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		stage.show();
	}

	@Override
	public void updateMap(List<Unit> units) {
		myMap.updateMap(units);
		
	}

	@Override
	public void updateUserInfo(PlayerInfo player) {
		myHUD.populate(player);
		myPlayerInfo = player;
	}
	
	public void updateInfo(PlayerInfo player) {
		myHUD.update(player);
		myPlayerInfo = player;
	}

	@Override
	public void showWin() {
		// TODO Auto-generated method stub
	}

	@Override
	public void showLose() {
		// TODO Auto-generated method stub
		
	}
	
	public void startWave(int i){
		myController.startWave(i);
	}

	@Override
	public void populate(HashMap<String, List<Unit>> store) {
		myStore.setStock(store);
	}
	
	private Node topMenuBar(){
		HBox result = new HBox();
		result.getChildren().addAll(myMenus.initialize(),topButtons());
		return result;

	}
	
	private void configure(){
		myStore.setWidth(myWidth);
		myStore.setHeight(myHeight*Double.parseDouble(myResource.getString("storeHeightConstant")));
		myHUD.setWidth(myWidth*Double.parseDouble(myResource.getString("hudWidthConstant")));
		myHUD.setHeight(myHeight*Double.parseDouble(myResource.getString("hudHeightConstant")));
		myMap.setWidth(myWidth*Double.parseDouble(myResource.getString("mapWidthConstant")));
		myMap.setHeight(myHeight*Double.parseDouble(myResource.getString("mapHeightConstant")));
		myMenus.setHeight(myHeight*Double.parseDouble(myResource.getString("menuHeightConstant")));
	}

	public int getMoney() {
		return myPlayerInfo.getMoney();
	}

	public void enableBuyButton(Unit unit) {
		myHUD.enableBuyButton(unit);
	}

	public void enableSell(MapUnit mapUnit) {
		myHUD.enableSell(mapUnit);
	}
	
	public void updateSelected(MapUnit myUnit){
		myHUD.updateSelected(myUnit);
	}
	
	public void enableTowerPurchase(Unit u) {
		myMap.enableTowerPurchase(u);
	}

	public void showPaths(List<Path> pathsForLevel) {
		myMap.showPaths(pathsForLevel);
	}

	public void resetStore() {
		myStore.resetStock();
	}
	
	public Node topButtons(){
		HBox result = new HBox();
		Button fastForward = new Button("2 x Fast");
		Button pause = new Button("Play/Pause");

		fastForward.setOnMouseClicked(e->fastForwardClicked());
		pause.setOnMouseClicked(e->pauseClicked());
		result.getChildren().addAll(myMenus.initialize(), pause, fastForward);
		return result;
	}

	private void pauseClicked() {
		// TODO Auto-generated method stub
	}

	private void fastForwardClicked() {
		// TODO Auto-generated method stub
	//	TwiceSpeed sell = new SellTowerRequest(tower);

		
	}
	
}
