package editor;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class RulesBox implements IView {
	
	ScrollPane myRulesBox;
	
	public RulesBox() {
		myRulesBox = new ScrollPane();
		myRulesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myRulesBox.setFitToHeight(true);
	}
	
	@Override
	public ScrollPane getView() {
		return myRulesBox;
	}
}
