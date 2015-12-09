package image;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import startup.Startup;

public class ImageMaker {
	private static Map<String, File> imageCache;

	public static void uploadImage(File localFile) {
		try {
			File file = new File("images/" + localFile.getName());
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			Path path = Paths.get(localFile.getPath());
			byte[] data = Files.readAllBytes(path);
			fos.write(data);
			fos.close();
			addToCache(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void addToCache(File file) {
		if (imageCache == null) {
			imageCache = new HashMap<String,File>();
		}
		imageCache.put(file.getName(), file);
	}
	
	/**
	 * Looks for the file first in existing image files in the project, then in cache
	 * 
	 * @param name Name of the file, including extension
	 * @return Image from file with the given name
	 */
	public static Image getImage(String name) {
		Image myImage;
		try {
			try {
				myImage = new Image(Startup.class.getClassLoader().getResourceAsStream(name));
			} catch (Exception e) {
				myImage = new Image(imageCache.get(name).toURI().toString());
			}
		} catch (Exception e1) {
			myImage = new Image(Startup.class.getClassLoader().getResourceAsStream("questionmark.png"));
		}
		return myImage;
	}
}
