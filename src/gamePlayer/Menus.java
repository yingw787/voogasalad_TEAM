package gamePlayer;

import java.util.Observable;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class Menus extends Observable implements IViewNode {
	private MenuBar myMenuBar;
	private View myView;
	
	public Menus(View v){
		this.myView = v;
	}
	
	public MenuBar initialize(){
		myMenuBar = new MenuBar();
		populate();
		return myMenuBar;
	}
	
	private void populate(){
		myMenuBar.getMenus().addAll(new Menu("New Game"), new Menu("Save"));
	}
	
	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub
		myMenuBar.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		myMenuBar.setPrefHeight(height);		
	}

}
