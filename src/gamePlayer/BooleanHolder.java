// This entire file is part of my masterpiece.
// Vanessa Wu

package gamePlayer;

public class BooleanHolder {
	private boolean purchaseEnabled, clickEnabled;
	
	public BooleanHolder(){
		purchaseEnabled = false;
		clickEnabled = false;
	}
	
	public void setPurchaseEnabled(boolean b){
		purchaseEnabled = b;
	}
	
	public void setClickEnabled(boolean b){
		clickEnabled = b;
	}
	
	public boolean getPurchaseEnabled(){
		return purchaseEnabled;
	}
	
	public boolean getClickEnabled(){
		return clickEnabled;
	}
}
