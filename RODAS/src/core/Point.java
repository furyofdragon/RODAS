package core;

public class Point {

	public Point(int id, double x, double y, double z) {
		super();
		this.id = id;
		this.x  = x;
		this.y  = y;
		this.z  = z;
	}

	int   id;
	double x;
	double y;
	double z;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
}
