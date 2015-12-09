package editor.tabs;

import units.Bullet;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import editor.IView;
import editor.tabData.BulletsData;
import editor.tabData.ITabData;

/**  Editor tab for Bullets
 **/
public class BulletsTab extends ATab {
	private BulletsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myBulletID;

	/**  Constructor for editor tab for Bullets
	 **/
	public BulletsTab(){
		initTab();
		createButtons();

		myBulletID = 0;
		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                //ln(new_val);    
	                //ln("clicked");
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
	}

	private void createButtons() {
		myAddButton = makeButton("Add New Bullet", e -> addBullet());
		myDeleteButton = makeButton("Delete Bullet", e -> deleteBullet());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
	}
	
	private void addBullet(){
		Bullet bullet = new Bullet();
		String bulletName = "Bullet " + myBulletID++;
		bullet.setAttribute("Name", bulletName);
		bullet.removeAttribute("BuyCost");
		bullet.removeAttribute("SellCost");
		myEntriesToShow.add(bulletName);
		myData.add(bulletName, bullet);
	}
	
	private void deleteBullet(){
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted Bullets
		for(Object t : myData.getData()){
			//(((Bullet) t).getStringAttribute("Name") + ", ");
		}
		//ln();
	}

	@Override
	public void setData(ITabData data) {
		myData = (BulletsData) data;
		for (String id : myData.getBulletNamesArray()) {
			myEntriesToShow.add(id);
			myBulletID++;
		}
	}

}
