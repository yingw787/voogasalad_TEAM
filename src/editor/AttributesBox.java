package editor;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;


import units.Tower;
import units.Unit;
import editor.tabData.DataController;
import editor.tabData.ITabData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class AttributesBox extends Observable implements IView, Observer {
	

	private ScrollPane myAttributesBox;


	private DataController myDataController;
	private VBox myBoxContent;
	private HBox myLabel;
	private VBox myCurrentAttributes;
	private Unit myCurrentUnit;
	
	public AttributesBox(DataController data) {
		myAttributesBox = new ScrollPane();
		myAttributesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myAttributesBox.setFitToHeight(true);

		myBoxContent = new VBox();
		myAttributesBox.setContent(myBoxContent);
		addHeader();

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
			System.out.println("Attributes box: user selected Unit: " + ((Unit)arg1).getStringAttribute("Name"));
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
		Button attributeButton = new Button(attribute + ": " + myCurrentUnit.getStringAttribute(attribute));
		attributeButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		attributeButton.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog(myCurrentUnit.getStringAttribute(attribute));
			dialog.setTitle("Change Attribute Value");
			dialog.setHeaderText("Changing value for " + attribute);
			dialog.setContentText("Please enter a new value:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(newValue -> {
				if(attribute.equals("Name")){
					
					setChanged();
					notifyObservers(newValue);
				}
				myCurrentUnit.setAttribute(attribute, newValue);
				clearAttributes();
				showAttributes();
			});
		});
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
					System.out.println("fail");
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
