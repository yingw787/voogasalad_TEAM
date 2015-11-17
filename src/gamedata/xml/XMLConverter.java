package gamedata.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import units.Unit;

public class XMLConverter {
	
	XStream myXStream = new XStream(new StaxDriver());

	/* creates sub-folder, if not already existing, that stores text files of each object in a games folder
	 * folder name: type
	 * file name(s): name
	 * call this method on individual objects
	 */
	public void toXML(Object obj, String type, String name) throws UnsupportedEncodingException, IOException {
		try {
		File file = new File("games/"+type+File.separator+name);
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
	public List<Unit> fromXML(String type) throws IOException {
		List<Unit> myObjects = new ArrayList<Unit>();
		try {
		 final File folder = new File("games/"+type+File.separator);
		    for (File fileEntry : folder.listFiles()) {
		    	myObjects.add(((Unit) myXStream.fromXML(fileEntry)));
		    }
		}
		
		catch (Exception e) {
			System.out.println("Cannot convert" + type + " from XML");
		}
		return myObjects;
	}
}
