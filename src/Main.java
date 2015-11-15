import java.io.IOException;
import java.io.UnsupportedEncodingException;

//import editor.xml.TestObjectHolder;
//import editor.xml.XMLConverter;
import javafx.application.Application;
import javafx.stage.Stage;
import startup.Startup;
//import units.Tower;
//import units.Troop;

public class Main extends Application{

	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
//		XMLConverter myConverter = new XMLConverter();
//		TestObjectHolder myHolder = new TestObjectHolder();
//		for (Tower tower : myHolder.getTowers()) {
//			myConverter.toXML(tower, tower.getStringAttribute("Type"), tower.getName());
//		}
//		for (Troop troop : myHolder.getTroops()) {
//			myConverter.toXML(troop, troop.getStringAttribute("Type"), troop.getName());
//		}
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Startup(stage);
	}
}