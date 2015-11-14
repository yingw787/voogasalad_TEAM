package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import editor.IView;

public class LevelsTab implements IView{
	private ScrollPane myTabView;
	
	public LevelsTab(){
		myTabView = new ScrollPane();
	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
