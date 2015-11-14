package editor;

import java.util.Map;

import javafx.scene.Node;
import editor.tabData.DataController;
import editor.tabs.*;

public class TabsListController {
	private TabsList myTabsList;
	private Map<String, ITab> myTabs;
	// Move this out to a resource file
	private String[] myTabNames = {"Scenes", "Towers", "Troops", "Bullets", "Paths", "Levels", "Game"};
	
	public TabsListController(DataController data) {
		myTabsList = new TabsList();
		initiateTabs(data);
	}
	
	private void initiateTabs(DataController data){
		for(String tabName : myTabNames){
			Node tabContent = null;
			ITab tab = null;
			try {
				tab = (ITab) Class.forName("editor.tabs." + tabName + "Tab").newInstance();
				tab.setData(data.getData(tabName));
				myTabs.put(tabName, tab);
				tabContent = ((IView) tab).getView();
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
	
	public TabsList getTabsList(){
		return myTabsList;
	}
}
