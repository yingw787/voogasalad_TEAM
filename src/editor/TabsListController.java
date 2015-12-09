package editor;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.scene.Node;
import editor.tabData.DataController;
import editor.tabs.*;

public class TabsListController {
	private TabsList myTabsList;
	private Map<String, ITab> myTabs;
	// Move this out to a resource file
	private String[] myTabNames = {"Game", "Player", "Towers", "Troops", "Bullets", "Paths", "Levels"};
	
	/**  Constructor for TabsListController object which stores info on each Tab's state
	 *   @param DataController DataController which stores current state
	 **/
	public TabsListController(DataController data) {
		myTabsList = new TabsList();
		myTabs = new HashMap<String, ITab>();
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
				//ln(tabName);
				e.printStackTrace();
			}
			((Observable) tab).addObserver(myTabsList);
			myTabsList.addTab(tabName, tabContent);
		}
	}
	
	public Node getView(){
		return myTabsList.getView();
	}
	
	public TabsList getTabsList(){
		return myTabsList;
	}
	
	public ITab getTab(String name){
		return myTabs.get(name);
	}
}
