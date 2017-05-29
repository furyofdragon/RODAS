package core;

public class Section {
	
	public Section(int id, double Area, double Ix, double Iy, double Wx, double Wy) {
		super();
		this.id = id;
		this.Area = Area;
		this.Ix = Ix;
		this.Iy = Iy;
		this.Ik = 0;
		this.Wx = Wx;
		this.Wy = Wy;
	}
	
	public Section(int id, double Area, double Ix, double Iy, double Ik, double Wx, double Wy) {
		super();
		this.id = id;
		this.Area = Area;
		this.Ix = Ix;
		this.Iy = Iy;
		this.Ik = Ik;
		this.Wx = Wx;
		this.Wy = Wy;
	}
	
	int id;
	double Area;
	double Ix;
	double Iy;
	double Ik;
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
	
	public void setArea(double Area) {
		this.Area = Area;
	}
	
	public double getIx() {
		return Ix;
	}
	
	public void setIx(double Ix) {
		this.Ix = Ix;
	}
	
	public double getIy() {
		return Iy;
	}
	
	public void setIy(double Iy) {
		this.Iy = Iy;
	}
	
	public double getWx() {
		return Wx;
	}
	
	public void setWx(double Wx) {
		this.Wx = Wx;
	}
	
	public double getWy() {
		return Wy;
	}
	
	public void setWy(double Wy) {
		this.Wy = Wy;
	}

}
