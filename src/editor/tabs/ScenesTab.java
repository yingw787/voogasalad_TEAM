package editor.tabs;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import editor.IView;
import editor.tabData.ITabData;

public class ScenesTab extends ATab implements IView, ITab {
	private ITabData myData;

	public ScenesTab(){
		initTab();
	}


	@Override
	public void setData(ITabData data) {
		myData = data;
	}

}
