package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.VBox;
import units.Tower;
import units.Troop;
import units.Unit;

public class Store implements IViewNode {
	private StoreManager myStoreManager;
	private TabManager myTabManager;
	private HashMap<String, List<Unit>> myTestMap;
	private View myView;
	
	public Store(View view){
		this.myView = view;
	}
	

	
	public VBox initialize(){
		VBox myVBox = new VBox();
		myStoreManager = new StoreManager(this, myTestMap);
		myTabManager = new TabManager(this);
		myVBox.getChildren().addAll(myTabManager.initialize(), myStoreManager.initialize());
		return myVBox;
	}

	@Override
	public void setHeight(double height){
		myStoreManager.setHeight(height*.8);
		myTabManager.setHeight(height*.2);
	}
	
	@Override
	public void setWidth(double width){
		myStoreManager.setWidth(width);
		myTabManager.setWidth(width);
	}

	public void changeStock(String unitType) {
		myStoreManager.populate(unitType);
	}

	public void setStock(HashMap<String, List<Unit>> store) {
		// TODO Auto-generated method stub
		myStoreManager.setStock(store);
	}



	public int getMoney() {
		return myView.getMoney();
	}



	public void enableBuyButton(Unit unit) {
		// TODO Auto-generated method stub
		myView.enableBuyButton(unit);
	}

}