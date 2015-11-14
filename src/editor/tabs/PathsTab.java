package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class PathsTab implements IView{
	private ScrollPane myTabView;
	
	public PathsTab(){
		myTabView = new ScrollPane();
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
