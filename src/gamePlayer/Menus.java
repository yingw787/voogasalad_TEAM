package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class Menus implements IViewNode {
	private MenuBar myMenuBar;
	
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
		
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		
	}

}
