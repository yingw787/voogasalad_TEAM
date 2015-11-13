package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import units.Point;
import units.Unit;

public class StoreManager {
	private ScrollPane myScrollPane;
	private HashMap<String, List<Unit>> myPopulation;
	private Store myStore;
	private HBox myHBox;
	
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
		List<ToggleButton> list = new ArrayList<ToggleButton>();
		List<Unit> storeItems = myPopulation.get(key);
		for (Unit unit : storeItems) {
			StoreButton button = buttonFactory(unit);
			if (myStore.getMoney() < unit.getCost()) {
				button.setDisable(true);
			}
			list.add(button);
		}
		ToggleGroup group = new ToggleGroup();
		for (ToggleButton tb : list) {
			tb.setToggleGroup(group);
		}
		myHBox.getChildren().addAll(list);
	}
	
	private StoreButton buttonFactory(Unit unit){
		Image image = new Image(unit.getImage());
		ImageView imageview = new ImageView(image);
		imageview.setFitHeight(73);
		imageview.setPreserveRatio(true);
		String text = unit.getName() + "\n Gold: " + unit.getCost();
		StoreButton button = new StoreButton(text, imageview);
		button.setOnAction(e -> {
			buttonManager();
		});
		return button;
	}

	private void buttonManager() {
		// TODO method to have the object that is clicked to appear in the main pane
		System.out.println("I presssed");
		
		
		
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
