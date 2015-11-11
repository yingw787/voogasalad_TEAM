package gamePlayer;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class HUD implements IViewNode{
	private VBox myVBox;
	public VBox initialize(){
		myVBox = new VBox();
		myVBox.setAlignment(Pos.CENTER);
		populate();
		return myVBox;
	}
	
	private void populate(){
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
