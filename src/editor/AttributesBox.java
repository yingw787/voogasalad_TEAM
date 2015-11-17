package editor;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import editor.tabData.DataController;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AttributesBox implements IView, Observer {
	
	ScrollPane myAttributesBox;
	VBox myContent;
	Button myImageButton;
	private DataController myDataController;
	
	public AttributesBox(DataController data) {
		myAttributesBox = new ScrollPane();
		myAttributesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myAttributesBox.setFitToHeight(true);
		myContent = new VBox();
		setContents("purpleminion.png");
		myAttributesBox.setContent(myContent);
	}
	
	private void setContents(String img) {
		Image image = new Image(img);
		ImageView iv = new ImageView(image);
		iv.setFitHeight(50);
		iv.setFitWidth(50);
		myImageButton = new Button("Image");
		myImageButton.setOnAction(e-> changeImage());
		myImageButton.setContentDisplay(ContentDisplay.TOP);
		myImageButton.setGraphic(iv);
		myContent.getChildren().add(myImageButton);
	}
	
	private void changeImage() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
	}
	
	@Override
	public ScrollPane getView() {
		return myAttributesBox;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Attributes box: tab changed to " + (String) arg1);
	}
}
