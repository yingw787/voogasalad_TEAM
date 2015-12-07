package gameEngine.requests;

import gameEngine.Engine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class TwiceSpeedRequest extends Request {

	public TwiceSpeedRequest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Engine e ) {
		// TODO Auto-generated method stub
		Timeline t = e.getTimeline();
		ObservableList<KeyFrame> kf = t.getKeyFrames();
		if(kf.get(0).getTime().equals(Duration.millis(Engine.MILLISECOND_DELAY ))){
			t.stop();
			kf.clear();
			kf.add(new KeyFrame(Duration.millis(Engine.MILLISECOND_DELAY /2),
					event ->e.step()));
			t.play();
		}else{
			t.stop();
			kf.clear();
			kf.add(new KeyFrame(Duration.millis(Engine.MILLISECOND_DELAY ),
					event ->e.step()));
			t.play();
		}
		
	}

}
