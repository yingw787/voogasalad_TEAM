package editor.tabs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import editor.IView;

public class TowersTab implements IView {
	private ScrollPane myTabView;
	private VBox myTabContent;
	
	public TowersTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);


	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}

}
