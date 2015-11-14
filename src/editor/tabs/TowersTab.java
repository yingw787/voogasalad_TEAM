package editor.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import units.Tower;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.TowersData;

public class TowersTab extends Observable implements IView, ITab {
	private ScrollPane myTabView;
	private VBox myTabContent;
	private TowersData myData;
	private Button myAddButton;
	private int myTowerID;
	
	private ListView<String> myTowerEntriesList;
	private ObservableList<String> myEntriesToShow;
	
	private class Entry extends HBox {
		public int ID;
		
	}
	
	
	public TowersTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
		
		myTowerID = 0;
		
		HBox buttons = new HBox();
		myAddButton = new Button("Make New Tower");
		Button myDeleteButton = new Button("Delete Tower");
		myAddButton.setOnAction(e -> addTower());
		myDeleteButton.setOnAction(e -> deleteTower());
		buttons.getChildren().addAll(myAddButton, myDeleteButton);
		myTabContent.getChildren().add(buttons);
		
		
		myEntriesToShow = FXCollections.observableArrayList();
		myTowerEntriesList = new ListView<String>(myEntriesToShow);
		myTowerEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                System.out.println(new_val);    
	    });
		myTabContent.getChildren().add(myTowerEntriesList);
	}
	
	private void addTower(){
		Entry towerEntry = new Entry();
		towerEntry.ID = myTowerID;
		Tower tower = new Tower();
		String towerName = "Tower " + myTowerID++;
		tower.setAttribute("Name", towerName);
		myEntriesToShow.add(towerName);
		myData.add(towerName, tower);
	}
	
	private void deleteTower(){
		String selected = myTowerEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted towers
		for(Object t : myData.getData()){
			System.out.print(((Tower) t).getStringAttribute("Name") + ", ");
		}
		System.out.println();
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}


	@Override
	public void setData(ITabData data) {
		myData = (TowersData) data;
	}

}
