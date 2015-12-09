package gamedata.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import units.Game;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Point;
import units.Unit;

public class XMLConverter {
	
	XStream myXStream = new XStream(new StaxDriver());

	/** Creates sub-folder, if not already existing, that stores text files of each object in a games folder
	 *  Folder name =  type
	 *  File name(s) = name
	 *  @param  obj  Object to be converted to XML
	 *  @param  game String of game folder to store object XML
	 *  @param  type String of type folder to store object XML
	 *  @param  name String of file to store object XML
	 **/
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
//			System.out.println("Cannot convert parameter object into XML");
		}
	}
	
	/**  returns an ArrayList of objects type from XML
	 *   @param  game  Game from where object to be converted is contained
	 *   @param  type  Type of object to be converted (object must be stored in data folder)
	 **/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> fromXML(String game, String type) throws IOException {
		List<Object> myObjects = new ArrayList();
		try {
		 final File folder = new File("games/"+game+File.separator+type+File.separator);
		    for (File fileEntry : folder.listFiles()) {
		    	myObjects.add(myXStream.fromXML(fileEntry));
		    }
		}
		catch (Exception e) {
			System.out.println("Cannot convert " + type + " from " + game + " from XML");
		}
		return myObjects;
	}
	
	/**  returns a PlayerInfo object for certain game
	 *   @param  game  Game from where object to be converted is contained
	 **/
	public PlayerInfo getPlayerInfo(String game) throws IOException{
		List<Object> objects = fromXML(game,"Player");
		return (PlayerInfo) objects.get(0);
	}
	
	/**  returns a List of level objects for certain game
	 *   @param  game  Game from where objects to be converted are contained
	 **/
	public List<Level> getLevels(String game) throws IOException{
		List<Object> objects = fromXML(game,"Level");
		List<Level> myLevels = new ArrayList<Level>();
		for (Object o : objects){
			myLevels.add((Level) o);
		}
		return myLevels;
	}
	
	/**  returns a List of Unit objects for certain game
	 *   @param  game  Game from where object to be converted is contained
	 *   @param  type  Type of object to be returned
	 **/
	public List<Unit> getUnits(String game, String type) throws IOException{
		List<Object> objects = fromXML(game,type);
		List<Unit> myUnits = new ArrayList<Unit>();
		for (Object o : objects){
			myUnits.add((Unit) o);
		}
		return myUnits;
	}
	
	/**  returns a list of Path objects for certain game
	 *   @param  game  Game from where object to be converted is contained
	 **/
	public List<Path> getPaths(String game) throws IOException{
		List<Object> objects = fromXML(game,"Path");
		List<Path> myUnits = new ArrayList<Path>();
		for (Object o : objects){
			Path p = (Path) o;
			List<Point> newPoints = new ArrayList<Point>();
			List<Point> oldPoints = p.getPoints();
			for (Point point : oldPoints){
				newPoints.add(new Point(point.getX()-10, point.getY()-50));
			}
			Path newPath = new Path(p.getName(),newPoints);
			newPath.setRadius(p.getRadius());
			myUnits.add(newPath);
		}
		return myUnits;
	}
	
	public String getHelp(String game){
		List<Object> objects = null;
		ResourceBundle myResource = ResourceBundle.getBundle("resources.Default");
		String help = myResource.getString("URL");
		try {
			objects = fromXML(game,"Game");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Game dgame = (Game) objects.get(0);
			help = dgame.getHelpPage();
		}
		return help;
	}
	
	public boolean getPathVisibility(String game){
		List<Object> objects = null;
		ResourceBundle myResource = ResourceBundle.getBundle("resources.Default");
		boolean visible = true;
		try {
			objects = fromXML(game,"Game");
			Game dgame = (Game) objects.get(0);
			visible = dgame.getPathVisibility();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Game dgame = (Game) objects.get(0);
			visible = dgame.getPathVisibility();
		}
		return visible;
	}
	
	public String getDescription(String game){
		try {
			Game myGame = (Game) fromXML(game,"Game").get(0);
			String description = myGame.getDescription();
			return (description != null) ? description : "No description available";
		} catch (IOException e) {
			e.printStackTrace();
			return "No description available";
		}
	}
	
	public String getImage(String game){
		try {
			Game myGame = (Game) fromXML(game,"Game").get(0);
			String description = myGame.getImage();
			return description;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
