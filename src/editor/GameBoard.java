package editor;

import java.util.ResourceBundle;

import gamedata.xml.XMLConverter;
import image.ImageMaker;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBoard implements IView {
	private static ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private static double boardWidth = Double.parseDouble(myDefaults.getString("BoardWidth"));
	private static double boardHeight = Double.parseDouble(myDefaults.getString("BoardHeight"));
	private static String defaultBackground = myDefaults.getString("Background");
	private SubScene myBoard;
	private ImageView background; // = new ImageView(ImageMaker.getImage(defaultBackground));
	
	public GameBoard(String game, Pane pane, double width, double height) {
		myBoard = new SubScene(pane, width, height);
		myBoard.maxHeight(height);
		myBoard.maxWidth(width);
		
		XMLConverter xml = new XMLConverter();
		background = new ImageView();
		if (game == null) {
			background.setImage(ImageMaker.getImage(defaultBackground));
		} else {
			background.setImage(ImageMaker.getImage(xml.getBackground(game)));
		}
		background.fitWidthProperty().bind(myBoard.widthProperty());
		background.fitHeightProperty().bind(myBoard.heightProperty());
		pane.getChildren().add(background);
	}
	
	public GameBoard(String game, Pane pane) {
		this(game, pane, boardWidth, boardHeight);
	}

	public void setBackgroundImage(String image) {
		background.setImage(ImageMaker.getImage(image));
	}
	
	@Override
	public SubScene getView() {
		return myBoard;
	}
}
