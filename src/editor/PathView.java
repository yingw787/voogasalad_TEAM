package editor;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import units.Path;
import units.Point;

public class PathView {
	private Point lastFlag;
	private Paint myPaint;
	private double myThickness;
	private List<ImageView> myFlags;
	private Image myFlagImage;
	
	public PathView() {
		myPaint = Color.AZURE;
		myThickness = 5;
		myFlagImage = new Image("flag.png");
		myFlags = new ArrayList<ImageView>();
	}

	private void drawFlag(double x, double y) {
		ImageView flag = new ImageView(myFlagImage);
		flag.setLayoutX(x);
		flag.setLayoutY(y);
		((Pane) MainGUI.myBoard.getRoot()).getChildren().add(flag);
		myFlags.add(flag);
	}
	
	public void draw(double x, double y) {
		// add flag to board
		drawFlag(x, y-50); //arg0.getSceneY() - 50
		
		//add line
		if (lastFlag != null) {
			Line line = new Line(lastFlag.getX(), lastFlag.getY()-25, x, y-25);
			line.setStrokeWidth(myThickness);
			line.setStroke(myPaint);
			((Pane) MainGUI.myBoard.getRoot()).getChildren().add(line);
		}
		lastFlag = new Point(x, y);
	}
	
	public void draw(Point point) {
		draw(point.getX(), point.getY());
	}
	
	public void drawAll(List<Point> pointList) {
		for (Point point : pointList) {
			draw(point);
		}
	}
	
	public void clear() {
		lastFlag = null;
		while (((Pane) MainGUI.myBoard.getRoot()).getChildren().size() > 1) {
			((Pane) MainGUI.myBoard.getRoot()).getChildren().remove(((Pane) MainGUI.myBoard.getRoot()).getChildren().size()-1);
		}
		myFlags.clear();
	}
}


/*	private void selectPaths() {
		myBuildingPath = new Path("Path "+ myPathID, new ArrayList<Point>());
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
			}
		});
		*/
