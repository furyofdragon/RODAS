package core;

import java.util.Vector;

public class FemModel {
	
	public static int nPoints;				// number of points
	public static int nRods;				// number of rods
	public static int nSections;			// number of sections
	public static int nMaterials;			// number of materials
	
	public static Vector<Node> vNodes = new Vector<Node>();;
	public static Vector<Rod> vRods;
	public static Vector<Section> vSections;
	public static Vector<Material> vMaterials;
	
	static public Vector<Node> getvNodes () {
		return vNodes;
	}
	
	static public Vector<Rod> getvRods () {
		return vRods;
	}

	public static Vector<Section> getvSections() {
		return vSections;
	}

	public static Vector<Material> getvMaterials() {
		return vMaterials;
	}

}
