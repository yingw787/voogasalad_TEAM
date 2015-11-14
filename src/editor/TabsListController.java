package editor;

import javafx.scene.Node;
import editor.tabs.*;

public class TabsListController {
	private TabsList myTabsList;
	// Move this out to a resource file
	private String[] myTabNames = {"Scenes", "Towers", "Troops", "Bullets", "Paths", "Levels", "Game"};
	
	public TabsListController() {
		myTabsList = new TabsList();
		initiateTabs();
	}
	
	private void initiateTabs(){
		for(String tabName : myTabNames){
			Node tabContent = null;
			try {
				tabContent = ((IView) Class.forName("editor.tabs." + tabName + "Tab").newInstance()).getView();
			} catch (Exception e){
				System.out.println(tabName);
				e.printStackTrace();
			}
			myTabsList.addTab(tabName, tabContent);
		}
	}
	
	public Node getView(){
		return myTabsList.getView();
	}
}
