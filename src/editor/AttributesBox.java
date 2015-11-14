package editor;

import editor.attributes.ImageBox;
import editor.tabData.DataController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class AttributesBox implements IView {
	
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
}
