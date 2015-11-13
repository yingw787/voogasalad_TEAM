package editor;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class TabsList implements IView {
	
	TabPane myTabs;
	
	public TabsList(String[] tabs) {
		myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		for (String name : tabs) {
			myTabs.getTabs().add(new Tab(name));
		}
	}

	@Override
	public Node getView() {
		return myTabs;
	}
}
