package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import units.Unit;

public class StoreManager {
	private ScrollPane myScrollPane;
	private HashMap<String, List<Unit>> myStock;
	private View myView;
	private Store myStore;
	private HBox myHBox;
	
	public StoreManager(View v, Store s) {
		this.myView = v;
		this.myStore = s;
//		this.myStock = myTestMap;
	}

	public ScrollPane initialize(){
		myScrollPane = new ScrollPane();
		myHBox = new HBox();
		myScrollPane.setContent(myHBox);
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		return myScrollPane;
	}

	public void populate(String key){
		myHBox.getChildren().clear();
		List<StoreButton> list = new ArrayList<StoreButton>();
		List<Unit> storeItems = new ArrayList<Unit>();
		storeItems = myStock.get(key);
		for (Unit unit : storeItems) {
			StoreButton button = buttonFactory(unit);
			if (myStore.getMoney() < unit.getAttribute("BuyCost")) {
				button.setDisable(true);
			}
			list.add(button);
		}
		ToggleGroup group = new ToggleGroup();
		for (StoreButton sb: list) {
			sb.setToggleGroup(group);
			if (sb.getUnit().getStringAttribute("Type").equals("Troop")){
				sb.setOnMouseClicked(e->myStore.enableBuyButton(sb.getUnit()));	
			} else if (sb.getUnit().getStringAttribute("Type").equals("Tower")){
				sb.setOnMouseClicked(e->buttonManager(sb.getUnit()));
			}
		}
		myHBox.getChildren().addAll(list);
	}

	private StoreButton buttonFactory(Unit unit){
		Image image = new Image(unit.getStringAttribute("Image"));
		ImageView imageview = new ImageView(image);
		imageview.setFitHeight(73);
		imageview.setPreserveRatio(true);
		String text = unit.getStringAttribute("Name") + "\n Gold: " + unit.getAttribute("BuyCost");
		StoreButton button = new StoreButton(text, imageview, unit);
		return button;
	}

	private void buttonManager(Unit u) {
		myView.enableTowerPurchase(u);
	}

	public void setHeight(double height) {
		myScrollPane.setPrefHeight(height);
	}

	public void setWidth(double width) {
		myScrollPane.setPrefWidth(width);
	}

	public void setStock(HashMap<String, List<Unit>> store) {
		myStock = store;
		populate("Towers");
	}


}
