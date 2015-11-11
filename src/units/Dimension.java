package units;

public class Dimension {
	private int myWidth;
	private int myHeight;
	
	public Dimension(int width, int height){
		this.myWidth = width;
		this.myHeight = height;
	}
	
	
	public int getWidth(){
		return myWidth;
	}
	
	public int getHeight(){
		return myHeight;
	}
}
