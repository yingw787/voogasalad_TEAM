// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer.menu;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpMenu extends Menu{
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	public HelpMenu(String URL){
		super("Help");
		addViews(URL);
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);

	}

	private void addViews(String URL) {
		MenuItem helpContents = new MenuItem("Help Contents");
		helpContents.setOnAction(e -> loadHelpPage(URL));
		this.getItems().addAll(helpContents);
	}

	private void loadHelpPage(String URL) {
		Group rootMain = new Group();
		Stage stage = new Stage();
		stage.setTitle("Help Page");
		Scene scene = new Scene(new Group());

		stage.setResizable(false);
		stage.setWidth(Integer.parseInt(myResource.getString("helpPageWidth")));
		stage.setHeight(Integer.parseInt(myResource.getString("helpPageHeight")));
		stage.show();

		WebView browser = new WebView();
		String url = URL;

		WebEngine webEngine = browser.getEngine();
		webEngine.load(url); 

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(browser);
		scrollPane.setPrefSize(Integer.parseInt(myResource.getString("helpPageWidth")), Integer.parseInt(myResource.getString("helpPageHeight")));
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		rootMain.getChildren().add(scrollPane);
		scene.setRoot(rootMain);

		stage.setScene(scene);
		stage.show();	
	}

}