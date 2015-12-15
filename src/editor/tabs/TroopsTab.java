package editor.tabs;

import units.Tower;
import units.Troop;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.TowersData;
import editor.tabData.TroopsData;

/**  Editor tab for Troops
 **/
public class TroopsTab extends ATab {
	private TroopsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myTroopID;
	
	/**  Constructor for editor tab for Troops
	 **/
	public TroopsTab(){
		initTab();
		createButtons();
		
		myTroopID = 0;

		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                //ln(new_val);    
	                //ln("clicked");
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
	}
	
	private void createButtons() {
		myAddButton = makeButton("Add New Troop", e -> addTroop());
		myDeleteButton = makeButton("Delete Troop", e -> deleteTroop());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
	}
	
	private void addTroop(){
		Troop troop = new Troop();
		String troopName = "Troop " + myTroopID++;
		troop.setAttribute("Name", troopName);
		troop.setAttribute("Image", "purpleminion.png");
		myEntriesToShow.add(troopName);
		myData.add(troopName, troop);
	}
	
	private void deleteTroop(){
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted Troops
		for(Object t : myData.getData()){
			//(((Troop) t).getStringAttribute("Name") + ", ");
		}
		//ln();
	}


	@Override
	public void setData(ITabData data) {
		myData = (TroopsData) data;
		for (Object o : myData.getData()) {
			Troop troop = (Troop) o;
			myEntriesToShow.add(troop.getStringAttribute("Name"));
			myTroopID++;
		}
	}

}