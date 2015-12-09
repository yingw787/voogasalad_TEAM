package gamePlayer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpMenu extends Menu{

	public HelpMenu(String URL){
		super("Help");
		addViews(URL);
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
		stage.setWidth(1200);
		stage.setHeight(700);
		stage.show();

		WebView browser = new WebView();
		String url = URL; //hard coded; need to change this

		WebEngine webEngine = browser.getEngine();
		webEngine.load(url); 

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(browser);
		scrollPane.setPrefSize(1200, 700);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		rootMain.getChildren().add(scrollPane);
		scene.setRoot(rootMain);

		stage.setScene(scene);
		stage.show();	
	}

}