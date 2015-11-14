package editor;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class TabsList implements IView {
	
	TabPane myTabs;
	Map<String, Node> myTabMap;
	
	public TabsList(){
		myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myTabMap = new HashMap<String, Node>();
	}
	
	public TabsList(String[] tabs) {
		myTabs = new TabPane();
		myTabMap = new HashMap<String, Node>();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		for (String name : tabs) {
			myTabs.getTabs().add(new Tab(name));
		}
	}

	public void addTab(String tabName, Node content){
		myTabMap.put(tabName, content);
		Tab tabToAdd = new Tab(tabName);
		tabToAdd.setContent(content);
		myTabs.getTabs().add(tabToAdd);
	}
	
	public Node getTab(String tabName){
		return myTabMap.get(tabName);
	}
	
	@Override
	public Node getView() {
		return myTabs;
	}
}
