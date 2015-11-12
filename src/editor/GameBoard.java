package editor;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameBoard implements IView {
	
	SubScene myBoard;
	ImageView background;
	
	public GameBoard(Group group, double width, double height) {
		myBoard = new SubScene(group, width, height);
		Image grassBG = new Image(getClass().getClassLoader().getResourceAsStream("grass.jpg"));
		background = new ImageView(grassBG);
		group.getChildren().add(background);
	}
	
	@Override
	public SubScene getView() {
		return myBoard;
	}
}
