package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class ScenesTab implements IView {
	private ScrollPane myScenesView;	
	
	
	@Override
	public Node getView() {
		return myScenesView;
	}

}
