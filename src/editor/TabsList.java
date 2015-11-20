package editor;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class TabsList extends Observable implements IView, Observer {
	
	TabPane myTabs;
	Map<String, Node> myTabMap;
	
	/**  Constructor for TabsList object representing list of editor tabs
	 **/
	public TabsList(){
		myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myTabMap = new HashMap<String, Node>();
		
		
		// Event listener for changing tabs, notify observers
		myTabs.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			System.out.println(newTab.getId());
			setChanged();
			notifyObservers(newTab.getId());
		});
	}
	
	/**  Constructor for TabsList object representing list of editor tabs
	 *   @param String[] String tabs to add to tabs
	 **/
	public TabsList(String[] tabs) {
		myTabs = new TabPane();
		myTabMap = new HashMap<String, Node>();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		for (String name : tabs) {
			myTabs.getTabs().add(new Tab(name));
		}
	}

	/**  Adds a new tab to the Tabs list
	 *   @param String Name of tab
	 *   @param Node  Node to set content of tab
	 **/
	public void addTab(String tabName, Node content){
		myTabMap.put(tabName, content);
		Tab tabToAdd = new Tab(tabName);
		tabToAdd.setContent(content);
		tabToAdd.setId(tabName);
		myTabs.getTabs().add(tabToAdd);
	}
	
	public Node getTab(String tabName){
		return myTabMap.get(tabName);
	}
	
	@Override
	public Node getView() {
		return myTabs;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}
}
