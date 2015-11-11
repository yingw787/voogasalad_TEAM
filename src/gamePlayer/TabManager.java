package gamePlayer;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabManager {
	private TabPane myTabPane;

	public TabPane initialize(){
		myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		populate();
		return myTabPane;
	}
		
	
	
	private void populate(){
		//just for now
		Tab towerTab = new Tab("Towers");
		Tab allyTab = new Tab("Allies");
		myTabPane.getTabs().addAll(towerTab, allyTab);
	}
}
