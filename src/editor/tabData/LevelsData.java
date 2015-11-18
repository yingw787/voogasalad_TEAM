package editor.tabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.Bullet;
import units.Level;

public class LevelsData implements ITabData {

	Map<String,Level> myLevels;
	
	public LevelsData() {
		myLevels = new HashMap<String,Level>();
	}
	
	public void add(String id, Level l){
		myLevels.put(id, l);
	}
	
	public void remove(String id){
		myLevels.remove(id);
	}
	
	public Level get(String id){
		return myLevels.get(id);
	}
	
	@Override
	public List<Object> getData() {
		List<Object> allLevels = new ArrayList<Object>();
		allLevels.addAll(myLevels.values());
		return allLevels;
	}

}
