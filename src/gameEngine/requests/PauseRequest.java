package gameEngine.requests;

import gameEngine.Engine;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;

public class PauseRequest extends Request {

	public PauseRequest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Engine e) {
		// TODO Auto-generated method stub
		Timeline t = e.getTimeline();
		if(t.getStatus() == Status.RUNNING)
			t.stop();
		else
			t.play();
	}

}
