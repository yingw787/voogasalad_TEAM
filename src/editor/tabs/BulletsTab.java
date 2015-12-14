// This entire file is part of my masterpiece.
// Susan Lang (sml59)

package editor.tabs;

import units.Bullet;
import units.Unit;
import javafx.beans.value.ObservableValue;
import editor.tabData.BulletsData;
import editor.tabData.ITabData;

/**  Editor tab for Bullets
 **/
public class BulletsTab extends ATabItems {
	private BulletsData myData;

	/**  Constructor for editor tab for Bullets
	 **/
	public BulletsTab(){
		initTab();
		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
	}
	
	@Override
	protected void addButton() {
		addNewUnit();
	}

	@Override
	protected void addToData(Unit item) {
		myData.add(item.getStringAttribute("Name"), (Bullet) item);
	}
	
	@Override
	protected void removeFromData(String name) {
		myData.remove(name);
	}
	
	@Override
	public void setData(ITabData data) {
		myData = (BulletsData) data;
		initData(myData);
	}
}
