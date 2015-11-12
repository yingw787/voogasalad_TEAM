package editor;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class AttributesBox implements IView {
	
	ScrollPane myAttributesBox;
	
	public AttributesBox() {
		myAttributesBox = new ScrollPane();
		myAttributesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myAttributesBox.setFitToHeight(true);
	}
	
	@Override
	public ScrollPane getView() {
		return myAttributesBox;
	}
}
