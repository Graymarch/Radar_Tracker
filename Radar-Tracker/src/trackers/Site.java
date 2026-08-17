package trackers;

import java.util.concurrent.atomic.AtomicInteger;

//Represents a sensitive site that tracked craft should stay away from. 
public class Site {
	//Ensures no two sites will have the same id. 
	private final static AtomicInteger idProvider = new AtomicInteger(2000);
	
	private final double x;
	private final double y;
	private final int id = idProvider.getAndIncrement();
	
	//Constructor
	public Site(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//Calculates a distance from a point given its (x,y) coordinates. 
	public double distance (double x, double y) {
		
		double xDiff = this.x - x;
		double yDiff = this.y - y;
		
		return Math.hypot(xDiff, yDiff);
	}
	
	//getters
	public double getX() {return this.x;}
	public double getY() {return this.y;}
	public int getId() {return this.id;}
}
