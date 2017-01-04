package core;

public class Section {
	
	public Section(int id, double area, double ix, double iy, double wx, double wy) {
		super();
		this.id = id;
		Area = area;
		Ix = ix;
		Iy = iy;
		Wx = wx;
		Wy = wy;
	}
	
	int id;
	double Area;
	double Ix;
	double Iy;
	double Wx;
	double Wy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getArea() {
		return Area;
	}
	public void setArea(double area) {
		Area = area;
	}
	public double getIx() {
		return Ix;
	}
	public void setIx(double ix) {
		Ix = ix;
	}
	public double getIy() {
		return Iy;
	}
	public void setIy(double iy) {
		Iy = iy;
	}
	public double getWx() {
		return Wx;
	}
	public void setWx(double wx) {
		Wx = wx;
	}
	public double getWy() {
		return Wy;
	}
	public void setWy(double wy) {
		Wy = wy;
	}

}
