package gamePlayer;

import java.util.Observable;
import controller.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * A class used to display the menu bar with menu items for Player.
 * There are methods to initialize the menubar and populate it with menuitems in the pane.
 * <p>
 * This class implements ViewNode interface.
 *
 * @return      menubar
 * @see         Player
 * @see         IViewNode
 */

public class Menus extends Observable implements IViewNode {
	private MenuBar myMenuBar;
	private Menu newMenu;
	private Menu saveMenu;
	private Menu helpMenu;
	private Menu addBackgroundMenu;
	private Controller myController;

	public Menus(Controller c){
		this.myController = c;
	}

	public MenuBar initialize(){
		myMenuBar = new MenuBar();
		newMenu = new Menu("New Game");
		saveMenu = new Menu("Save");
		helpMenu = new Menu("Help");
		addBackgroundMenu = new Menu("Add Background");
		populate();
		return myMenuBar;
	}

	private void populate(){
		newMenu.setOnAction(actionEvent -> newMenuAction());
		saveMenu.setOnAction(actionEvent -> savemenuAction());
		helpMenu.setOnAction(actionEvent -> helpMenuAction());
		addBackgroundMenu.setOnAction(actionEvent -> helpMenuAction());
		
		myMenuBar.getMenus().addAll(newMenu, saveMenu, helpMenu,addBackgroundMenu);
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
		myMenuBar.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		myMenuBar.setPrefHeight(height);		
	}

}