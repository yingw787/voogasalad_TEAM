package editor.tabs;

import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import units.Troop;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.LevelsData;

public class LevelsTab extends Observable implements IView, ITab{
	private ScrollPane myTabView;
	private VBox myTabContent;
	private LevelsData myData;
	private List<Troop> myTroops;
	private Button myWaveSpeedButton;
	private Button mySpawnButton;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;
	private ListView<String> myPathEntriesList;
	private ObservableList<String> myEntriesToShow;

	public LevelsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}
	
	private void initializeButtons() {
		HBox buttons = new HBox();
		myAddButton = new Button("Add New Path");
	//	myAddButton.setOnAction(e-> selectPaths());
		myDeleteButton = new Button("Delete Path");
		myDeleteButton.setOnAction(e-> deleteLevel());
		myFinishButton = new Button("Finalize Path");
		myFinishButton.setOnAction(e-> finishLevel());
		buttons.getChildren().addAll(myAddButton, myDeleteButton, myFinishButton);
		buttons.setAlignment(Pos.BOTTOM_RIGHT); 
		myTabContent.getChildren().add(buttons);
	}
	
	private void initializeLevels() {
		myEntriesToShow = FXCollections.observableArrayList();
		myPathEntriesList = new ListView<String>(myEntriesToShow);
		myPathEntriesList.setMinWidth(432);
		myTabContent.getChildren().add(myPathEntriesList);
	}
	
	private void addLevel() {
		
	}
	
	private void deleteLevel() {
		
	}
	
	private void finishLevel() {
		
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}


	@Override
	public void setData(ITabData data) {
		myData = (LevelsData) data;
		initializeButtons();
		initializeLevels();
	}

}
