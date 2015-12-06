package editor.tabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import units.Bullet;

public class BulletsData extends ATabData {
	Map<String, Bullet> myBullets;
	
	public BulletsData () {
		myBullets = new HashMap<String, Bullet>();
	}
	
	public void add(String id, Bullet b){
		myBullets.put(id, b);
	}
	
	public void remove(String id){
		myBullets.remove(id);
	}
	
	public Bullet get(String id){
		return myBullets.get(id);
	}
	
	public String[] getBulletNamesArray(){
		String[] arr = new String[myBullets.keySet().size()];
		int i=0;
		for(String attr : myBullets.keySet()){
			arr[i++] = attr;
		}
		return arr;
	}
	
	@Override
	public List<Object> getData() {
		List<Object> allBullets = new ArrayList<Object>();
		allBullets.addAll(myBullets.values());
		return allBullets;
	}

	@Override
	public void setData(List<Object> list) {
		for (Object o : list) {
			Bullet bullet = (Bullet) o;
			add(bullet.getStringAttribute("Name"), bullet);
		}
	}

}
