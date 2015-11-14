package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import units.PlayerInfo;
import units.Unit;

public class View {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private int myWidth = Integer.parseInt(myDefaults.getString("Width"));
	private int myHeight = Integer.parseInt(myDefaults.getString("Height"));
	private Stage myStage;
	private HUD myHUD;
	private Store myStore;
	private Map myMap;
	private Menus myMenus;
	private PlayerInfo myPlayerInfo;
	
	public View(Stage stage){
		this.myStage = stage;
		Group root = new Group();
		myHUD = new HUD(this);
		myMap = new Map(this);
		myMenus = new Menus(this);
		myStore = new Store(this);
		BorderPane borderPane = new BorderPane();
		populate(borderPane);
		root.getChildren().add(borderPane);
		Scene scene = new Scene(root, myWidth, myHeight);
		myStage.setScene(scene);
	}
	
	private void populate(BorderPane bp){
		bp.setTop(myMenus.initialize());
		bp.setLeft(myMap.initialize());
		bp.setRight(myHUD.initialize());
		bp.setBottom(myStore.initialize());
		configure();
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

	public void populateStore(HashMap<String, List<Unit>> store) {
		myStore.setStock(store);
		
	}

	public void updateMap(List<Unit> units) {
		myMap.updateMap(units);
	}

	public void updateUserInfo(PlayerInfo player) {
		myHUD.populate(player);
		myPlayerInfo = player;
	}

	public int getMoney() {
		return myPlayerInfo.getMoney();
	}

	public void enableBuyButton(Unit unit) {
		myHUD.enableBuyButton(unit);
	}

	public void enableSell() {
		myHUD.enableSell();
		
	}

}
