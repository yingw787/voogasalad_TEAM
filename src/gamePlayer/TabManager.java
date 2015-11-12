package gamePlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabManager {
	private TabPane myTabPane;
	private Store myStore;

	public TabManager(Store s){
		this.myStore = s;
	}
	
	public TabPane initialize(){
		myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		populate();
		myTabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>(){
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab oldTab, Tab newTab) {
						myStore.changeStock(newTab.getText());
					}
					
				}
				);
		
		return myTabPane;
	}
		
	
	private void populate(){
		//just for now
		Tab towerTab = new Tab("Towers");
		Tab allyTab = new Tab("Troops");
		myTabPane.getTabs().addAll(towerTab, allyTab);
	}

	public void setHeight(double height) {
		myTabPane.setPrefHeight(height);
		
	}

	public void setWidth(double width) {
		myTabPane.setPrefWidth(width);
	}
}
