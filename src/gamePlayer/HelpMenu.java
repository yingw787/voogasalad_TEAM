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
		Group rootMain = new Group();
		Stage stage = new Stage();
		stage.setTitle("Help Page");
		Scene scene = new Scene(new Group());

		stage.setResizable(false);
		stage.setWidth(1200);
		stage.setHeight(700);
		stage.show();

		WebView browser = new WebView();
		String url = "http://www.cs.duke.edu/courses/fall15/compsci308/assign/04_voogasalad/part5.php"; //hard coded; need to change this

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