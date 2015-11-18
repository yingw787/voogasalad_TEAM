package editor.tabs;

import java.util.Observable;

import units.Bullet;
import units.Troop;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import editor.IView;
import editor.tabData.BulletsData;
import editor.tabData.ITabData;
import editor.tabData.TroopsData;

public class TroopsTab extends ATab implements IView, ITab {
	private TroopsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myTroopsID;
	
	private ListView<String> myTroopsEntriesList;
	private ObservableList<String> myEntriesToShow;
	
	public TroopsTab(){
		initTab();
		
		myTroopsID = 0;
		
		HBox buttons = new HBox();
		myAddButton = makeButton("Make New Troop", e -> addTroop());
		myDeleteButton = makeButton("Delete Troop", e -> deleteTroop());
		buttons.getChildren().addAll(myAddButton, myDeleteButton);
		myTabContent.getChildren().add(buttons);
		
		myEntriesToShow = FXCollections.observableArrayList();
		myTroopsEntriesList = new ListView<String>(myEntriesToShow);
		myTroopsEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                System.out.println(new_val);    
	                System.out.println("clicked");
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
		myTabContent.getChildren().add(myTroopsEntriesList);
	}
	
	private void addTroop(){
		Troop troop = new Troop();
		String troopName = "Troop " + myTroopsID++;
		troop.setAttribute("Name", troopName);
		troop.setAttribute("Image", "purpleminion.png");
		myEntriesToShow.add(troopName);
		myData.add(troopName, troop);
	}
	
	private void deleteTroop(){
		String selected = myTroopsEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted Troops
		for(Object t : myData.getData()){
			System.out.print(((Troop) t).getStringAttribute("Name") + ", ");
		}
		System.out.println();
	}


	@Override
	public void setData(ITabData data) {
		myData = (TroopsData) data;
	}

}