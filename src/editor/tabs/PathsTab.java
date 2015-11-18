package editor.tabs;

import java.util.ArrayList;
import java.util.List;

import editor.IView;
import editor.MainGUI;
import editor.tabData.ITabData;
import editor.tabData.PathsData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import units.Path;
import units.Point;

public class PathsTab extends ATab implements IView, ITab{
	private PathsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;
	private int myCurrentPath = 1;
	private Point lastFlag;
	private Path myBuildingPath;
	private List<ImageView> myFlags;

	public PathsTab(){
		initTab();
		createButtons();
		myFlags = new ArrayList<ImageView>();
	}

	private void createButtons() {
		myAddButton = makeButton("Add New Path", e-> selectPaths());
		myDeleteButton = makeButton("Delete Path", e-> deleteButton());
		myFinishButton = makeButton("Finalize Path", e-> finishPath());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton, myFinishButton);
	}

	private void deleteButton() {
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		String selectedPath = selected.split(":")[0];
		myEntriesToShow.remove(selected);
		myData.remove(selectedPath);
	}
	
	private void finishPath() {
		MainGUI.myBoard.setOnMouseClicked(e -> {});
		// remove all existing flags
		try {
		while (((Pane) MainGUI.myBoard.getRoot()).getChildren().size() > 1) {
			((Pane) MainGUI.myBoard.getRoot()).getChildren().remove(((Pane) MainGUI.myBoard.getRoot()).getChildren().size()-1);
		}
		myData.addPath("Path " + myCurrentPath, myBuildingPath);
		myEntriesToShow.add("Path " + myCurrentPath + ": " + myData.pointsToString("Path " + myCurrentPath));
		myCurrentPath++;
		myBuildingPath = null;
		}
		catch (NullPointerException e){
			System.out.println("No checkpoints selected");
		}
	}
	
	private void selectPaths() {
		myBuildingPath = new Path("Path "+ myCurrentPath, new ArrayList<Point>());
		MainGUI.myBoard.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				// add flag to board
				Image flag = new Image("flag.png");
				ImageView myFlag = new ImageView(flag);
				myFlag.setLayoutX(arg0.getSceneX());
				myFlag.setLayoutY(arg0.getSceneY() - 50);
				myFlags.add(myFlag);
				//add line
				if (myFlags.size()>1) {
					Line line = new Line(lastFlag.getX(),lastFlag.getY()-25,arg0.getSceneX(),arg0.getSceneY()-25);
					line.setStrokeWidth(5);
					line.setStroke(Color.AZURE);
					((Pane) MainGUI.myBoard.getRoot()).getChildren().add(line);
				}

				((Pane) MainGUI.myBoard.getRoot()).getChildren().add(myFlag);
				myBuildingPath.getPoints().add(new Point(arg0.getSceneX(), arg0.getSceneY()));
				lastFlag = new Point(arg0.getSceneX(), arg0.getSceneY());
			}
		});
	}

	@Override
	public void setData(ITabData data) {
		myData = (PathsData) data;
	}
}
