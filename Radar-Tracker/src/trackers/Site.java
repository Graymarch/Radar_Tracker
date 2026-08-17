package trackers;

import java.util.concurrent.atomic.AtomicInteger;

public class Site {
	private final static AtomicInteger idProvider = new AtomicInteger(2000);
	
	private final double x;
	private final double y;
	private final int id = idProvider.getAndIncrement();
	
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
	public int getId() {return this.id;}
}
