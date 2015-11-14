package editor;

import java.util.Observable;
import java.util.Observer;

import editor.attributes.ImageBox;
import editor.tabData.DataController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class AttributesBox implements IView, Observer {
	
	ScrollPane myAttributesBox;
	ImageBox myImageBox;
	private DataController myDataController;
	
	public AttributesBox(DataController data) {
		myAttributesBox = new ScrollPane();
		myAttributesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myAttributesBox.setFitToHeight(true);
	}
	
	@Override
	public ScrollPane getView() {
		return myAttributesBox;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Attributes box: tab changed to " + (String) arg1);
	}
}
