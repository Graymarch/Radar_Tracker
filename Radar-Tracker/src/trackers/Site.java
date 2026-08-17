package trackers;

public class Site {
	private double x;
	private double y;
	
	public Site(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance (double x, double y) {
		
		double xDiff = this.x - x;
		double yDiff = this.y - y;
		
		return Math.hypot(xDiff, yDiff);
	}
	
	public double getX() {return this.x;}
	public double getY() {return this.y;}
}
