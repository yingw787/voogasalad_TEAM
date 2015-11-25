package gamePlayer;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class EditMenu extends Menu{
	private Map myMap;
	
	public EditMenu(Map m){
		super("Edit");
		this.myMap= m;
		myMap.initialize();
		addViews();
	}

	private void addViews() {
		MenuItem addBackground = new MenuItem("Add Background Map");
		addBackground.setOnAction(e -> uploadMap());
		this.getItems().addAll(addBackground);
	}

	
	public void uploadMap() {
	    FileChooser fileChooser = new FileChooser();
	    File selectedFile = fileChooser.showOpenDialog(null);
	    Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		
		String label = null;
	    String fileName;
	    
	    if (selectedFile != null) {
	        fileName = selectedFile.getName();
	        System.out.println(fileName + " selected");
	        myMap.setBackgroundMap(new Image(getClass().getClassLoader().getResourceAsStream(fileName)));
	        
	    }
	    else {
	        if (selectedFile == null) {
	        	label = "UploadCanceled";
				alert.setContentText(label);
				alert.showAndWait();
	        }
	    }
	}
	
}


