package trackers;

import enums.*;

public class Tracker{
	private final int id; 
	private double x;
	private double y;
	private double heading;
	private double velocity;
	private sizeClass sizeClass; 
	private threatType threatType;
	private long lastUpdate;
	
	public Tracker(int id, double x, double y, double velocity, double heading, sizeClass sizeClass) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.heading = heading;
		this.sizeClass = sizeClass;
		this.lastUpdate = System.currentTimeMillis();
	}
	
	public Tracker(Tracker tracker) {
		this.id = tracker.getId();
		this.x = tracker.getX();
		this.y = tracker.getY();
		this.velocity = tracker.getVelocity();
		this.heading = tracker.getHeading();
		this.sizeClass = tracker.getSize();
		this.lastUpdate = System.currentTimeMillis();
	}
	
	public void advance(double seconds) {
		this.x += seconds * (velocity * Math.cos(heading));
		this.y += seconds * (velocity * Math.sin(heading));
		this.lastUpdate = System.currentTimeMillis();
	}
	
	public String toString() {
		return String.format("[Object %d]: Location-(%f, %f), Velocity-%f ft/s, Heading-%f degrees, Size-%s", 
				this.id, this.x, this.y, this.velocity, (this.heading * (180/Math.PI)), this.sizeClass);
	}
	
	public int getId() {return this.id;}
	public double getX() {return this.x;}
	public double getY() {return this.y;}
	public double getVelocity() {return this.velocity;}
	public double getHeading() {return this.heading;}
	public sizeClass getSize() {return this.sizeClass;}
	public threatType getThreat() {return this.threatType;}
	public long getLastUpdate() {return this.lastUpdate;}
	
	public void setThreat(threatType threatType) {this.threatType = threatType;}

}