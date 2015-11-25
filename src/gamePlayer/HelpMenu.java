package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelpMenu extends Menu{

	public HelpMenu(){
		super("Help");
		addViews();
	}

	private void addViews() {
		MenuItem helpContents = new MenuItem("Help Contents");
		helpContents.setOnAction(e -> loadHelpPage());
		this.getItems().addAll(helpContents);
	}

	private void loadHelpPage() {
		System.out.println("help");
	}
}