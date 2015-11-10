package gamePlayer;

import javafx.scene.layout.StackPane;

public class HUD implements IViewNode{
	private StackPane myStackPane;
	public StackPane initialize(){
		myStackPane = new StackPane();
		return myStackPane;
	}
	
	@Override
	public void setHeight(double height){
		myStackPane.setPrefHeight(height);
	}
	
	@Override
	public void setWidth(double width){
		myStackPane.setPrefWidth(width);
	}
}
