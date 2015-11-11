package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class AltMenu extends MenuBar {
	public AltMenu(){
		this.getMenus().add(new Menu("Hi"));
		this.getMenus().add(new Menu("Bye"));
	}

}
