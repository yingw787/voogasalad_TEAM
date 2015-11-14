package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class Menus extends MenuBar implements IViewNode {
	private MenuBar myMenuBar;
	private Menu newMenu;
	private Menu saveMenu;
	private Menu helpMenu;

	private View myView;
	
	public Menus(View v){
		this.myView = v;
	}
	
	public MenuBar initialize(){
		myMenuBar = new MenuBar();
		newMenu = new Menu("New Game");
		saveMenu = new Menu("Save");
		helpMenu = new Menu("Help");
		populate();
		return myMenuBar;
	}

	private void populate(){
		newMenu.setOnAction(event -> {newMenuAction();});
		saveMenu.setOnAction(actionEvent -> savemenuAction());
		helpMenu.setOnAction(actionEvent -> helpMenuAction());
		
		myMenuBar.getMenus().addAll(newMenu, saveMenu, helpMenu);
	}

	private void helpMenuAction() {
		System.out.println("print help");
	}

	private void savemenuAction() {
		System.out.println("print save");
	}

	private void newMenuAction() {
		System.out.println("print new");
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
