package editor;

import image.ImageMaker;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameBoard implements IView {
	
	SubScene myBoard;
	ImageView background;
	
	public GameBoard(Pane pane, double width, double height) {
		myBoard = new SubScene(pane, width, height);
		Image grassBG = ImageMaker.getImage(("grass.jpg"));
		background = new ImageView(grassBG);
		pane.getChildren().add(background);
		myBoard.maxHeight(background.getFitHeight());
	}
	
	@Override
	public SubScene getView() {
		return myBoard;
	}
}
