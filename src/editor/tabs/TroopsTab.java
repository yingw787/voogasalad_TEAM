package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class TroopsTab implements IView {
	private ScrollPane myTabView;
	
	public TroopsTab(){
		myTabView = new ScrollPane();
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
