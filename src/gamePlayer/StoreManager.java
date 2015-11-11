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
		for (int i = 0; i < 8; i++) {
			ToggleButton button = buttonFactory("turret_transparent.png");
			list.add(button);
			if (i==7){
				button.setDisable(true);
			}
		}
		ToggleGroup group = new ToggleGroup();
		for (ToggleButton tb : list) {
			tb.setToggleGroup(group);
		}
//		myHBox.getChildren().add(buttonFactory());
		myHBox.getChildren().addAll(list);
	}
	
	private ToggleButton buttonFactory(String url){
		Image image = new Image(url);
		ImageView imageview = new ImageView(image);
		imageview.setFitHeight(73);
		imageview.setPreserveRatio(true);
		ToggleButton button = new ToggleButton("Turret", imageview);
		return button;
	}
}
