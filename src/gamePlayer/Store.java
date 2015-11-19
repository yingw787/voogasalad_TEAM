package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javafx.scene.layout.VBox;
import units.Unit;

public class Store extends Observable implements IViewNode {
	private StoreManager myStoreManager;
	private TabManager myTabManager;
	private Player myPlayer;
	
	public Store(Player p){
		this.myPlayer = p;
	}
	
	public VBox initialize(){
		VBox myVBox = new VBox();
		myStoreManager = new StoreManager(myPlayer, this);
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
	
	public void resetStock(){
		myStoreManager.populate(myTabManager.getCurrentTab());
	}

	public int getMoney() {
		return myPlayer.getMoney();
	}

	public void enableBuyButton(Unit unit) {
		// TODO Auto-generated method stub
		myPlayer.enableBuyButton(unit);
	}

}
