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
	private HashMap<String, List<Unit>> myPopulation;
	private Store myStore;
	private HBox myHBox;
	private Controller myController;
	private Map myMap;

	public StoreManager(Store s, HashMap<String, List<Unit>> myTestMap) {
		this.myStore = s;
		this.myPopulation = myTestMap;
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
		List<Unit> storeItems = myPopulation.get(key);
		for (Unit unit : storeItems) {
			StoreButton button = buttonFactory(unit);
			if (myStore.getMoney() < unit.getAttribute("BuyCost")) {
				button.setDisable(true);
			}
			list.add(button);
		}
		ToggleGroup group = new ToggleGroup();
		for (StoreButton sb : list) {
			sb.setToggleGroup(group);
			if (sb.getUnit().getStringAttribute("Type").equals("Troop")){
				sb.setOnMouseClicked(e->myStore.enableBuyButton(sb.getUnit()));	
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
		button.setOnAction(e -> {
			towerButtonManager(button);
		});
		return button;
	}

	// TODO method to have the object that is clicked to appear in the main pane

	private void towerButtonManager(StoreButton storeButton) {

		if(storeButton.getUnit().getStringAttribute("Name").startsWith("tower") && myMap.hasBeenClicked()){
			System.out.println("I pressed tower"); // for testing purpose
			myController.buyTowerRequest(storeButton.getUnit(), myMap.getPointsClicked());
		}else{
			System.out.println("I pressed troop"); // for testing purpose
		}

	}

	public void setHeight(double height) {
		myScrollPane.setPrefHeight(height);
	}

	public void setWidth(double width) {
		myScrollPane.setPrefWidth(width);
	}

	public void setStock(HashMap<String, List<Unit>> store) {
		this.myPopulation = store;
		populate("Towers");
	}


}
