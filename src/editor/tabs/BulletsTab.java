package editor.tabs;

import java.util.Observable;

import units.Bullet;
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
import editor.IView;
import editor.tabData.BulletsData;
import editor.tabData.DataController;
import editor.tabData.ITabData;
import editor.tabData.TowersData;

public class BulletsTab extends Observable implements IView, ITab {
	private ScrollPane myTabView;
	private VBox myTabContent;	
	private BulletsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private int myBulletID;
	
	private ListView<String> myBulletEntriesList;
	private ObservableList<String> myEntriesToShow;
	
	
	
	public BulletsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
		
		myBulletID = 0;
		
		HBox buttons = new HBox();
		myAddButton = new Button("Make New Bullet");
		myDeleteButton = new Button("Delete Bullet");
		myAddButton.setOnAction(e -> addBullet());
		myDeleteButton.setOnAction(e -> deleteBullet());
		buttons.getChildren().addAll(myAddButton, myDeleteButton);
		myTabContent.getChildren().add(buttons);
		
		
		myEntriesToShow = FXCollections.observableArrayList();
		myBulletEntriesList = new ListView<String>(myEntriesToShow);
		myBulletEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	                System.out.println(new_val);    
	                System.out.println("clicked");
	                setChanged();
	                notifyObservers(myData.get(new_val));
	    });
		myTabContent.getChildren().add(myBulletEntriesList);
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
		String selected = myBulletEntriesList.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myData.remove(selected);
		
		// Check for deleted Bullets
		for(Object t : myData.getData()){
			System.out.print(((Bullet) t).getStringAttribute("Name") + ", ");
		}
		System.out.println();
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}


	@Override
	public void setData(ITabData data) {
		myData = (BulletsData) data;
	}

}
