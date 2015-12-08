import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import startup.Startup;


public class Main extends Application{

	public static void main(String[] args) throws IOException {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Startup(stage).getScene());
		stage.show();
	}
}