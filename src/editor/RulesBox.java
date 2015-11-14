package editor;

import editor.tabData.DataController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class RulesBox implements IView {
	
	ScrollPane myRulesBox;
	private DataController myDataController;
	
	public RulesBox(DataController data) {
		myRulesBox = new ScrollPane();
		myRulesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myRulesBox.setFitToHeight(true);
	}
	
	@Override
	public ScrollPane getView() {
		return myRulesBox;
	}
}
