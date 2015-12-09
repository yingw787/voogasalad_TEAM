package gamePlayer;

import java.io.IOException;
import java.util.Observable;

import controller.Controller;
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

public class Menus extends Observable implements IViewNode {
	private MenuBar myMenuBar;
	private FileMenu fileMenu;
	private EditMenu editMenu;
	private HelpMenu helpMenu;
	@SuppressWarnings("unused")
	private Controller myController;
	@SuppressWarnings("unused")
	private Player myPlayer;
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
		editMenu = new EditMenu(myMap);//, myPlayer, myController);
		String URL = myController.getHelp();
		helpMenu = new HelpMenu(URL);
		populate();
		return myMenuBar;
	}

	private void populate(){		
		myMenuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
	}

	/* (non-Javadoc)
	 * @see gamePlayer.IViewNode#setWidth(double)
	 */
	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub
		myMenuBar.setPrefWidth(width);
	}

	/* (non-Javadoc)
	 * @see gamePlayer.IViewNode#setHeight(double)
	 */
	@Override
	public void setHeight(double height) {
		myMenuBar.setPrefHeight(height);		
	}

}