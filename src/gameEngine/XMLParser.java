package gameEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLParser {
	private final static String SALADNAME = "Salad_T.E.A.M";
    private final static String DEFAULTFILE= "src/resources/EngineTest.xml";
	XStream myXstream;
	Document myDocument;
	
	public XMLParser() {
		myXstream = new XStream(new DomDriver());
	}
	
	public void writeEnviroment(InitialEnvironment environ){
		try {
			ObjectOutputStream out = myXstream.createObjectOutputStream(new FileWriter(DEFAULTFILE),SALADNAME);
			out.writeObject(environ);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InitialEnvironment readEnvironment(){
		try {
			ObjectInputStream in = myXstream.createObjectInputStream(new FileReader(DEFAULTFILE));
			InitialEnvironment e = (InitialEnvironment)in.readObject();
			in.close();
			return e;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
}
