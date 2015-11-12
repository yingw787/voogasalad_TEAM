package gamePlayer;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import units.PlayerInfo;

public class HUD implements IViewNode{
	private VBox myVBox;
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
		myVBox.getChildren().addAll(money, lives, level);
	}
	
	@Override
	public void setHeight(double height){
		myVBox.setPrefHeight(height);
	}
	
	@Override
	public void setWidth(double width){
		myVBox.setPrefWidth(width);
	}

	
}
