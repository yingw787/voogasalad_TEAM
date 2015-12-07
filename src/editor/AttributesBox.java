package editor;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javax.imageio.ImageIO;

import units.Unit;
import editor.tabData.DataController;
import editor.tabData.ITabData;
import editor.tabData.TroopsData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


public class AttributesBox extends Observable implements IView, Observer {
	

	private Map<String, File> imageCache; // where to keep this??
	
	private ScrollPane myAttributesBox;

	private DataController myDataController;
	private VBox myBoxContent;
	private HBox myLabel;
	private VBox myCurrentAttributes;
	private Unit myCurrentUnit;
	
	/**  Constructor for AttributesBox object which stores and edits Tower and Troop attributes
	 *   @params DataController DataController object storing Unit attributes
	 **/
	public AttributesBox(DataController data) {
		myAttributesBox = new ScrollPane();
		myAttributesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myAttributesBox.setFitToHeight(true);

		myBoxContent = new VBox();
		myAttributesBox.setContent(myBoxContent);
		addHeader();
		imageCache = new HashMap<String, File>();
	}
	
	private void addHeader() {
		myLabel = new HBox();
		Text label = new Text("Attributes");
		label.setFont(Font.font("Verdana", 30));
		myLabel.getChildren().add(label);
		myBoxContent.getChildren().add(myLabel);
	}

	@Override
	public ScrollPane getView() {
		return myAttributesBox;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
//		System.out.println("Attributes box: tab changed to " + (String) arg1);
		if(arg1 instanceof String){
			clearAttributes();
		}
		else if(arg1 instanceof Unit){
			//System.out.println("Attributes box: user selected Unit: " + ((Unit)arg1).getStringAttribute("Name"));
			myCurrentUnit = (Unit) arg1;
			clearAttributes();
			showAttributes();
		}
	}

	private void showAttributes() {
		myCurrentAttributes = new VBox();
		myBoxContent.getChildren().add(myCurrentAttributes);
		for(String stringAttr : myCurrentUnit.getStringAttributeSet()){
//			myCurrentAttributes.getChildren().add(new Text(stringAttr + ": " + myCurrentUnit.getStringAttribute(stringAttr)));
			makeEditableStringAttribute(stringAttr);
		}
		for(String attr : myCurrentUnit.getAttributeSet()){
//			myCurrentAttributes.getChildren().add(new Text(attr + ": " + myCurrentUnit.getAttribute(attr)));
			makeEditableAttribute(attr);
		}
	}

	/**
	 * @param attribute
	 */
	private void makeEditableStringAttribute(String attribute) {
		final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
		if (myCurrentUnit.getStringAttribute(attribute).matches(IMAGEFILE_SUFFIXES)) {
			makeEditableImageAttribute(attribute);
		} else {
			Button attributeButton = new Button();
			String label = myCurrentUnit.getStringAttribute(attribute);
			attributeButton.setText(attribute + ": " + label);
			attributeButton.setOnAction(e -> {
				TextInputDialog dialog = new TextInputDialog(myCurrentUnit.getStringAttribute(attribute));
				dialog.setTitle("Change Attribute Value");
				dialog.setHeaderText("Changing value for " + attribute);
				dialog.setContentText("Please enter a new value:");
				Optional<String> result = dialog.showAndWait();
				result.ifPresent(newValue -> {
					if (attribute.equals("Name")) {

						setChanged();
						notifyObservers(newValue);
					}
					myCurrentUnit.setAttribute(attribute, newValue);
					clearAttributes();
					showAttributes();
				});
			});
			attributeButton.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
			myCurrentAttributes.getChildren().add(attributeButton);
		}
	}
	
	/**
	 * @param attribute
	 */
	private void makeEditableImageAttribute(String attribute) {
		Button attributeButton = new Button();
		String label = myCurrentUnit.getStringAttribute(attribute);
		List<String> imageSuffixList = new ArrayList<String>();
		for (String suffix : ImageIO.getReaderFileSuffixes()) {
			imageSuffixList.add("*." + suffix);
		}
		myCurrentAttributes.getChildren().add(new Label(attribute + ": "));
		ImageView myImage;
		try {
			myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(label)));
		} catch (Exception runtime) {
			myImage = new ImageView(new Image(imageCache.get(label).toURI().toString()));
		}

		myImage.setFitHeight(50);
		myImage.setPreserveRatio(true);
		attributeButton.setGraphic(myImage);
		attributeButton.setText(label);

		attributeButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Image From File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", imageSuffixList));
			File selectedFile = fileChooser.showOpenDialog(new Stage());

			if (selectedFile != null) {
				try {
					File file = new File("images/" + selectedFile.getName());
					file.getParentFile().mkdirs();
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					Path path = Paths.get(selectedFile.getPath());
					byte[] data = Files.readAllBytes(path);
					fos.write(data);
					fos.close();
					myCurrentUnit.setAttribute(attribute, selectedFile.getName());
					imageCache.put(selectedFile.getName(), selectedFile);
					clearAttributes();
					showAttributes();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		attributeButton.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		myCurrentAttributes.getChildren().add(attributeButton);
	}

	private void makeEditableAttribute(String attribute) {
		Button attributeButton = new Button(attribute + ": " + myCurrentUnit.getAttribute(attribute));
		attributeButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		attributeButton.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog("" + myCurrentUnit.getAttribute(attribute));
			dialog.setTitle("Change Attribute Value");
			dialog.setHeaderText("Changing value for " + attribute);
			dialog.setContentText("Please enter a new number:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(newValue -> {
				try{
					Double.parseDouble(newValue);
					myCurrentUnit.setAttribute(attribute, Double.parseDouble(newValue));
					clearAttributes();
					showAttributes();
				} catch(Exception excep){
					Alert warning = new Alert(AlertType.INFORMATION);
					warning.setTitle("Warning");
					warning.setHeaderText("Invalid value");
					warning.setContentText("Only numbers allowed.");
					warning.show();
				//	System.out.println("fail");
				}

			});
		});
		myCurrentAttributes.getChildren().add(attributeButton);
	}
	
	private void clearAttributes() {
		if(myCurrentAttributes != null){
			myBoxContent.getChildren().remove(myCurrentAttributes);
			myCurrentAttributes = null;
		}
	}
}
