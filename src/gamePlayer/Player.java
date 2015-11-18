package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
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
	private Menus myMenus;
	private PlayerInfo myPlayerInfo;
	private Button addMapButton;
	
	public Player(Controller controller, Stage s) {
		this.myController = controller;
		this.myStage = s;
		Group root = new Group();
		myHUD = new HUD(myController, this);
		myMap = new Map(myController, this);
		myMenus = new Menus(myController);
		myStore = new Store(this);
		BorderPane borderPane = new BorderPane();
		populate(borderPane);
		root.getChildren().add(borderPane);
		Scene scene = new Scene(root, myWidth, myHeight);
		myStage.setScene(scene);
		initialize(s);
	}
	
	private void populate(BorderPane bp){
		bp.setTop(topMenuBar());
		bp.setLeft(myMap.initialize());
		bp.setRight(myHUD.initialize());
		bp.setBottom(myStore.initialize());
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
		result.getChildren().addAll(myMenus.initialize());//, addMapButton());
		return result;

	}

	public Node addMapButton(){
		addMapButton = new Button("Add Background");
		addMapButton.setOnMouseClicked(e->myMap.uploadMap());
		return addMapButton;
	}
	
	private void configure(){
		myStore.setWidth(myWidth);
		myStore.setHeight(myHeight*.2);
		myHUD.setWidth(myWidth*.25);
		myHUD.setHeight(myHeight*.7);
		myMap.setWidth(myWidth*.75);
		myMap.setHeight(myHeight*.7);
		myMenus.setHeight(myHeight*.05);
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
	
}
