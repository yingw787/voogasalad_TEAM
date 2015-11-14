package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import editor.IView;

public class PathsTab implements IView{
	private ScrollPane myTabView;
	private VBox myTabContent;

	public PathsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
