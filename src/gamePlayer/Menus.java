package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class Menus extends MenuBar implements IViewNode {
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
		this.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		this.setPrefHeight(height);		
	}

}
