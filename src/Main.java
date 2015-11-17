import java.io.IOException;
import java.util.ArrayList;

import gamedata.xml.XMLConverter;
import javafx.application.Application;
import javafx.stage.Stage;
import startup.Startup;
import units.Unit;


public class Main extends Application{

	public static void main(String[] args) throws IOException {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Startup(stage);
	}
}