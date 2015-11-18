package editor.tabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.Troop;

public class TroopsData implements ITabData {

public static Map<String, Troop> myTroops;
	
	public TroopsData () {
		myTroops = new HashMap<String, Troop>();
	}
	
	public void add(String id, Troop b){
		myTroops.put(id, b);
	}
	
	public void remove(String id){
		myTroops.remove(id);
	}
	
	public Troop get(String id){
		return myTroops.get(id);
	}
	
	public String[] getBulletNamesArray(){
		String[] arr = new String[myTroops.keySet().size()];
		int i=0;
		for(String attr : myTroops.keySet()){
			arr[i++] = attr;
		}
		return arr;
	}
	
	@Override
	public List<Object> getData() {
		List<Object> allTroops = new ArrayList<Object>();
		allTroops.addAll(myTroops.values());
		return allTroops;
	}

}
