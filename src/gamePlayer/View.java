package gamePlayer;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private int myWidth = Integer.parseInt(myDefaults.getString("Width"));
	private int myHeight = Integer.parseInt(myDefaults.getString("Height"));
	private Stage myStage;
	private HUD myHUD;
	private Store myStore;
	private Map myMap;
	private Menus myMenus;
	
	public View(Stage stage){
		this.myStage = stage;
		Group root = new Group();
		myHUD = new HUD();
		myMap = new Map();
		myMenus = new Menus();
		myStore = new Store();
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

}
