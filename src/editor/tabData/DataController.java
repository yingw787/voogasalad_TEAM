package editor.tabData;

import java.util.HashMap;
import java.util.Map;

import editor.IView;
import editor.tabData.*;

public class DataController {
	private Map<String, ITabData> myData;
	// Move this out to properties file later
	private String[] types = {"Bullets", "Game", "Levels", "Paths", "Scenes", "Towers", "Troops", "Player"};
	
	public DataController(){
		initialize();
	}

	private void initialize() {
		myData = new HashMap<String, ITabData>();
		
		for(String type : types){
			ITabData dataHolder = null;
			try {
				dataHolder = (ITabData) Class.forName("editor.tabData." + type + "Data").newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			myData.put(type, dataHolder);
		}
	}
	
	public ITabData getData(String type){
		//System.out.println("Trying to get " + type + " data");
		return myData.get(type);
	}
	
}
