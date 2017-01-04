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

}
