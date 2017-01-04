package core;

public class Rod {
	
	public Rod(int id, Point startPoint, Point endPoint, Section section, Material material) {
		super();
		this.id = id;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.section = section;
		this.material = material;
	}
	
	public Rod(int id, int psid, int peid, int sid, int mid) {
		this.id = id;
		
	}

	int id;
	Point startPoint;
	Point endPoint;
	Section section;
	Material material;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}

}
