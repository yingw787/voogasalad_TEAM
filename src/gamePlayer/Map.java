package gamePlayer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Map implements IViewNode {
	private Pane myPane;

	
	public Pane initialize(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: green;");
		myPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getSceneX() + " " + arg0.getSceneY());
			}
			
		});
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
