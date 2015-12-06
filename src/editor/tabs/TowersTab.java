package editor.tabs;

import java.util.Observable;
import java.util.Observer;

import units.Path;
import units.Tower;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.TowersData;

/**  Editor tab for Towers
 **/
public class TowersTab extends ATab implements Observer {
	private TowersData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myTowerID;
	
	private class Entry extends HBox {
		public int ID;
		
	}

	/**  Constructor for editor tab for Towers
	 **/
	public TowersTab(){
		initTab();
		createButtons();

		myTowerID = 0;

		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
//	                System.out.println(new_val);    
//	                System.out.println("clicked");
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
	}
	
	private void createButtons() {
		myAddButton = makeButton("Add New Tower", e -> addTower());
		myDeleteButton = makeButton("Delete Tower", e -> deleteTower());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
	}
	
	private void addTower(){
		Entry towerEntry = new Entry();
		towerEntry.ID = myTowerID;
		Tower tower = new Tower();
		String towerName = "Tower " + myTowerID++;
		tower.setAttribute("Name", towerName);
		tower.setAttribute("Image", "turret.png");
		myEntriesToShow.add(towerName);
		myData.add(towerName, tower);
	}
	
	private void deleteTower(){
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted towers
//		for(Object t : myData.getData()){
//			System.out.print(((Tower) t).getStringAttribute("Name") + ", ");
//		}
	//	System.out.println();
	}


	@Override
	public void setData(ITabData data) {
		myData = (TowersData) data;
		for (Object o : myData.getData()) {
			Tower tower = (Tower) o;
			myEntriesToShow.add(tower.getStringAttribute("Name"));
			myTowerID++;
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
//		String selected = myTowerEntriesList.getSelectionModel().getSelectedItem();
//		myEntriesToShow.remove(selected);
//		myEntriesToShow.add((String) arg1);
//		Tower temp = myData.get(selected);
//		myData.remove(selected);
//		myData.add((String) arg1, temp);
	}

}
