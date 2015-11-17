package editor.tabs;

import java.util.Observable;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import editor.IView;
import editor.MainGUI;
import editor.tabData.ITabData;

public class PathsTab extends Observable implements IView, ITab{
	private ScrollPane myTabView;
	private VBox myTabContent;
	private ITabData myData;
	private ListView<String> myPathEntriesList;
	private ObservableList<String> myEntriesToShow;
	private Button myAddButton;
	private Button myDeleteButton;
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
		myAddButton = new Button("Add Path");
		myAddButton.setOnAction(e-> addButton());
		myDeleteButton = new Button("Delete Path");
		myDeleteButton.setOnAction(e-> deleteButton());
		buttons.getChildren().addAll(myAddButton, myDeleteButton);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);  // not sure if this works?
		myTabContent.getChildren().add(buttons);
	}
	
	private void initializePaths() {
		myEntriesToShow = FXCollections.observableArrayList();
		myPathEntriesList = new ListView<String>(myEntriesToShow);
		myPathEntriesList.setMinWidth(432);
		myTabContent.getChildren().add(myPathEntriesList);
	}
	
	private void addButton() {
		myEntriesToShow.add("Path " + myCurrentPath);
		myCurrentPath++;
	}
	
	private void deleteButton() {
		String selected = myPathEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
	}
	
//	private void selectPaths() {
//		MainGUI.myBoard.setOnMouseClicked(e -> 
//			System.out.println("Coordinates: "+MainGUI.myBoard.getLayoutX()+", "+"")
//			);
//	}
	
	@Override
	public Node getView() {
		return myTabView;
	}


	@Override
	public void setData(ITabData data) {
		myData = data;
	}

}
