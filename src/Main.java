import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gamedata.xml.TestXML;
import gamedata.xml.XMLConverter;
import javafx.application.Application;
import javafx.stage.Stage;
import startup.Startup;


public class Main extends Application{

	public static void main(String[] args) throws IOException {
		TestXML t = new TestXML();
		t.testFrom();
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Startup(stage);
	}
}