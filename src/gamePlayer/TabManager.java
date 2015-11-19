package gamePlayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
/*Manages the TabPane that is part of the Store and allows the user to change 
 * between buttons that correspond to Towers and ones that correspond to Troops
 */
public class TabManager {
	private TabPane myTabPane;
	private Store myStore;
	private String myCurrentTab;
	
	public TabManager(Store s){
		this.myStore = s;
	}
	
	/*Initializes the important elements of the TabManager like the Tabpane
	 */
	public TabPane initialize(){
		myTabPane = new TabPane();
		myCurrentTab = "Towers";
		myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		populate();
		myTabPane.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Tab>(){
					@Override
					public void changed(ObservableValue<? extends Tab> ov, Tab oldTab, Tab newTab) {
						myStore.changeStock(newTab.getText());
						myCurrentTab = newTab.getText();
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

	/*Sets the height of the TabPane
	 */
	public void setHeight(double height) {
		myTabPane.setPrefHeight(height);
	}
	
	/*Returns the name of the currently selected Tab
	 */
	public String getCurrentTab(){
		return myCurrentTab;
	}

	/*Sets the width of the TabPane
	 */
	public void setWidth(double width) {
		myTabPane.setPrefWidth(width);
	}

}
