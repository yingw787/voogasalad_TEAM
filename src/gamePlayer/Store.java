package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import units.Unit;

/*Holds the TabManager and StoreManager, which communicate with each other
 * in order to allow the Player to purchase Troops and Towers
 */
public class Store extends Observable implements IViewNode {
	private StoreManager myStoreManager;
	private TabManager myTabManager;
	private Player myPlayer;
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	public Store(Player p){
		this.myPlayer = p;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);

	}
	
	
	/*Initializes the pertinent elements of the Store such as the 
	 * StoreManager, TabManager, and the VBox they are held in
	 */
	public VBox initialize(){
		VBox myVBox = new VBox();
		myStoreManager = new StoreManager(myPlayer, this);
		myTabManager = new TabManager(this);
		myVBox.getChildren().addAll(myTabManager.initialize(), myStoreManager.initialize());
		return myVBox;
	}

	/*Sets the height of the elements of the Store
	 */
	@Override
	public void setHeight(double height){
		myStoreManager.setHeight(height*Double.parseDouble(myResource.getString("storeManagerHeightConstant")));
		myTabManager.setHeight(height*Double.parseDouble(myResource.getString("tabManagerHeightConstant")));
	}
	
	/*Sets the width of the elements of the Store
	 */
	@Override
	public void setWidth(double width){
		myStoreManager.setWidth(width);
		myTabManager.setWidth(width);
	}

	/*Changes the type of buttons shown in the StoreManager 
	 */
	public void changeStock(String unitType) {
		myStoreManager.populate(unitType);
	}

	/*Initializes the Store with all of the possible Troops and Towers for purchase
	 */
	public void setStock(HashMap<String, List<Unit>> store) {
		// TODO Auto-generated method stub
		myStoreManager.setStock(store);
	}
	
	/*Repopulates the store when purchases are made to omit towers that the user
	 * cannot afford*/
	public void resetStock(){
		myStoreManager.populate(myTabManager.getCurrentTab());
	}

	/*returns the amount of money the user has
	 */
	public int getMoney() {
		return myPlayer.getMoney();
	}

	/*Enables the buy button in the HUD when a troop is selected
	 */
	public void enableBuyButton(Unit unit) {
		// TODO Auto-generated method stub
//		myPlayer.enableBuyButton(unit);
	}

}
