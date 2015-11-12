package editor;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TabsList implements IView {
	
	TabPane myTabs;
	
	public TabsList(String[] tabs) {
		myTabs = new TabPane();
		for (String name : tabs) {
			Tab tab = new Tab(name);
			VBox vbox = new VBox();
			vbox.getChildren().add(new Text(name));
			tab.setContent(vbox);
			myTabs.getTabs().add(tab);
		}
	}

	@Override
	public Node getView() {
		return myTabs;
	}
}
