package editor;

import java.util.Observer;

import editor.tabData.DataController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainGUI {
	
	private final int width = 1125;
	private final int height = 528;
	private Stage myStage;
	private Scene myScene;
	private GridPane myPane;
	private ToolBar myToolBar;
	private TabPane myTabs;
	private ScrollPane myAttributes;
	private ScrollPane myRules;
	private Pane myBoardParent;
	public static SubScene myBoard;
	private DataController myDataController;
	
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
		myBoardParent = new Pane();
		GameBoard gb = new GameBoard(myBoardParent, 675, 490);
		myBoard = (SubScene) gb.getView();
		// intialize game data holders
		myDataController = new DataController();
		// initialize tabs list
//		String[] tabOptions = {"Scenes", "Towers", "Bullets", "Troops", "Level", "Game"};
//		TabsList tl = new TabsList(tabOptions);
//		myTabs = (TabPane) tl.getView();
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
	
	private void setConstraints() {
		myPane.getColumnConstraints().addAll(
				new ColumnConstraints(675),
				new ColumnConstraints(225),
				new ColumnConstraints(225));
		myPane.getRowConstraints().addAll(
				new RowConstraints(38),
				//new RowConstraints(38),
				//new RowConstraints(252),
				new RowConstraints(240));
	}
	
}
