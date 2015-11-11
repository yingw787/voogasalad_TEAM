package gamePlayer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StoreManager {
	private ScrollPane myScrollPane;
	private HBox myHBox;
	
	public ScrollPane initialize(){
		myScrollPane = new ScrollPane();
		myHBox = new HBox();
		myScrollPane.setContent(myHBox);
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		populate();
		return myScrollPane;
	}
	private void populate(){
		List<ToggleButton> list = new ArrayList<ToggleButton>();
		for (int i = 0; i < 10; i++) {
			list.add(buttonFactory());
		}
		ToggleGroup group = new ToggleGroup();
		for (ToggleButton tb : list) {
			tb.setToggleGroup(group);
		}
//		myHBox.getChildren().add(buttonFactory());
		myHBox.getChildren().addAll(list);
	}
	
	private ToggleButton buttonFactory(){
		Image image = new Image("turret_transparent.png");
		ImageView imageview = new ImageView(image);
		imageview.setFitHeight(82);
		imageview.setPreserveRatio(true);
		ToggleButton button = new ToggleButton("Turret", imageview);
		return button;
	}
}
