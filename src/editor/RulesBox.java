package editor;

import java.util.Observable;
import java.util.Observer;

import units.Tower;
import units.Unit;
import editor.tabData.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RulesBox implements IView, Observer {
	
	private ScrollPane myRulesBox;
	private DataController myDataController;
	private VBox myBoxContent;
	private HBox myLabel;
	private VBox myRuleEditor;
	private HBox myButtons;
	private Button myAddButton;
	private Button myDeleteButton;
	private ListView<String> myCurrentRules;
	private ObservableList<String> myEntriesToShow;
	private Unit myCurrentUnit;
	
	public RulesBox(DataController data) {
		myRulesBox = new ScrollPane();
		myRulesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myRulesBox.setFitToHeight(true);
		myBoxContent = new VBox();
		myRulesBox.setContent(myBoxContent);
		addHeader();
		
	}

	/**
	 * 
	 */
	private void makeButtons() {
		myButtons = new HBox();
		myAddButton = new Button("Make Rule");
		myAddButton.setOnAction(e -> addRule());
		myDeleteButton = new Button("Delete Rule");
		myDeleteButton.setOnAction(e -> deleteRule());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
		myBoxContent.getChildren().add(myButtons);
	}
	
	private void deleteRule() {
		
	}

	private void addRule() {
		
	}

	private void addHeader() {
		myLabel = new HBox();
		Text label = new Text("Rules");
		label.setFont(Font.font("Verdana", 30));
		myLabel.getChildren().add(label);
		myBoxContent.getChildren().add(myLabel);
	}

	@Override
	public ScrollPane getView() {
		return myRulesBox;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
//		System.out.println("Rules box: tab changed to " + (String) arg);
		if(arg instanceof String){
			clearRules();
		}
		else if(arg instanceof Unit){
			System.out.println("Rules box: user selected tower: " + ((Tower)arg).getStringAttribute("Name"));
			clearRules();
			myCurrentUnit = (Unit) arg;
			showRules((Unit) arg);
		}
	}

	private void clearRules() {
		if(myRuleEditor != null){
			myBoxContent.getChildren().remove(myRuleEditor);
			myRuleEditor = null;
		}
	}

	private void showRules(Unit arg) {
		myRuleEditor = new VBox();
		myEntriesToShow = FXCollections.observableArrayList();
		myCurrentRules = new ListView<String>(myEntriesToShow);
		myCurrentRules.setMaxWidth(200);
		makeButtons();
		myRuleEditor.getChildren().addAll(myButtons, myCurrentRules);
		myBoxContent.getChildren().add(myRuleEditor);

		for(String key : arg.getRuleSet()){
			myEntriesToShow.add(key);
		}
		
	}
}
