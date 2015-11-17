package gamedata.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import units.Level;
import units.PlayerInfo;
import units.Unit;

public class XMLConverter {
	
	XStream myXStream = new XStream(new StaxDriver());

	/* creates sub-folder, if not already existing, that stores text files of each object in a games folder
	 * folder name: type
	 * file name(s): name
	 * call this method on individual objects
	 */
	public void toXML(Object obj, String game, String type, String name) throws UnsupportedEncodingException, IOException {
		try {
		File file = new File("games/"+game+File.separator+type+File.separator+name);
		file.getParentFile().mkdirs();
		file.createNewFile();
		String xml = myXStream.toXML(obj);
		FileOutputStream fos = new FileOutputStream(file);
	    byte[] bytes = xml.getBytes("UTF-8");
        fos.write(bytes);
        fos.close();
		}
		
		catch (Exception e) {
			System.out.println("Cannot convert parameter object into XML");
		}
	}
	
	/* returns an ArrayList of objects of a certain type from XML
	 * @params: type of object that has been converted to XML previously 
	 * and is stored in the games folder under a subfolder 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Object> fromXML(String game, String type) throws IOException {
		List<Object> myObjects = new ArrayList();
		try {
		 final File folder = new File("games/"+game+File.separator+type+File.separator);
		    for (File fileEntry : folder.listFiles()) {
		    	myObjects.add(myXStream.fromXML(fileEntry));
		    }
		}
		catch (Exception e) {
			System.out.println("Cannot convert " + type + "from " + game + " from XML");
		}
		return myObjects;
	}
	
	public List<PlayerInfo> getPlayerInfo(String game) throws IOException{
		List<Object> objects = fromXML(game,"PlayerInfo");
		List<PlayerInfo> myInfo = new ArrayList<PlayerInfo>();
		for (Object o : objects){
			myInfo.add((PlayerInfo) o);
		}
		return myInfo;
	}
	
	public List<Level> getLevels(String game) throws IOException{
		List<Object> objects = fromXML(game,"Level");
		List<Level> myLevels = new ArrayList<Level>();
		for (Object o : objects){
			myLevels.add((Level) o);
		}
		return myLevels;
	}
	
	public List<Unit> getUnits(String game, String type) throws IOException{
		List<Object> objects = fromXML(game,type);
		List<Unit> myUnits = new ArrayList<Unit>();
		for (Object o : objects){
			myUnits.add((Unit) o);
		}
		return myUnits;
	}
}
