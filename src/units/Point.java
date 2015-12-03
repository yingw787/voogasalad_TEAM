package units;

public class Point {
	private double myX;
	private double myY;
	
	/**  Constructor for Point object
	 *   @params X and Y values of Point object
	 **/
	public Point(double x, double y){
		this.myX = x;
		this.myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public double getDistance(Point p) {
		return Math.sqrt((this.myX - p.myX) * (this.myX - p.myX) + (this.myY - p.myY) * (this.myY - p.myY));
	}
	
	public void setX(double x){
		this.myX =x;
	}
	
	public void setY(double y){
		this.myY = y;
	}
}
