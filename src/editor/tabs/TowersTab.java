package editor.tabs;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import editor.IView;
import editor.tabData.ITabData;

public class TowersTab extends Observable implements IView, ITab {
	private ScrollPane myTabView;
	private VBox myTabContent;
	private ITabData myData;
	
	public TowersTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);


	}
	
	
	@Override
	public Node getView() {
		return myTabView;
	}


	@Override
	public void setData(ITabData data) {
		myData = data;
	}

}
