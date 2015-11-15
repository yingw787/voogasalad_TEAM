package editor.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XMLConverter {
	
	XStream myXStream = new XStream(new StaxDriver());

	public void toXML(Object obj, String type, String name) throws UnsupportedEncodingException, IOException {
		File file = new File("games/"+type+File.separator+name);
		file.getParentFile().mkdirs();
		file.createNewFile();
		myXStream.alias(type, obj.getClass());
		String xml = myXStream.toXML(obj);
		FileOutputStream fos = new FileOutputStream(file);
	    byte[] bytes = xml.getBytes("UTF-8");
        fos.write(bytes);
        fos.close();
	}
}
