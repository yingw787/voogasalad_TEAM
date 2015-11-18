package editor.tabs;

import units.Bullet;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import editor.IView;
import editor.tabData.BulletsData;
import editor.tabData.ITabData;

public class BulletsTab extends ATab implements IView, ITab {
	private BulletsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myBulletID;

	public BulletsTab(){
		initTab();
		createButtons();

		myBulletID = 0;
		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                System.out.println(new_val);    
	                System.out.println("clicked");
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
			System.out.print(((Bullet) t).getStringAttribute("Name") + ", ");
		}
		System.out.println();
	}

	@Override
	public void setData(ITabData data) {
		myData = (BulletsData) data;
	}

}
