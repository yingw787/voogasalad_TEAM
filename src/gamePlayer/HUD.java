package gamePlayer;

import java.util.Observable;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import units.PlayerInfo;
import units.Unit;

public class HUD extends Observable implements IViewNode{
	private VBox myVBox;
	private Button myBuyButton, mySellButton; 
	private View myView;
	
	public HUD(View v){
		this.myView = v;
	}
	
	public VBox initialize(){
		myVBox = new VBox(20);
		myVBox.setAlignment(Pos.CENTER);
		return myVBox;
	}
	
	public void populate(PlayerInfo player){
		myVBox.getChildren().clear();
		Text money = new Text("Gold: " + player.getMoney());
		money.setStyle("-fx-font: 30px Tahoma;");
		Text lives = new Text("Lives: " + player.getLives());
		lives.setStyle("-fx-font: 30px Tahoma;");
		Text level = new Text("Level: " + player.getLevel());
		level.setStyle("-fx-font: 30px Tahoma;");
		myBuyButton = new Button("Buy");
		myBuyButton.setDisable(true);
		myBuyButton.setOnMouseClicked(e->buyButtonClicked());
		mySellButton = new Button("Sell");
		mySellButton.setDisable(true);
		mySellButton.setOnMouseClicked(e->sellButtonClicked());
		myVBox.getChildren().addAll(money, lives, level, myBuyButton, mySellButton);
	}
	
	private void sellButtonClicked(){
		mySellButton.setDisable(true);
		//myView.sellItem();
	}
	
	private void buyButtonClicked() {
		myBuyButton.setDisable(true);
//		myView.purchaseItem();
	}

	@Override
	public void setHeight(double height){
		myVBox.setPrefHeight(height);
	}
	
	@Override
	public void setWidth(double width){
		myVBox.setPrefWidth(width);
	}

	public void enableBuyButton(Unit unit) {
		myBuyButton.setDisable(false);
	}

	public void enableSell() {
		mySellButton.setDisable(false);
	}

	
}
