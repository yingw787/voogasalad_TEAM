package editor.tabs;

import editor.IView;
import editor.tabData.ITabData;

public class ScenesTab extends ATab implements IView, ITab {
	private ITabData myData;

	public ScenesTab(){
		initTab();
	}


	@Override
	public void setData(ITabData data) {
		myData = data;
	}

}
