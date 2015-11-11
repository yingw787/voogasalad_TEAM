package gamePlayer;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabManager extends TabPane {
	public TabManager(){
		this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		populate();
	}
	
	private void populate(){
		//just for now
		Tab towerTab = new Tab("Towers");
		Tab allyTab = new Tab("Allies");
		this.getTabs().addAll(towerTab, allyTab);
	}
}
