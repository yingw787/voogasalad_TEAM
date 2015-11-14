package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class TowersTab implements IView {
	private ScrollPane myTabView;
	
	public TowersTab(){
		myTabView = new ScrollPane();
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
