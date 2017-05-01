package core;

public class Rod {
	
	public Rod(int id, int startPointId, int endPointId, int sectionId, int materialId) {
		super();
		this.id = id;
		this.startPointId = startPointId;
		this.endPointId = endPointId;
		this.sectionId = sectionId;
		this.materialId = materialId;
	}

	int id;
	int startPointId;
	int endPointId;
	int sectionId;
	int materialId;
	
	double length;
	double mass;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getStartPointId() {
		return startPointId;
	}

	public void setStartPointId(int startPointId) {
		this.startPointId = startPointId;
	}

	public int getEndPointId() {
		return endPointId;
	}

	public void setEndPointId(int endPointId) {
		this.endPointId = endPointId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	
	
	public void setLength() {
		Node node1 = FemModel.getNodeById(startPointId);
		Node node2 = FemModel.getNodeById(endPointId);
		
		double x1 = node1.x;
		double y1 = node1.y;
		double z1 = node1.z;
		double x2 = node2.x;
		double y2 = node2.y;
		double z2 = node2.z;
		double dx = x2 - x1;
		double dy = y2 - y1;
		double dz = z2 - z1;
		length = Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public double getLength() {
		return length;
	}
	
	
	public void setMass() {
		Material material = FemModel.getMaterialById(materialId);
		Section section = FemModel.getSectionById(sectionId);
		mass = length * section.Area * material.ro;
	}
	
	public double getMass() {
		return mass;
	}

}
