package gamePlayer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import gamePlayer.map.Map;
import gamePlayer.store.Store;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
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
	private Button addMapButton;

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
		bp.setTop(topMenuBar());
		bp.setLeft(myMap.initialize());
		bp.setRight(myHUD.initialize());
		bp.setBottom(myStore.initialize());
		configure();
	}


	private Node topMenuBar(){
		HBox result = new HBox();
		result.getChildren().addAll(myMenus.initialize(), addMapButton());
		return result;

	}

	public Node addMapButton(){
		addMapButton = new Button("Add Background");
		addMapButton.setPrefSize(120,30);
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
