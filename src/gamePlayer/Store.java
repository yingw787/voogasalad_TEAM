package gamePlayer;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class Store implements IViewNode {
	//	private List<Towers> possibleTowers;
	//  private List<Troops> possibleTroops;
	private int myWidth;
	private ScrollPane myScrollPane;
	private TabPane myTabPane;
//	private HashMap<String, List<Actor>>()
	
	public Store(){
		//need hashmap of String (tab text) to the population inside the store
	}
	
	public VBox initialize(){
		VBox vbox = new VBox();
		myTabPane = new TabPane();
		populate(myTabPane);
		myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		myScrollPane = new ScrollPane();
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setPrefWidth(myWidth);
		
		vbox.getChildren().addAll(myTabPane, myScrollPane);
		return vbox;
	}
	
	private void populate(TabPane myTabPane){
		//just for now
		Tab towerTab = new Tab("Towers");
		Tab allyTab = new Tab("Allies");
		myTabPane.getTabs().addAll(towerTab, allyTab);
	}
	
	@Override
	public void setHeight(double height){
		myScrollPane.setPrefHeight(height*.8);
		myTabPane.setPrefHeight(height*.2);
	}
	
	@Override
	public void setWidth(double width){
		myScrollPane.setPrefWidth(width);
		myTabPane.setPrefWidth(width);
	}
}
