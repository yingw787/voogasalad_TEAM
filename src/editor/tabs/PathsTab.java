package editor.tabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import units.Path;
import units.Point;
import editor.IView;
import editor.MainGUI;
import editor.tabData.ITabData;
import editor.tabData.PathsData;

public class PathsTab extends Observable implements IView, ITab{
	private ScrollPane myTabView;
	private VBox myTabContent;
	private PathsData myData;
	private ListView<String> myPathEntriesList;
	private ObservableList<String> myEntriesToShow;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;
	private int myCurrentPath = 1;

	public PathsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
		initializeButtons();
		initializePaths();
	}

	private void initializeButtons() {
		HBox buttons = new HBox();
		myAddButton = new Button("Add New Path");
		myAddButton.setOnAction(e-> selectPaths());
		myDeleteButton = new Button("Delete Path");
		myDeleteButton.setOnAction(e-> deleteButton());
		myFinishButton = new Button("Finalize Path");
		myFinishButton.setOnAction(e-> finishPath());
		buttons.getChildren().addAll(myAddButton, myDeleteButton, myFinishButton);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);  // not sure if this works?
		myTabContent.getChildren().add(buttons);
	}

	private void initializePaths() {
		myEntriesToShow = FXCollections.observableArrayList();
		myPathEntriesList = new ListView<String>(myEntriesToShow);
		myPathEntriesList.setMinWidth(432);
		myTabContent.getChildren().add(myPathEntriesList);
	}

//	private void addButton() {
//		myEntriesToShow.add("Path " + myCurrentPath);
//	}

	private void deleteButton() {
		String selected = myPathEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
	}
	
	private void finishPath() {
		myEntriesToShow.add("Path " + myCurrentPath + ": ");
	}
	
	private void selectPaths() {
		Path myPath = new Path("Path "+ myCurrentPath, new ArrayList<Point>());
		MainGUI.myBoard.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// add flag to board
				Image flag = new Image("flag.png");
				ImageView myFlag = new ImageView(flag);
				myFlag.setLayoutX(arg0.getSceneX());
				myFlag.setLayoutY(arg0.getSceneY() - 50);
				((Pane) MainGUI.myBoard.getRoot()).getChildren().add(myFlag);
				myPath.getPoints().add(new Point(arg0.getSceneX(), arg0.getSceneY()));
			}
		});
	}

	@Override
	public Node getView() {
		return myTabView;
	}

	@Override
	public void setData(ITabData data) {
		myData = (PathsData) data;
	}
}
