package editor.tabs;

import java.util.Observable;

import javax.imageio.ImageIO;

import editor.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public abstract class ATab extends Observable implements IView, ITab {
	protected ScrollPane myTabView;
	protected VBox myTabContent;


	
	protected void initTab() {
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}

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