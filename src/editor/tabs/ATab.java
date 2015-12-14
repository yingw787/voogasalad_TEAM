// This entire file is part of my masterpiece.
// Susan Lang (sml59)

package editor.tabs;

import java.util.Observable;

import editor.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**  Abstract class for all editor tabs
 **/
public abstract class ATab extends Observable implements IView, ITab {
	protected ScrollPane myTabView;
	protected VBox myTabContent;
	protected String type;
	
	/**  Initializes the tab layout.
	 * <p>
	 * Creates a VBox {@code myTabContent} within a ScrollPane {@code myTabView},
	 * and initializes a box for buttons and a list for items within.
	 **/
	protected void initTab() {
		String className = getClass().getSimpleName();
		type = className.substring(0, className.length()-4);
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}

	/**  Creates a Button with the specified label and action.
	 * 
	 * @param label Label for button
	 * @param handler Action that button will perform on click
	 * @return Button
	 **/
    protected Button makeButton (String label, EventHandler<ActionEvent> handler) {
        Button button = new Button();
        button.setText(label);
        button.setOnAction(handler);
        return button;
    }
	
	@Override
	public Node getView() {
		return myTabView;
	}
	
}