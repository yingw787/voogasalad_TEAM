package editor.attributes;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageBox {
	
	ImageView myImage;
	Button myImageBox;
	
	public ImageBox(String img) {
		Image image = new Image(img);
		myImage = new ImageView(image);
		myImageBox = new Button("Change Image");
		myImageBox.setGraphic(myImage);
	}
	
	public Button getImageBox() {
		return myImageBox;
	}
	
	public ImageView getImage() {
		return myImage;
	}
}
