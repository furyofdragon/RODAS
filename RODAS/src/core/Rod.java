package core;

public class Rod {
	
	public Rod(int id, int startPointId, int endPointId, int sectionId, int materialId) {
		super();
		this.id = id;
		this.startPointId = startPointId;
		this.endPointId = endPointId;
		this.sectionId = sectionId;
		this.materialId = materialId;
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Klocal[i][j] = 0;
			}
		}
	}

	int id;
	int startPointId;
	int endPointId;
	int sectionId;
	int materialId;
	
	double length;
	double mass;
	
	double[][] Klocal = new double[12][12];		// stiffness matrix 12x12 in local coordinates
	
	
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
	
	
	// element's stiffness matrix in local coordinates
	public void calcKlocal() {
		Material material = FemModel.getMaterialById(materialId);
		Section section = FemModel.getSectionById(sectionId);
		
		double E = material.E;
		double G = material.G;
		double A = section.Area;
		double Iy = section.Ix;
		double Iz = section.Iy;
		// TODO fix input
		double Ik = 0;
		
		double length2 = length * length;
		double length3 = Math.pow(length, 3);
			
		Klocal[0][0]   = E * A / length;
		Klocal[0][6]   = -1 * E * A / length;
		
		Klocal[1][1]   = 12 * E * Iy / length3;
		Klocal[1][5]   = 6 * E * Iy / length2;
		Klocal[1][7]   = -12 * E * Iy / length3;
		Klocal[1][11]  = 6 * E * Iy / length2;
		
		Klocal[2][2]   = 12 * E * Iz / length3;
		Klocal[2][4]   = -6 * E * Iz / length2;
		Klocal[2][8]   = -12 * E * Iz / length3;
		Klocal[2][10]  = -6 * E * Iz / length2;
		
		Klocal[3][3]   = G * Ik / length;
		Klocal[3][9]   = -1 * G * Ik / length;
		
		Klocal[4][4]   = 4 * E * Iz / length;
		Klocal[4][8]   = -6 * E * Iz / length2;
		Klocal[4][10]  = 2 * E * Iz / length;
		
		Klocal[5][5]   = 4 * E * Iy / length;
		Klocal[5][7]   = -6 * E * Iy / length2;
		Klocal[5][11]  = 2 * E * Iy / length;
		
		Klocal[6][6]   = E * A / length;
		
		Klocal[7][7]   = 12 * E * Iy / length3;
		Klocal[7][11]  = -6 * E * Iy / length2;
		
		Klocal[8][8]   = 12 * E * Iz / length3;
		Klocal[8][10]  = 6 * E * Iz / length2;
		
		Klocal[9][9]   = G * Ik / length;
		
		Klocal[10][10] = 4 * E * Iz / length;
		
		Klocal[11][11] = 4 * E * Iy / length;
		
		// below main diagonal - simmetrical
		for (int i = 1; i < 12; i++) {
			for (int j = 0; j < i; j++) {
				Klocal[i][j] = Klocal[j][i];
			}
		}
	}

}
