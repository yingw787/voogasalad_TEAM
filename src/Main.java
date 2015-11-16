import javafx.application.Application;
import javafx.stage.Stage;
import startup.Startup;

public class Main extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Startup(stage);
	}
}