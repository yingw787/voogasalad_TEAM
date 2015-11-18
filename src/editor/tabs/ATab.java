package editor.tabs;

import java.util.Observable;

import editor.IView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ATab extends Observable implements IView, ITab {
	protected ScrollPane myTabView;
	protected VBox myTabContent;
	protected HBox myButtons;
	protected ListView<String> myEntriesList;
	protected ObservableList<String> myEntriesToShow;
	
	protected void initTab() {
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
		initButtons();
		initList();
	}
	
	private void initButtons() {
		myButtons = new HBox();
		myButtons.setAlignment(Pos.BOTTOM_RIGHT);
		myTabContent.getChildren().add(myButtons);
	}
	
	private void initList() {
		myEntriesToShow = FXCollections.observableArrayList();
		myEntriesList = new ListView<String>(myEntriesToShow);
		myEntriesList.setMinWidth(432); // I don't like this magic number but it works for now
		myEntriesList.maxHeightProperty().bind(myTabView.heightProperty().subtract(myButtons.heightProperty()));
		myTabContent.getChildren().add(myEntriesList);
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