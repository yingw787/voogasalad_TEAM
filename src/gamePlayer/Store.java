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
	
	
	public Store(){
		testCaseMaker();
		initialize();
	}
	
	private void testCaseMaker(){
		myTestMap = new HashMap<String, List<Unit>>();
		List<Unit> TowerList = new ArrayList<Unit>();
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Attack Turret", "turret.png", 250));
		myTestMap.put("Towers", TowerList);
		List<Unit> TroopList = new ArrayList<Unit>();
		TroopList.add(new Troop("Basic Minion", "purpleminion.png", 150));
		TroopList.add(new Troop("Basic Minion", "purpleminion.png", 150));
		TroopList.add(new Troop("Caster Minion", "casterminion.png", 300));
		myTestMap.put("Troops", TroopList);
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
}
