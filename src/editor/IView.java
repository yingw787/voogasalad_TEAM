package editor;

import javafx.scene.Node;

public interface IView {

	/**  Interface for each Node of editor to implement
	 * 	 Returns Node representing specific component of editor
	 **/
	public Node getView();
	
}
