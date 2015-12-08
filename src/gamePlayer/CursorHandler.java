package gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import gameEngine.requests.BuyTowerRequest;
import interfaces.IRequest;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import units.Point;
import units.Tower;
import units.Unit;

public class CursorHandler {

	private Circle myRange;
	private ImageView towerCursor;
	private Unit potentialPurchase;
	
	private BooleanHolder myBooleanHolder;
	private PathInfoHolder myPathInfoHolder;
	private ResourceBundle myResource;
	private Pane myPane;
	private Controller myController;
	
	public CursorHandler(BooleanHolder bh, Pane p, Controller c, ResourceBundle r, PathInfoHolder pih){
		myBooleanHolder = bh;
		myPane = p;
		myController = c;
		myResource = r;
		myPathInfoHolder = pih;
	}
	
	public void enableTowerPurchase(Unit u) {
		towerCursor = new ImageView(new Image(u.getStringAttribute("Image")));
		myRange = new Circle();
		myBooleanHolder.setPurchaseEnabled(true);
		myBooleanHolder.setClickEnabled(true);
				myRange.setFill(Color.RED);
				myRange.setStroke(Color.RED);
				myRange.setOpacity(Double.parseDouble(myResource.getString("rangeOpacity")));
				myRange.setRadius(u.getAttribute("Health"));
				towerCursor.setPreserveRatio(true);
				towerCursor.setFitHeight(Integer.parseInt(myResource.getString("towerHeight")));
				myPane.getChildren().add(myRange);
				myPane.getChildren().addAll(towerCursor);
				myPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						towerCursor.setLayoutX(me.getX());
						towerCursor.setLayoutY(me.getY());
						if (validPlacement(me)) {
							myBooleanHolder.setClickEnabled(true);
							towerCursor.setImage(new Image(u.getStringAttribute("Image")));
							myRange.setCenterX(me.getX() + towerCursor.getFitWidth() + 10);
							myRange.setCenterY(me.getY() + towerCursor.getFitHeight() / 2);
							myRange.setVisible(true);
						} else {
							myBooleanHolder.setClickEnabled(false);
							towerCursor.setImage(new Image("xmark.png"));
							myRange.setVisible(false);
						}
					}
				});
			potentialPurchase = u;
	}
	
	private boolean validPlacement(MouseEvent me){
		boolean valid = true;
		for (Line l : myPathInfoHolder.getIllegalZones()){
			if (l.contains(me.getSceneX(), me.getSceneY())){
				valid = false;
			}
		}
		return valid;
	}
	
	public void paneClicked(MouseEvent arg0){
		if (myBooleanHolder.getPurchaseEnabled()&&myBooleanHolder.getClickEnabled()){
			BuyTowerRequest buyRequest = new BuyTowerRequest((Tower) potentialPurchase, new Point(arg0.getX(), arg0.getY()));
			List<IRequest> requestSender = new ArrayList<IRequest>();
			requestSender.add(buyRequest);
			myController.update(requestSender);
			myBooleanHolder.setPurchaseEnabled(false);
			myPane.getChildren().remove(myRange);
			myPane.getChildren().remove(towerCursor);
		}
	}
}
