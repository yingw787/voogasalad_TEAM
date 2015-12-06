package editor.tabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.Tower;

public class TowersData extends ATabData {
	private Map<String, Tower> myTowers;
	
	public TowersData(){
		myTowers = new HashMap<String, Tower>();
	}
	
	public void add(String id, Tower t){
		myTowers.put(id, t);
	}
	
	public void remove(String id){
		myTowers.remove(id);
	}
	
	public Tower get(String id){
		return myTowers.get(id);
	}
	
	@Override
	public List<Object> getData() {
		List<Object> allTowers = new ArrayList<Object>();
		allTowers.addAll(myTowers.values());
		return allTowers;
	}

	@Override
	public void setData(List<Object> list) {
		for (Object o : list) {
			Tower tower = (Tower) o;
			add(tower.getStringAttribute("Name"), tower);
		}
	}
}
