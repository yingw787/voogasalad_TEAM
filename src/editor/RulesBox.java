package editor;

import java.util.Observable;
import java.util.Observer;

import editor.tabData.DataController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class RulesBox implements IView, Observer {
	
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Rules box: tab changed to " + (String) arg);
	}
}
