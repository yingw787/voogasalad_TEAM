package editor;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Header implements IView {
	
	ToolBar myToolBar;
	String[] mySections;
	
	public Header() {
		myToolBar = new ToolBar();
	}
	
	public Header(String[] sections) {
		mySections = sections;
		myToolBar = new ToolBar();
		
		for (String option : sections) {
			myToolBar.getItems().add(new Button(option));
		}
	}

	@Override
	public ToolBar getView() {
		return myToolBar;
	}
}
