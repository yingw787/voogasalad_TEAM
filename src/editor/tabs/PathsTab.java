package editor.tabs;

import java.util.ArrayList;
import java.util.Optional;

import editor.IView;
import editor.MainGUI;
import editor.PathView;
import editor.tabData.ITabData;
import editor.tabData.PathsData;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import units.Path;
import units.Point;


/**  Editor tab for Paths
 **/
public class PathsTab extends ATab implements IView, ITab{
	private PathsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;
	private int myPathID;
	private double myPathRadius;
	private Path myBuildingPath;
	private PathView myPathView;
	
	/**  Constructor for editor tab for Paths
	 **/
	public PathsTab(){
		initTab();
		createButtons();
		myPathID = 0;
		myPathView = new PathView();
		
		myEntriesList.getSelectionModel().selectedItemProperty().addListener(    
				(ObservableValue<? extends String> ov, String old_val, String new_val) -> {
//	                setChanged();
//	                notifyObservers(myData.get(new_val.split(":")[0]));
	                myPathView.clear();
	                if (new_val != null) {
		                myPathView.drawAll(myData.get(new_val.split(":")[0]).getPoints());
	                }
	    });
	}

	private void createButtons() {
		myAddButton = makeButton("Add New Path", e-> selectPaths());
		myDeleteButton = makeButton("Delete Path", e-> deleteButton());
		myFinishButton = makeButton("Finalize Path", e-> setRadius());
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
			myPathView.clear();
			myData.addPath("Path " + myPathID, myBuildingPath);
			myEntriesToShow.add("Path " + myPathID + ": " + myData.pointsToString("Path " + myPathID));
			myPathID++;
			myBuildingPath = null;
			myEntriesList.getSelectionModel().selectLast();
		} catch (NullPointerException e) {
			System.out.println("No checkpoints selected");
		}
	}
	
	private void selectPaths() {
		myBuildingPath = new Path("Path "+ myPathID, new ArrayList<Point>());
		myPathView.clear();
		MainGUI.myBoard.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				myPathView.draw(arg0.getSceneX(), arg0.getSceneY());
				myBuildingPath.getPoints().add(new Point(arg0.getSceneX(), arg0.getSceneY()));
			}
		});
	}
	
	private void setRadius() {
		TextInputDialog dialogBox = new TextInputDialog();
		dialogBox.setContentText("Select a radius for the path:");
		dialogBox.setHeaderText("Choose Radius");
		Optional<String> result = dialogBox.showAndWait();
		if (result.isPresent()) {
		     try {
		    	 myPathRadius = Double.parseDouble(result.get());
		    	 myBuildingPath.setRadius(myPathRadius);
		    	 finishPath();
		     }
		     catch(Exception e) {
		    	 Alert warning = new Alert(AlertType.ERROR, "You must select a valid number!");
		    	 warning.show();
		     }
		 }
	}

	@Override
	public void setData(ITabData data) {
		myData = (PathsData) data;
		for (Object o : myData.getData()) {
			Path path = (Path) o;
			myEntriesToShow.add(path.getName() + ": " + myData.pointsToString("Path " + myPathID));
			myPathID++;
		}
	}
}
