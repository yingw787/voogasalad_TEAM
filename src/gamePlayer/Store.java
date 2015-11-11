package gamePlayer;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class Store implements IViewNode {
	private ScrollPane myScrollPane;
	private TabPane myTabPane;
//	private HashMap<String, List<Actor>>()
	
	public Store(){
		initialize();
		//need hashmap of String (tab text) to the population inside the store
	}
	
	public VBox initialize(){
		VBox myVBox = new VBox();
		myScrollPane = new StoreManager().initialize();
		myTabPane = new TabManager().initialize();
		myVBox.getChildren().addAll(myTabPane, myScrollPane);
		return myVBox;
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
