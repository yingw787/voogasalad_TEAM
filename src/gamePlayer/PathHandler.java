// This entire file is part of my masterpiece.
// Vanessa Wu

package gamePlayer;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import units.Path;
import units.Point;

public class PathHandler {
	private Pane myPane;
	private PathInfoHolder myPathInfoHolder;
	private ResourceBundle myResource;
	private boolean myPathVisibility;
	
	public PathHandler(Pane p, PathInfoHolder pih, ResourceBundle r, boolean visible){
		myPane = p;
		myPathInfoHolder = pih;
		myResource = r;
		myPathVisibility = visible;
	}
	
	private Line drawPath(Point startLoc, Point endLoc, Double radius){
		Line path = new Line();
		Line zone = new Line();
		path.setStartX(startLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setStartY(startLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setEndX(endLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setEndY(endLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		path.setStrokeLineCap(StrokeLineCap.ROUND);
		path.setStrokeWidth(Integer.parseInt(myResource.getString("pathStrokeWidth")));
		path.setVisible(myPathVisibility);
		zone.setStartX(startLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setStartY(startLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setEndX(endLoc.getX()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setEndY(endLoc.getY()+Integer.parseInt(myResource.getString("pathOffset")));
		zone.setStrokeLineCap(StrokeLineCap.ROUND);
		zone.setStrokeWidth(radius);
		zone.setVisible(false);
		myPathInfoHolder.getIllegalZones().add(zone);
		return path;
	}
	
	public void showPaths(List<Path> pathsForLevel) {
		myPane.getChildren().removeAll(myPathInfoHolder.getCurrentPaths());
		myPane.getChildren().removeAll(myPathInfoHolder.getIllegalZones());
		myPathInfoHolder.getIllegalZones().clear();
		myPathInfoHolder.getCurrentPaths().clear();
		for (Path p : pathsForLevel){
			List<Point> myPoints = p.getPoints();
			for (int i = 0; i < myPoints.size()-1; i++){
				myPathInfoHolder.getCurrentPaths().add(drawPath(myPoints.get(i),myPoints.get(i+1),p.getRadius()));
			}
		}
		for (Line l : myPathInfoHolder.getCurrentPaths()){
			l.setStrokeType(StrokeType.OUTSIDE);
			LinearGradient linearGradient = new LinearGradient(50-18d, 0d, 50+18d, 0d,
					 false, CycleMethod.REFLECT,new Stop(0,Color.BURLYWOOD), new Stop(1,Color.PERU));
			l.setStroke(linearGradient);
		}
		myPane.getChildren().addAll(myPathInfoHolder.getCurrentPaths());
		myPane.getChildren().addAll(myPathInfoHolder.getIllegalZones());
	}
}
