package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class ScenesTab implements IView {
	private ScrollPane myTabView;
	
	public ScenesTab(){
		myTabView = new ScrollPane();
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
