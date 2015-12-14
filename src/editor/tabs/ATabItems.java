// This entire file is part of my masterpiece.
// Susan Lang (sml59)

package editor.tabs;

import editor.tabData.ITabData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import units.Unit;


/**  Extends ATab. Abstract class for editor tabs for Units. (Towers, Troops, Bullets, Paths, Levels)
 **/
public abstract class ATabItems extends ATab {
	protected HBox myButtons;
	protected ListView<String> myEntriesList;
	protected ObservableList<String> myEntriesToShow;
	protected int myID;
	
	/**  Initializes the tab layout, including the list of units & add and delete buttons.
	 **/
	@Override
	protected void initTab() {
		super.initTab();
		initButtons();
		initList();
	}

	private void initButtons() {
		myButtons = new HBox();
		myButtons.setAlignment(Pos.BOTTOM_RIGHT);
		Button myAddButton = makeButton("Add New " + type, e -> this.addButton());
		Button myDeleteButton = makeButton("Delete " + type, e -> this.deleteButton());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
		myTabContent.getChildren().add(myButtons);
	}
	
	private void initList() {
		myEntriesToShow = FXCollections.observableArrayList();
		myEntriesList = new ListView<String>(myEntriesToShow);
		myEntriesList.prefWidthProperty().bind(myTabView.widthProperty());
		myEntriesList.maxHeightProperty().bind(myTabView.heightProperty().subtract(myButtons.heightProperty()));
		myTabContent.getChildren().add(myEntriesList);
	}
	
	/** Adds given unit to list of existing units.
	 * 
	 * @param item The unit to be added to the list.
	 */
	protected void addToList(Unit item) {
		myEntriesToShow.add(item.getStringAttribute("Name"));
	}
	
	/** Creates a new instance of the tab's unit; adds it to the tab's list of units and adds it to the tab's data.
	 */
	protected void addNewUnit() {
		try {
			Unit unit = (Unit) Class.forName("units." + type).newInstance();
			unit.setAttribute("Name", type + " " + myID++);
			addToList(unit);
			addToData(unit);
		} catch (Exception e) {
			System.out.println("Could not add new " + type + " to myData.");
			e.printStackTrace();
		}
	}
	
	/** Adds given unit to the tab data.
	 * 
	 * @param item Unit to be added.
	 */
	protected abstract void addToData(Unit item);
	
	/** Removes unit with given name from tab data.
	 * 
	 * @param item Name of unit to be removed.
	 */
	protected abstract void removeFromData(String item);
	
	/** Defines the action for the tab's Add button.
	 */
	protected abstract void addButton();
	
	/** Defines the action for the tab's Delete button. By default, removes selected unit from list of units and from tab data.
	 */
	protected void deleteButton() {
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		if (selected != null) {
			myEntriesToShow.remove(selected);
			removeFromData(selected);
		}
	}
	
	/** Loads data into tab to reflect existing units.
	 * 
	 * @param data Data relevant to this tab
	 */
	protected void initData(ITabData data) {
		for (Object o : data.getData()) {
			Unit item = (Unit) o;
			addToList(item);
			myID++;
		}
	}
}
