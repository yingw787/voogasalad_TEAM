package editor.tabData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import editor.IView;
import editor.tabData.*;
import gamedata.xml.*;

public class DataController {
	private String myName;
	private Map<String, ITabData> myData;
	// Move this out to properties file later
	private String[] types = {"Bullets", "Game", "Levels", "Paths", "Scenes", "Towers", "Troops", "Player"};
	
	public DataController(String name){
		myName = name;
		initialize();
	}

	private void initialize() {
		myData = new HashMap<String, ITabData>();
		XMLConverter converter = new XMLConverter();

			for (String type : types) {
				ITabData dataHolder = null;
				ATabData aData = null;
				try {
					aData = (ATabData) Class.forName("editor.tabData." + type + "Data").newInstance();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					if (myName == null) {
						dataHolder = (ITabData) Class.forName("editor.tabData." + type + "Data").newInstance();
					} else {
						if (type.equals("Game") || type.equals("Player")) {
							aData.setData(converter.fromXML(myName, type));
							dataHolder = (ITabData) aData;
						} else {
							aData.setData(converter.fromXML(myName, type.substring(0, type.length()-1)));
							dataHolder = (ITabData) aData;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				myData.put(type, dataHolder);
//				ITabData temp = myData.get(type);
			}
	}
	
	public ITabData getData(String type){
		////ln("Trying to get " + type + " data");
		
		ITabData result = myData.get(type);
		return result;
	}
	
//	class MyTabData implements ITabData {
//		List<Object> myList;
//		
//		MyTabData (List<Object> list) {
//			myList = list;
//		}
//
//		@Override
//		public List<Object> getData() {
//			return myList;
//		}
//	}
	
}
