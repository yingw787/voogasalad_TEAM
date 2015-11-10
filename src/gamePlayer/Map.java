package gamePlayer;

import javafx.scene.layout.Pane;

public class Map implements IViewNode {
	private Pane myPane;

	
	public Pane initialize(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: green;");
		return myPane;
	}


	@Override
	public void setWidth(double width) {
		myPane.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		myPane.setPrefHeight(height);
	}
}
