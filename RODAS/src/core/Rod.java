package core;

public class Rod {
	
	public Rod(int id, int startPointId, int endPointId, int sectionId, int materialId) {
		super();
		this.id = id;
		this.startPoint.id = startPointId;
		this.endPoint.id = endPointId;
		this.section.id = sectionId;
		this.material.id = materialId;
	}

	int id;
	Node startPoint;
	Node endPoint;
	Section section;
	Material material;
	
	double length;
	double mass;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getStartPointId() {
		return startPoint.id;
	}

	public void setStartPointId(int startPointId) {
		this.startPoint.id = startPoint.id;
	}

	public int getEndPointId() {
		return endPoint.id;
	}

	public void setEndPointId(int endPointId) {
		this.endPoint.id = endPoint.id;
	}

	public int getSectionId() {
		return section.id;
	}

	public void setSectionId(int sectionId) {
		this.section.id = sectionId;
	}

	public int getMaterialId() {
		return material.id;
	}

	public void setMaterialId(int materialId) {
		this.material.id = materialId;
	}
	
	public void setLength() {
		double x1 = startPoint.x;
		double y1 = startPoint.y;
		double z1 = startPoint.z;
		double x2 = endPoint.x;
		double y2 = endPoint.y;
		double z2 = endPoint.z;
		double dx = x2 - x1;
		double dy = y2 - y1;
		double dz = z2 - z1;
		length = Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public double getLength() {
		return length;
	}
	
	public void setMass() {
		mass = length * material.ro;
	}
	
	public double getMass() {
		return mass;
	}

}
