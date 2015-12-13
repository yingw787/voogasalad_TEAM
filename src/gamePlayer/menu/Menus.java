// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer.menu;

import java.io.IOException;

import controller.Controller;
import gamePlayer.IViewNode;
import gamePlayer.Map;
import gamePlayer.Player;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

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

public class Menus implements IViewNode {
	private MenuBar myMenuBar;
	private FileMenu fileMenu;
	private HelpMenu helpMenu;
	private Controller myController;
	@SuppressWarnings("unused")
	private Player myPlayer;
	@SuppressWarnings("unused")
	private Map myMap;
	private Stage myStage;
	
	public Menus(Controller c, Map m, Stage stage){
		this.myMap = m;
		this.myController = c;
		this.myStage = stage;
	}

	/**
	 * Initializes all the menus in menubar.
	 *
	 * @return the menu bar
	 * @throws IOException 
	 */
	public MenuBar initialize() throws IOException{
		myMenuBar = new MenuBar();
		fileMenu = new FileMenu(myStage);
		String URL = myController.getHelp();
		helpMenu = new HelpMenu(URL);
		populate();
		return myMenuBar;
	}

	private void populate(){		
		myMenuBar.getMenus().addAll(fileMenu, helpMenu);
	}

	@Override
	public void setWidth(double width) {
		myMenuBar.setPrefWidth(width);
	}

	@Override
	public void setHeight(double height) {
		myMenuBar.setPrefHeight(height);		
	}

}