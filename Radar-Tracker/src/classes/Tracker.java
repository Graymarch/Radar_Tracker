package classes;

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
	
	public Tracker(int id, double x, double y, double velocity, double heading, sizeClass sizeClass, long lastUpdate) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.heading = heading;
		this.sizeClass = sizeClass;
		this.lastUpdate = lastUpdate;
	}
	
	public void advance(double hours) {
		this.x += hours * (velocity * Math.cos(heading));
		this.y += hours * (velocity * Math.sin(heading));
		this.lastUpdate = System.currentTimeMillis();
	}
	
	public int getId() {return this.id;}
	public double getX() {return this.x;}
	public double getY() {return this.y;}
	public double getHeading() {return this.heading;}
	public sizeClass getSize() {return this.sizeClass;}
	public threatType getThreat() {return this.threatType;}
	public long getLastUpdate() {return this.lastUpdate;}
	
	public void setThreat(threatType threatType) {this.threatType = threatType;}

}