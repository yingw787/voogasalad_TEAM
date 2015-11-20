package editor.tabs;

import editor.IView;
import editor.tabData.ITabData;

/**  Editor tab for Scenes
 **/
public class ScenesTab extends ATab implements IView, ITab {
	private ITabData myData;

	/**  Constructor for editor tab for Scenes
	 **/
	public ScenesTab(){
		initTab();
	}


	@Override
	public void setData(ITabData data) {
		myData = data;
	}

}
