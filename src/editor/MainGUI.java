package editor;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainGUI {
	
	private final int width = 1125;
	private final int height = 528;
	Stage myStage;
	Scene myScene;
	GridPane myPane;
	ToolBar myToolBar;
	SubScene myBoard;
	TabPane myTabs;
	ScrollPane myAttributes;
	ScrollPane myRules;
	Group myGroup;
	
	public MainGUI() {
		myStage = new Stage();
		myPane = new GridPane();
		initialize();
		// add views
		myPane.add(myBoard, 0, 1, 1, 2);
		myPane.add(myToolBar, 0, 0, 3, 1);
		myPane.add(myTabs, 1, 1, 2, 1);
		myPane.add(myRules, 1, 2, 1, 1);
		myPane.add(myAttributes, 2, 2, 1, 1);
		setConstraints();
		// set up scene
		myScene = new Scene(myPane, width, height);
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void initialize() {
		String[] toolOptions = {"File", "Edit", "Options", "Help"};
		// initialize tool bar
		Header tb = new Header(toolOptions);
		myToolBar = (ToolBar) tb.getView();
		myToolBar.setPrefWidth(width);
		// initialize game board
		myGroup = new Group();
		GameBoard gb = new GameBoard(myGroup, 675, 490);
		myBoard = (SubScene) gb.getView();
		// initialize tabs list
		String[] tabOptions = {"Scenes", "Towers", "Bullets", "Troops", "Game"};
		TabsList tl = new TabsList(tabOptions);
		myTabs = (TabPane) tl.getView();
		// initialize rules box
		RulesBox rb = new RulesBox();
		myRules = rb.getView();
		// initialize attributes box
		AttributesBox ab = new AttributesBox();
		myAttributes = ab.getView();
	}
	
	private void setConstraints() {
		myPane.getColumnConstraints().addAll(
				new ColumnConstraints(675),
				new ColumnConstraints(225),
				new ColumnConstraints(225));
		myPane.getRowConstraints().addAll(
				new RowConstraints(38),
				new RowConstraints(252),
				new RowConstraints(238));
	}
}
