package editor.tabs;

import java.util.Observable;

import editor.IView;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public abstract class ATab extends Observable implements IView, ITab {
	protected ScrollPane myTabView;
	protected VBox myTabContent;
	
//	protected void makeButton(String label, Lambda x);
	
	protected void initTab() {
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}
	
}