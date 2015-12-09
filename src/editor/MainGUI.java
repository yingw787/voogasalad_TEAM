package editor;

import java.util.Observer;
import java.util.ResourceBundle;

import editor.tabData.DataController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import startup.Startup;

public class MainGUI {
	private String myName;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private ToolBar myToolBar;
	private TabPane myTabs;
	private ScrollPane myAttributes;
	private ScrollPane myRules;
	public static SubScene myBoard;
	public static GameBoard myGameBoard;
	private DataController myDataController;
	
	/**  Constructor for MainGUI object which opens the default GUI for editor
	 * @param name 
	 **/
	public MainGUI(String name) {
		myName = name;
		myStage = new Stage();

		initialize();
		
		myPane = new BorderPane();
		GridPane grid = new GridPane();
		VBox boardBox = new VBox(myBoard); // to keep the game board anchored while the window is resized
		VBox editBox = new VBox();
		grid.add(boardBox, 0, 0);
		grid.add(editBox, 1, 0);
		
		myPane.setTop(myToolBar);
		myPane.setCenter(grid);

		HBox rulesAttributes = new HBox(myRules, myAttributes);
		editBox.getChildren().addAll(myTabs, rulesAttributes);
		
		grid.prefWidthProperty().bind(myPane.widthProperty());
		boardBox.setPrefWidth(myBoard.getWidth());
		editBox.prefWidthProperty().bind(grid.widthProperty().subtract(boardBox.getWidth()));
		editBox.prefHeightProperty().bind(grid.heightProperty());
		myTabs.prefWidthProperty().bind(editBox.widthProperty());
		myTabs.prefHeightProperty().bind(editBox.heightProperty().divide(2));
		rulesAttributes.prefWidthProperty().bind(editBox.widthProperty());
		rulesAttributes.prefHeightProperty().bind(editBox.heightProperty().divide(2));
		myRules.prefWidthProperty().bind(rulesAttributes.widthProperty().divide(2));
		myAttributes.prefWidthProperty().bind(rulesAttributes.widthProperty().divide(2));

		// set up scene
		myScene = new Scene(myPane, boardBox.getPrefWidth() + 465.0, myPane.getPrefHeight());
		myStage.setScene(myScene);
		myStage.show();
		myStage.setMinWidth(boardBox.getPrefWidth());
		myStage.setMinHeight(myScene.getHeight()); // doesnt work right
		
		myStage.setOnCloseRequest(e -> {
			myStage.close();
			Stage stage = new Stage();
			stage.setScene(new Startup(stage).getScene());
			stage.show();
		});
	}
	
	private void initialize() {
		// intialize game data holders
		myDataController = new DataController(myName);
		
		// initialize tool bar
		Header tb = new Header();
		SaveGame sv = new SaveGame(myDataController);
		tb.getView().getItems().add(sv.getSaveButton());
		myToolBar = (ToolBar) tb.getView();
		
		// initialize game board
		myGameBoard = new GameBoard(myName, new Pane());
//		myGameBoard = gb;
		myBoard = (SubScene) myGameBoard.getView();
		
		// initialize tabs list
		TabsListController tabController = new TabsListController(myDataController);
		myTabs = (TabPane) tabController.getView();
		
		// initialize rules box
		RulesBox rb = new RulesBox(myDataController);
		myRules = rb.getView();
		
		// initialize attributes box
		AttributesBox ab = new AttributesBox(myDataController);
		myAttributes = ab.getView();
		
		TabsList tl = tabController.getTabsList();
		// Make observer/observable relationships
		tl.addObserver(ab);
		tl.addObserver(rb);
		ab.addObserver((Observer) tabController.getTab("Towers"));
	}
	
}
