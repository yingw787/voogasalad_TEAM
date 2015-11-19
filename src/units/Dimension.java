package units;

public class Dimension {
	private int myWidth;
	private int myHeight;
	
	/**  Constructor for Dimension object
	 *   @params Width and height of Dimension object
	 **/
	public Dimension(int width, int height){
		this.myWidth = width;
		this.myHeight = height;
	}
	
	/**  
	 * @return width of Dimension object
	 */
	public int getWidth(){
		return myWidth;
	}
	
	/**
	 * @return height of Dimension object
	 */
	public int getHeight(){
		return myHeight;
	}
}
