package core;

import math.Matrix;

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
	
	private int s = 12;								// matrix size
	Matrix Klocal = new Matrix(s, s);		// stiffness matrix 12x12 in local coordinates
	Matrix Kglobal = new Matrix(s, s);		// stiffness matrix 12x12 in global coordinates
	Matrix Transl = new Matrix(s, s);		// coordinate translation matrix
	
	
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
		double Ik = section.Ik;
		
		double length2 = length * length;
		double length3 = Math.pow(length, 3);
		
		// order
		// u1x u1y u1z fi1x fi1y fi1z u2x u2y u2z fi2x fi2y fi2z
			
		Klocal.data[0][0]   = E * A / length;
		Klocal.data[0][6]   = -1 * E * A / length;
		
		Klocal.data[1][1]   = 12 * E * Iy / length3;
		Klocal.data[1][5]   = 6 * E * Iy / length2;
		Klocal.data[1][7]   = -12 * E * Iy / length3;
		Klocal.data[1][11]  = 6 * E * Iy / length2;
		
		Klocal.data[2][2]   = 12 * E * Iz / length3;
		Klocal.data[2][4]   = -6 * E * Iz / length2;
		Klocal.data[2][8]   = -12 * E * Iz / length3;
		Klocal.data[2][10]  = -6 * E * Iz / length2;
		
		Klocal.data[3][3]   = G * Ik / length;
		Klocal.data[3][9]   = -1 * G * Ik / length;
		
		Klocal.data[4][4]   = 4 * E * Iz / length;
		Klocal.data[4][8]   = -6 * E * Iz / length2;
		Klocal.data[4][10]  = 2 * E * Iz / length;
		
		Klocal.data[5][5]   = 4 * E * Iy / length;
		Klocal.data[5][7]   = -6 * E * Iy / length2;
		Klocal.data[5][11]  = 2 * E * Iy / length;
		
		Klocal.data[6][6]   = E * A / length;
		
		Klocal.data[7][7]   = 12 * E * Iy / length3;
		Klocal.data[7][11]  = -6 * E * Iy / length2;
		
		Klocal.data[8][8]   = 12 * E * Iz / length3;
		Klocal.data[8][10]  = 6 * E * Iz / length2;
		
		Klocal.data[9][9]   = G * Ik / length;
		
		Klocal.data[10][10] = 4 * E * Iz / length;
		
		Klocal.data[11][11] = 4 * E * Iy / length;
		
		// below main diagonal - symmetrical
		for (int i = 1; i < s; i++) {
			for (int j = 0; j < i; j++) {
				Klocal.data[i][j] = Klocal.data[j][i];
			}
		}
	}
	
	public void calcTransl() {
		Node node1 = FemModel.getNodeById(startPointId);
		Node node2 = FemModel.getNodeById(endPointId);
		
		double x1 = node1.x;
		double y1 = node1.y;
		double z1 = node1.z;
		double x2 = node2.x;
		double y2 = node2.y;
		double z2 = node2.z;
		
		// TODO: code below only for flat overlap z = z'
		double lxx = (x2 - x1) / length;
		double lxy = (y2 - y1) / length;
		double lxz = (z2 - z1) / length;
		double lyx = lxy;
		double lyy = lxx;
		double lyz = 0;
		double lzx = 0;
		double lzy = 0;
		double lzz = 1;
		
		Transl.data[0][0] = lxx;
		Transl.data[0][1] = lxy;
		Transl.data[0][2] = lxz;
		Transl.data[1][0] = lyx;
		Transl.data[1][1] = lyy;
		Transl.data[1][2] = lyz;
		Transl.data[2][0] = lzx;
		Transl.data[2][1] = lzy;
		Transl.data[2][2] = lzz;
		
		Transl.data[3][3] = lxx;
		Transl.data[3][4] = lxy;
		Transl.data[3][5] = lxz;
		Transl.data[4][3] = lyx;
		Transl.data[4][4] = lyy;
		Transl.data[4][5] = lyz;
		Transl.data[5][3] = lzx;
		Transl.data[5][4] = lzy;
		Transl.data[5][5] = lzz;
		
		Transl.data[6][6] = lxx;
		Transl.data[6][7] = lxy;
		Transl.data[6][8] = lxz;
		Transl.data[7][6] = lyx;
		Transl.data[7][7] = lyy;
		Transl.data[7][8] = lyz;
		Transl.data[8][6] = lzx;
		Transl.data[8][7] = lzy;
		Transl.data[8][8] = lzz;
		
		Transl.data[9][9]   = lxx;
		Transl.data[9][10]  = lxy;
		Transl.data[9][11]  = lxz;
		Transl.data[10][9]  = lyx;
		Transl.data[10][10] = lyy;
		Transl.data[10][11] = lyz;
		Transl.data[11][9]  = lzx;
		Transl.data[11][10] = lzy;
		Transl.data[11][11] = lzz;
	}
	
	// element's stiffness matrix in global coordinates
	public void calcKglobal() {
		Matrix TranslT = Transl.transponse();
	}

}
